package com.springboot.weathertracker.domain;

public class WeatherTrackerRequest {
	
	public WeatherTrackerRequest(String timestamp, double temprature, double dewPoint, double precipitation ) {

		this.timestamp = timestamp;
		this.temprature = temprature;
		this.dewPoint = dewPoint;
		this.precipitation = precipitation;

	}

	private String timestamp;
	private double temprature;
	private double dewPoint;
	private double precipitation;
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public double getTemprature() {
		return temprature;
	}
	public void setTemprature(double temprature) {
		this.temprature = temprature;
	}
	public double getDewPoint() {
		return dewPoint;
	}
	public void setDewPoint(double dewPoint) {
		this.dewPoint = dewPoint;
	}
	public double getPrecipitation() {
		return precipitation;
	}
	public void setPrecipitation(double precipitation) {
		this.precipitation = precipitation;
	}
	
	
	
}
