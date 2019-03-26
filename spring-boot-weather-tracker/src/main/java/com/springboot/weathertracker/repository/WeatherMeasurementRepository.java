package com.springboot.weathertracker.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.weathertracker.domain.WeatherMeasurement;
import com.springboot.weathertracker.domain.WeatherMeasurementIndentity;

@Repository
public interface WeatherMeasurementRepository extends JpaRepository<WeatherMeasurement, WeatherMeasurementIndentity> {

	List<WeatherMeasurement> findByWeatherMeasurementIndentityMetricType(String metricType);

	List<WeatherMeasurement> findByWeatherMeasurementIndentityTimestamp(Timestamp timestamp);
	
	List<WeatherMeasurement> findAllByWeatherMeasurementIndentityTimestampBetween(Timestamp fromTimestamp, Timestamp toTimestamp);
}
