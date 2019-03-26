package com.springboot.weathertracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.weathertracker.domain.WeatherMeasurement;
import com.springboot.weathertracker.domain.WeatherTrackerRequest;
import com.springboot.weathertracker.domain.WeatherTrackerStatistics;
import com.springboot.weathertracker.exception.ResourceNotFoundException;
import com.springboot.weathertracker.service.WeatherTrackerService;

@RestController
public class WeatherTrackerController {

	@Autowired
	private WeatherTrackerService weatherTrackerService;

	@RequestMapping(value = "/measurements/{timestamp:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<WeatherMeasurement> getWeatherMeasurementByTimestamp(@PathVariable("timestamp") String timestamp) {
		try {
			System.out.println("Inside Get Controller" + timestamp);
			return weatherTrackerService.getAllMeasurementsByTimestamp(timestamp);
		} catch (ResourceNotFoundException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement Not Found", exc);
		}
	}

	@PostMapping("/measurements")
	@ResponseStatus(HttpStatus.CREATED)
	public List<WeatherMeasurement> addWeatherMeasurement(@RequestBody WeatherTrackerRequest weatherMeasurement) {

		List<WeatherMeasurement> weatherMeasurements = weatherTrackerService.addWeatherMeasurement(weatherMeasurement);

		return weatherMeasurements;
	}

	@RequestMapping(value = "/stats", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public WeatherMeasurement getWeatherMeasurementStatisticsByRanges(
			@RequestBody WeatherTrackerStatistics weatherTrackerStatistics) {

		try {
			return weatherTrackerService.getAllMeasurementsByTimestampRanges(weatherTrackerStatistics);
		} catch (ResourceNotFoundException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement Not Found", exc);
		}
	}

}
