package com.offsidegaming.monitoring.controller;

import com.offsidegaming.monitoring.model.response.MeasurementResponse;
import com.offsidegaming.monitoring.model.response.HistoryResponse;
import com.offsidegaming.monitoring.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.badRequest;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping("/histories/{userId}")
    public ResponseEntity getHistories(@PathVariable("userId") Long userId) {
        try {
            Map<String, List<MeasurementResponse>> result = measurementService.getByUseId(userId);
            return ResponseEntity.ok().body(HistoryResponse.builder().measurementView(result).build());
        } catch (Exception e) {
            return badRequest().body(HistoryResponse.builder().message(e.toString()).build());
        }
    }

    @PostMapping("/{userId}")
    public ResponseEntity submitMeasurements(@PathVariable("userId") Long userId,
                                             @RequestBody List<MeasurementResponse> appointmentViews) {
        try {
            return ResponseEntity.ok(measurementService.submitMeasurements(appointmentViews, userId));
        } catch (Exception e) {
            return badRequest().body(HistoryResponse.builder().message(e.toString()).build());
        }
    }
}
