package com.grpc.grpcpraxis.grpcserver.services.impl;

import com.grpc.grpcpraxis.grpcserver.HelloWorld;
import com.grpc.grpcpraxis.grpcserver.HelloWorld.HelloRequest;
import com.grpc.grpcpraxis.grpcserver.HelloWorld.HelloResponse;
import com.grpc.grpcpraxis.grpcserver.HelloWorldServiceGrpc.HelloWorldServiceImplBase;
import com.grpc.grpcpraxis.grpcserver.entities.NumberFactorResult;
import com.grpc.grpcpraxis.grpcserver.exceptions.ValidationException;
import com.grpc.grpcpraxis.grpcserver.mapper.NumberFactorResultMapper;
import com.grpc.grpcpraxis.grpcserver.repositories.NumberRepository;
import com.grpc.grpcpraxis.grpcserver.validators.Validator;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@GRpcService
public class HelloWorldGrpcServiceImpl extends HelloWorldServiceImplBase {
    private final NumberRepository numberRepository;
    private final Validator validator;
    @Autowired
    public HelloWorldGrpcServiceImpl(Validator validator, NumberRepository numberRepository) {
        this.validator = validator;
        this.numberRepository = numberRepository;
    }

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        try {
            // Perform field validation on the request message
            validator.validateFields(request);

            //proceed for response
            List<NumberFactorResult> numberFactorResults =numberRepository.getValue();
            List<HelloWorld.NumberFactorResult> numberFactorResultResponse = numberFactorResults.stream()
                .map(this::convertToDto)
                .sorted(Comparator.comparing(HelloWorld.NumberFactorResult::getFactorValue).reversed())
                .collect(Collectors.toList());

            HelloResponse response = HelloResponse.newBuilder()
                .addAllNumberFactor(numberFactorResultResponse)
                .setSize(numberFactorResultResponse.size())
                .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (ValidationException e) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription(e.getMessage()).asRuntimeException());
        }
    }

    private HelloWorld.NumberFactorResult convertToDto(NumberFactorResult myProjection) {
        return NumberFactorResultMapper.toNumberFactorResult(myProjection);
    }

}
