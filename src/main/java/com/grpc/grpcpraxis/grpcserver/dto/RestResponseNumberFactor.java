package com.grpc.grpcpraxis.grpcserver.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestResponseNumberFactor {
    private Long value;
    private Long factorValue;
    private double result;
    private double factoredValue;
    private String name;
}
