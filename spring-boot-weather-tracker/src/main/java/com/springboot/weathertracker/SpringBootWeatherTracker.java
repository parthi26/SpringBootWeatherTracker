package com.springboot.weathertracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.weathertracker.controller.WeatherTrackerController;
import com.springboot.weathertracker.domain.WeatherTrackerRequest;
import com.springboot.weathertracker.domain.WeatherTrackerStatistics;

@SpringBootApplication
public class SpringBootWeatherTracker implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	WeatherTrackerController controller;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWeatherTracker.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		logger.info("Measurements added -> {}",
				controller.addWeatherMeasurement(new WeatherTrackerRequest("2018-10-02 18:48:05.100", 29.6, 1.5, 2.5)));

		logger.info("Measurements added -> {}",
				controller.addWeatherMeasurement(new WeatherTrackerRequest("2019-10-02 18:48:05.100", 30.6, 1.5, 2.5)));

		logger.info("Measurements added -> {}",
				controller.addWeatherMeasurement(new WeatherTrackerRequest("2019-03-02 18:48:05.100", 25.6, 1.5, 2.5)));

		logger.info("Retrieving Measurements -> {}",
				controller.getWeatherMeasurementByTimestamp("2018-10-02 18:48:05.100"));

		logger.info("Retrieving Statistics -> {}",
				controller.getWeatherMeasurementStatisticsByRanges(new WeatherTrackerStatistics("min", "temprature",
						"2018-10-02 18:48:05.100", "2019-10-02 18:48:05.100")));

	}
}
