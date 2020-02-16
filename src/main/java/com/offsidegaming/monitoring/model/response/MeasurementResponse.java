package com.offsidegaming.monitoring.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MeasurementResponse {
    private Double amount;
    private String created;
    private String measurementName;
}
