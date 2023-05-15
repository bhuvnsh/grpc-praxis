package com.grpc.grpcpraxis.grpcserver.mapper;

import com.github.javafaker.Faker;
import com.google.protobuf.DoubleValue;
import com.grpc.grpcpraxis.grpcserver.HelloWorld;
import com.grpc.grpcpraxis.grpcserver.dto.RestResponseNumberFactor;
import com.grpc.grpcpraxis.grpcserver.entities.NumberFactorResult;

import java.util.Random;

public class NumberFactorResultMapper {

    private static final Faker faker = new Faker();
    private NumberFactorResultMapper() {}
    public static HelloWorld.NumberFactorResult toNumberFactorResult(NumberFactorResult projection) {
        return HelloWorld.NumberFactorResult.newBuilder()
                .setValue(projection.getValue())
                .setFactorValue(projection.getFactorValue())
                .setResult(projection.getResult())
                // DoubleValue.of is used to wrap double as specified in the proto file
                .setFactoredValue(DoubleValue.of((double) projection.getValue() / (long)(new Random().nextInt(900) + 101)))
                .setName(faker.name().fullName())
                .build();
    }

    public static RestResponseNumberFactor toNumberFactorResultForRest(NumberFactorResult projection) {
        return RestResponseNumberFactor.builder()
                .value(projection.getValue())
                .factorValue(projection.getFactorValue())
                .result(projection.getResult())
                .factoredValue((double) projection.getValue() / (long)(new Random().nextInt(900) + 101))
                .name(faker.name().fullName())
                .build();
    }
}
