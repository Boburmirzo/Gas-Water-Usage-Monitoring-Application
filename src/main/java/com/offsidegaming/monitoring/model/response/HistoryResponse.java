package com.offsidegaming.monitoring.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class HistoryResponse {
    private String message;
    private Map<String, List<MeasurementResponse>> measurementView;
}