package com.grpc.grpcpraxis.grpcserver.services.impl;

import com.grpc.grpcpraxis.grpcserver.dto.RestRequest;
import com.grpc.grpcpraxis.grpcserver.dto.RestResponseNumberFactor;
import com.grpc.grpcpraxis.grpcserver.entities.NumberFactorResult;
import com.grpc.grpcpraxis.grpcserver.mapper.NumberFactorResultMapper;
import com.grpc.grpcpraxis.grpcserver.repositories.NumberRepository;
import com.grpc.grpcpraxis.grpcserver.services.NumberFactorResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NumberFactorResultServiceImpl implements NumberFactorResultService {
    @Autowired
    NumberRepository numberRepository;
    @Override
    public List<RestResponseNumberFactor> sayHelloByRest(RestRequest restRequest) {
        List<NumberFactorResult> numberFactorResults = numberRepository.getValue();
        return numberFactorResults.stream()
                .map(this::convertToDto)
                .sorted(Comparator.comparing(RestResponseNumberFactor::getFactorValue).reversed())
                .collect(Collectors.toList());
    }

    private RestResponseNumberFactor convertToDto(NumberFactorResult myProjection) {
        return NumberFactorResultMapper.toNumberFactorResultForRest(myProjection);
    }

}
