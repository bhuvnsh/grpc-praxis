package com.grpc.grpcpraxis.grpcserver.services;

import com.grpc.grpcpraxis.grpcserver.dto.RestRequest;
import com.grpc.grpcpraxis.grpcserver.dto.RestResponseNumberFactor;

import java.util.List;

public interface NumberFactorResultService {
    List<RestResponseNumberFactor>  sayHelloByRest(RestRequest restRequest);
}
