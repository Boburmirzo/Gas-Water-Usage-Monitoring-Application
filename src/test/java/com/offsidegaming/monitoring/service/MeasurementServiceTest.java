package com.offsidegaming.monitoring.service;

import com.offsidegaming.monitoring.model.entity.Measurement;
import com.offsidegaming.monitoring.model.response.MeasurementResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MeasurementServiceTest {

    @Autowired
    MeasurementService measurementService;

    @Test
    void getByUserIdTest1() {
        //Given
        Long randomUserId = new Random().nextLong();

        //When
        //Then
        assertThrows(RuntimeException.class, () -> measurementService.getByUseId(randomUserId));
    }

    @Test
    void getByUserIdTest2() {
        //Given
        Long existingUser = 1L;

        //When
        Map<String, List<MeasurementResponse>> results = measurementService.getByUseId(existingUser);

        //Then
        assertEquals(3, results.size());
    }

    @Test
    void submitMeasurementsTest1() {
        //Given
        MeasurementResponse measurementResponse1 = MeasurementResponse.builder().amount(0.0).measurementName("GAS").build();
        MeasurementResponse measurementResponse2 = MeasurementResponse.builder().amount(0.132).measurementName("HOT_WATER").build();
        MeasurementResponse measurementResponse3 = MeasurementResponse.builder().amount(0.788).measurementName("HOT_WATER").build();
        List<MeasurementResponse> appointmentViews = Arrays.asList(measurementResponse1, measurementResponse2, measurementResponse3);
        Long existingUser = 1L;

        //When
        //Then
        assertThrows(RuntimeException.class, () -> measurementService.submitMeasurements(appointmentViews, existingUser));
    }

    @Test
    void submitMeasurementsTest2() {
        //Given
        MeasurementResponse measurementResponse1 = MeasurementResponse.builder().amount(0.0).measurementName("NOT_EXISTING_TYPE").build();
        List<MeasurementResponse> appointmentViews = Collections.singletonList(measurementResponse1);
        Long existingUser = 1L;

        //When
        //Then
        assertThrows(RuntimeException.class, () -> measurementService.submitMeasurements(appointmentViews, existingUser));
    }

    @Test
    void submitMeasurementsTest3() {
        //Given
        Long existingUser = 1L;
        //When
        //Then
        assertThrows(RuntimeException.class, () -> measurementService.submitMeasurements(null, existingUser));
    }

    @Test
    void submitMeasurementsTest4() {
        //Given
        double gasAmount = 0.0;
        double hotWaterAmount = 0.132;
        double coldWaterAmount = 0.788;
        MeasurementResponse measurementResponse1 = MeasurementResponse.builder().amount(gasAmount).measurementName("GAS").build();
        MeasurementResponse measurementResponse2 = MeasurementResponse.builder().amount(hotWaterAmount).measurementName("HOT_WATER").build();
        MeasurementResponse measurementResponse3 = MeasurementResponse.builder().amount(coldWaterAmount).measurementName("COLD_WATER").build();
        List<MeasurementResponse> appointmentViews = Arrays.asList(measurementResponse1, measurementResponse2, measurementResponse3);
        Long existingUser = 1L;

        //When
        Map<Long, List<Measurement>> savedMeasures = measurementService.submitMeasurements(appointmentViews, existingUser);

        //Then
        assertEquals(3, savedMeasures.size());
        assertEquals(1, savedMeasures.get(1L).size());
        assertEquals(new Double(gasAmount), savedMeasures.get(1L).get(0).getAmount());
        assertEquals(1, savedMeasures.get(2L).size());
        assertEquals(new Double(hotWaterAmount), savedMeasures.get(2L).get(0).getAmount());
        assertEquals(1, savedMeasures.get(3L).size());
        assertEquals(new Double(coldWaterAmount), savedMeasures.get(3L).get(0).getAmount());
    }

}