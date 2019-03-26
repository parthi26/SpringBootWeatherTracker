package com.springboot.weathertracker.domain;

public class WeatherTrackerStatistics {

	
	private String stat ;
	private String metric;
	private String fromDateTime;
	private String toDateTime;
	
	
	public WeatherTrackerStatistics(String stat, String metric, String fromDateTime , String toDateTime) {

		this.stat = stat;
		this.metric = metric;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
	}
	
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getMetric() {
		return metric;
	}
	public void setMetric(String metric) {
		this.metric = metric;
	}
	public String getFromDateTime() {
		return fromDateTime;
	}
	public void setFromDateTime(String fromDateTime) {
		this.fromDateTime = fromDateTime;
	}
	public String getToDateTime() {
		return toDateTime;
	}
	public void setToDateTime(String toDateTime) {
		this.toDateTime = toDateTime;
	}
	
	
	
}
