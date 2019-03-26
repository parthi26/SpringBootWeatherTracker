package com.springboot.weathertracker.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class WeatherMeasurement {

	@EmbeddedId
	private WeatherMeasurementIndentity weatherMeasurementIndentity;


	private String metricValue;

	public WeatherMeasurement() {

	}

	public WeatherMeasurement(WeatherMeasurementIndentity id, String metricValue) {

		this.weatherMeasurementIndentity = id;
		this.metricValue = metricValue;

	}

	public WeatherMeasurementIndentity getWeatherMeasurementIndentity() {
		return weatherMeasurementIndentity;
	}

	public void setWeatherMeasurementIndentity(WeatherMeasurementIndentity weatherMeasurementIndentity) {
		this.weatherMeasurementIndentity = weatherMeasurementIndentity;
	}

	public String getMetricValue() {
		return metricValue;
	}

	public void setMetricValue(String metricValue) {
		this.metricValue = metricValue;
	}

	@Override
	public String toString() {
		return String.format("WeatherMeasurement [weatherMeasurementIndentity=%s, metricValue=%s ]",
				weatherMeasurementIndentity, metricValue);
	}

}
