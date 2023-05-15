package com.grpc.grpcpraxis.grpcserver.configurations;

import org.lognet.springboot.grpc.GRpcService;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class GrpcServiceDiscovery {

    private final ApplicationContext context;

    public GrpcServiceDiscovery(ApplicationContext context) {
        this.context = context;
    }

    public Map<String, Object> discoverGrpcServices() {
        return context.getBeansWithAnnotation(GRpcService.class);
    }
}
