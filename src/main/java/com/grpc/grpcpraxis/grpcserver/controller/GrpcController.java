package com.grpc.grpcpraxis.grpcserver.controller;

import com.grpc.grpcpraxis.grpcserver.dto.RestRequest;
import com.grpc.grpcpraxis.grpcserver.dto.RestResponse;
import com.grpc.grpcpraxis.grpcserver.dto.RestResponseNumberFactor;
import com.grpc.grpcpraxis.grpcserver.services.NumberFactorResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grpcServer/rest")
public class GrpcController {
    private final NumberFactorResultService service;
    @Autowired
    private GrpcController(NumberFactorResultService service) {
        this.service = service;
    }
    @PostMapping("/fetch")
    public RestResponse getNumberFactor(@RequestBody RestRequest restRequest) {
            List<RestResponseNumberFactor> result = service.sayHelloByRest(restRequest);
        RestResponse response = new RestResponse();
        response.setRestResponseNumberFactor(result);
        response.setSize(result.size());
        return response;
    }
}
