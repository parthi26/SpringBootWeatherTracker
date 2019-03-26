package com.springboot.weathertracker;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.weathertracker.controller.WeatherTrackerController;
import com.springboot.weathertracker.domain.WeatherMeasurement;
import com.springboot.weathertracker.domain.WeatherMeasurementIndentity;
import com.springboot.weathertracker.domain.WeatherTrackerRequest;
import com.springboot.weathertracker.service.WeatherTrackerService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = WeatherTrackerController.class, secure = false)
public class WeatherTrackerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WeatherTrackerService weatherTrackerService;

	List<WeatherMeasurement> mockMeasurements = new ArrayList<WeatherMeasurement>();

	WeatherMeasurementIndentity mockweatherMeasurementIndentity = new WeatherMeasurementIndentity(
			Timestamp.valueOf("2011-10-02 18:48:05.123"), "temprature");
	WeatherMeasurement mockMeasurement = new WeatherMeasurement(mockweatherMeasurementIndentity, "29.6");
	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void addMeasurement() throws Exception {

		mockMeasurements.add(mockMeasurement);

		WeatherTrackerRequest mockWeatherTracketReq = new WeatherTrackerRequest("2011-10-02 18:48:05.123", 29.6, 1.5,
				2.5);

		String weatherTrackerRequestJson = mapper.writeValueAsString(mockWeatherTracketReq);

		Mockito.when(weatherTrackerService.addWeatherMeasurement(Mockito.any(WeatherTrackerRequest.class)))
				.thenReturn(mockMeasurements);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/measurements").accept(MediaType.APPLICATION_JSON)
				.content(weatherTrackerRequestJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

	@Test
	public void getWeatherMeasurementByTimestamp() throws Exception {

		mockMeasurements.add(mockMeasurement);

		Mockito.when(weatherTrackerService.getAllMeasurementsByTimestamp(Mockito.anyString()))
				.thenReturn(mockMeasurements);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/measurements/2011-10-02 18:48:05.123")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());

		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		
	}
}
