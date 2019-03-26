package com.springboot.weathertracker.utils;

import com.springboot.weathertracker.domain.WeatherMeasurement;
import com.springboot.weathertracker.domain.WeatherTrackerRequest;

public enum WeatherTrackerEnum {

	temprature {
		@Override
		public WeatherMeasurement create(WeatherTrackerRequest req, WeatherMeasurement entity) {
			if(req.getTemprature() !=0) {
			entity.getWeatherMeasurementIndentity().setMetricType("temprature");
			entity.setMetricValue(String.valueOf(req.getTemprature()));
			}
			return entity;
		}
	},
	dewPoint {

		@Override
		public WeatherMeasurement create( WeatherTrackerRequest req, WeatherMeasurement entity) {
			
			if(req.getDewPoint() != 0) {
				entity.getWeatherMeasurementIndentity().setMetricType("dewpoint");
				entity.setMetricValue(String.valueOf(req.getDewPoint()));
			}
			return entity;
		}
	},
	precipitation {

		@Override
		public WeatherMeasurement create(WeatherTrackerRequest req, WeatherMeasurement entity) {
			
			if(req.getPrecipitation() != 0) {
				entity.getWeatherMeasurementIndentity().setMetricType("precipitation");
				entity.setMetricValue(String.valueOf(req.getPrecipitation()));
			}
			return entity;
		}
	};

	
	public abstract WeatherMeasurement create( WeatherTrackerRequest req, WeatherMeasurement entity);
}

