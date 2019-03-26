package com.springboot.weathertracker.service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.weathertracker.domain.WeatherMeasurement;
import com.springboot.weathertracker.domain.WeatherMeasurementIndentity;
import com.springboot.weathertracker.domain.WeatherTrackerRequest;
import com.springboot.weathertracker.domain.WeatherTrackerStatistics;
import com.springboot.weathertracker.exception.ResourceNotFoundException;
import com.springboot.weathertracker.repository.WeatherMeasurementRepository;
import com.springboot.weathertracker.utils.WeatherTrackerEnum;

@Service
public class WeatherTrackerService {

	@Autowired
	private WeatherMeasurementRepository weatherMeasurementRepository;

	private Logger LOG = LoggerFactory.getLogger("WeatherTrackerService");

	public List<WeatherMeasurement> addWeatherMeasurement(WeatherTrackerRequest weatherMeasurementRequest) {

		List<WeatherMeasurement> weatherMeasurements = buildWeatherMeasurementEntity(weatherMeasurementRequest);

		for (WeatherMeasurement weatherMeasurement : weatherMeasurements) {
			weatherMeasurementRepository.save(weatherMeasurement);
		}

		LOG.info("Measurement count in DB: {}", weatherMeasurementRepository.count());
		LOG.info("Measurement with metricType dewpoint: {}",
				weatherMeasurementRepository.findByWeatherMeasurementIndentityMetricType("precipitation"));

		LOG.info("Measurement with timestamp: {}",
				weatherMeasurementRepository.findByWeatherMeasurementIndentityTimestamp(
						Timestamp.valueOf(weatherMeasurementRequest.getTimestamp())));

		return weatherMeasurements;
	}

	public List<WeatherMeasurement> getAllMeasurementsByTimestamp(String timestamp) throws ResourceNotFoundException {
		List<WeatherMeasurement> weatherMeasurement = new ArrayList<>();

		LOG.info("Inside getAllMeasurementsByTimestamp", timestamp);
		weatherMeasurementRepository.findByWeatherMeasurementIndentityTimestamp(Timestamp.valueOf(timestamp))
				.forEach(weatherMeasurement::add);
		
		if(weatherMeasurement.isEmpty()) {
			throw new ResourceNotFoundException("Measurements not found " + timestamp );
		}

		return weatherMeasurement;
	}

	public WeatherMeasurement getAllMeasurementsByTimestampRanges(
			WeatherTrackerStatistics weatherTrackerStatistics) throws ResourceNotFoundException {
		List<WeatherMeasurement> weatherMeasurement = new ArrayList<>();

		weatherMeasurementRepository.findAllByWeatherMeasurementIndentityTimestampBetween(
				Timestamp.valueOf(weatherTrackerStatistics.getFromDateTime()),
				Timestamp.valueOf(weatherTrackerStatistics.getToDateTime())).forEach(weatherMeasurement::add);

		weatherMeasurement = buildListByMetricType(weatherTrackerStatistics.getMetric(), weatherMeasurement);

		if(weatherMeasurement.isEmpty()) {
			throw new ResourceNotFoundException("Measurements not found " + weatherTrackerStatistics.getMetric() );
		}

		
		WeatherMeasurement minByMetricType = weatherMeasurement
			      .stream()
			      .min(Comparator.comparing(WeatherMeasurement::getMetricValue))
			      .orElseThrow(NoSuchElementException::new);
		
		
		return minByMetricType;
	}

	private List<WeatherMeasurement> buildWeatherMeasurementEntity(WeatherTrackerRequest weatherMeasurementRequest) {

		List<WeatherMeasurement> weatherMeasurements = new ArrayList<WeatherMeasurement>();

		Field[] fields = weatherMeasurementRequest.getClass().getDeclaredFields();

		for (Field field : fields) {

			if (ObjectUtils.notEqual("timestamp", field.getName())) {
				WeatherMeasurementIndentity weatherMeasurementIndentity = new WeatherMeasurementIndentity();
				WeatherMeasurement weatherMeasurement = new WeatherMeasurement(weatherMeasurementIndentity, "");

				weatherMeasurement.getWeatherMeasurementIndentity()
						.setTimestamp(Timestamp.valueOf(weatherMeasurementRequest.getTimestamp()));
				weatherMeasurements.add(buildEnumerator(weatherMeasurementRequest, weatherMeasurement,
						WeatherTrackerEnum.valueOf(field.getName())));

			}
		}

		return weatherMeasurements;
	}

	private WeatherMeasurement buildEnumerator(WeatherTrackerRequest req, WeatherMeasurement entity,
			WeatherTrackerEnum weatherTrackerEnum) {
		return weatherTrackerEnum.create(req, entity);
	}

	private List<WeatherMeasurement> buildListByMetricType(String metricType,
			List<WeatherMeasurement> weatherMeasurements) {

		List<WeatherMeasurement> weatherMeasurementsbyMetric = new ArrayList<>();
		for (WeatherMeasurement weatherMeasurement : weatherMeasurements) {
			if (weatherMeasurement.getWeatherMeasurementIndentity().getMetricType().equals(metricType)) {
				weatherMeasurementsbyMetric.add(weatherMeasurement);
			}
		}
		return weatherMeasurementsbyMetric;
	}
}
