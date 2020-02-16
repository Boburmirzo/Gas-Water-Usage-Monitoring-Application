package com.offsidegaming.monitoring.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.offsidegaming.monitoring.model.response.MeasurementResponse;
import com.offsidegaming.monitoring.service.MeasurementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MeasurementController.class)
public class MeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MeasurementService measurementService;

    @Test
    public void shouldReturnMeasurementHistories() throws Exception {
        //Given
        Long existingUser = 1L;
        MeasurementResponse measurementResponse1 = MeasurementResponse.builder().amount(0.0).measurementName("GAS").build();
        MeasurementResponse measurementResponse2 = MeasurementResponse.builder().amount(0.132).measurementName("HOT_WATER").build();
        MeasurementResponse measurementResponse3 = MeasurementResponse.builder().amount(0.788).measurementName("HOT_WATER").build();
        List<MeasurementResponse> measurementResponses = Arrays.asList(measurementResponse1, measurementResponse2, measurementResponse3);

        when(measurementService.getByUseId(existingUser))
                .thenReturn(measurementResponses.stream()
                        .collect(Collectors.groupingBy(MeasurementResponse::getMeasurementName)));

        mockMvc.perform(get("/measurement/histories/{userId}", existingUser))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldSubmitNewMeasurements() throws Exception {
        //Given
        Long existingUser = 1L;
        MeasurementResponse measurementResponse1 = MeasurementResponse.builder().amount(0.0).measurementName("GAS").build();
        MeasurementResponse measurementResponse2 = MeasurementResponse.builder().amount(0.132).measurementName("HOT_WATER").build();
        MeasurementResponse measurementResponse3 = MeasurementResponse.builder().amount(0.788).measurementName("HOT_WATER").build();
        List<MeasurementResponse> measurementResponses = Arrays.asList(measurementResponse1, measurementResponse2, measurementResponse3);

        mockMvc.perform(post("/measurement/{userId}", existingUser)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(measurementResponses)))
                .andExpect(status().isOk());
    }
}
