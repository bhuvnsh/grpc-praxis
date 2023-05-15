package com.grpc.grpcpraxis.grpcserver.dto;

import lombok.Data;

import java.util.List;

@Data
public class RestResponse {
    private List<RestResponseNumberFactor> restResponseNumberFactor;
    private int size;
}
