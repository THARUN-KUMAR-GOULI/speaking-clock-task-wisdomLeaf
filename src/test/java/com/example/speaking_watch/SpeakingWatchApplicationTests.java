package com.example.speaking_watch;

import com.example.speaking_watch.Controller.ClockController;
import com.example.speaking_watch.Service.ClockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Equals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SpeakingWatchApplicationTests {

	private MockMvc mockMvc;

	@InjectMocks
	private ClockController clockController;

	@Mock
	private ClockService clockService;

	@BeforeEach
	public void setup(){
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(clockController).build();
	}

	@Test
	public void testgettimewords() throws Exception{
		LocalTime time = LocalTime.now();
		String currentTime = time.format(DateTimeFormatter.ofPattern("HH:mm"));

		String expectedOutput = clockService.convertTimeToWords();

		Mockito.when(clockService.convertTimeToWords()).thenReturn("");

		mockMvc.perform(get("/api/convert"))
				.andExpect(status().isOk())
				.andExpect(content().string(""));
	}

	@Test
	public void testGetTimeWords_Negative() throws Exception {
		Mockito.when(clockService.convertTimeToWords()).thenThrow(new RuntimeException("Error occurred"));

		mockMvc.perform(get("/api/convert"))
				.andExpect(status().isInternalServerError())
				.andExpect(content().string("Error occurred"));
	}

	@Test
	public void testConvertTime_ValidTime_Positive() throws Exception {
		String inputTime = "12:00";
		String expectedOutput = "It's Midday";

		Mockito.when(clockService.convertTime(inputTime)).thenReturn(expectedOutput);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/time")
						.param("time", inputTime))
				.andExpect(status().isOk())
				.andExpect(content().string(expectedOutput));
	}

	@Test
	public void testConvertTime_InvalidTimeFormat_Negative() throws Exception {
		String inputTime = "12-00";  // Invalid format
		String expectedOutput = "Invalid format";

		Mockito.when(clockService.convertTime(inputTime)).thenReturn(expectedOutput);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/time")
						.param("time", inputTime))
				.andExpect(status().isOk())
				.andExpect(content().string(expectedOutput));
	}

	@Test
	public void testConvertTime_Exception_Negative() throws Exception {
		String inputTime = "invalid_time";

		Mockito.when(clockService.convertTime(inputTime)).thenThrow(new RuntimeException("Error occurred"));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/time")
						.param("time", inputTime))
				.andExpect(status().isInternalServerError());
	}


}
