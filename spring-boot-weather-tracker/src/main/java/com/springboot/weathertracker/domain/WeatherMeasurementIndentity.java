package com.springboot.weathertracker.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Embeddable
public class WeatherMeasurementIndentity  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
    private Timestamp timestamp;

    @NotNull
    @Size(max = 20)
    private String metricType;

    public WeatherMeasurementIndentity() {

    }

    public WeatherMeasurementIndentity(Timestamp timestamp, String metricType) {
        this.timestamp = timestamp;
        this.metricType = metricType;
    }

   
    public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getMetricType() {
		return metricType;
	}

	public void setMetricType(String metricType) {
		this.metricType = metricType;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherMeasurementIndentity that = (WeatherMeasurementIndentity) o;

        if (!timestamp.equals(that.timestamp)) return false;
        return metricType.equals(that.metricType);
    }

    @Override
    public int hashCode() {
        int result = timestamp.hashCode();
        result = 31 * result + metricType.hashCode();
        return result;
    }
}
