package com.grpc.grpcpraxis.grpcserver.configurations;

import com.grpc.grpcpraxis.grpcserver.interceptors.ApplicationGrpcRequestInterceptor;
import com.grpc.grpcpraxis.grpcserver.interceptors.ApplicationGrpcResponseInterceptor;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.Executors;


@Configuration
public class GrpcServerConfiguration {

    private final ApplicationContext context;
    private final ApplicationGrpcRequestInterceptor requestInterceptor;
    private final ApplicationGrpcResponseInterceptor responseInterceptor;

    private ServerBuilder<?> serverBuilder;

    @Autowired
    public GrpcServerConfiguration(ApplicationContext context, ApplicationGrpcRequestInterceptor requestInterceptor,
                                   ApplicationGrpcResponseInterceptor responseInterceptor) {
        this.context = context;
        this.requestInterceptor = requestInterceptor;
        this.responseInterceptor = responseInterceptor;
    }

    public ServerBuilder<?> grpcServerBuilder() {
        return ServerBuilder.forPort(8090)
                .maxInboundMessageSize(1024 * 1024) // Set the maximum message size to 1 MB
                .executor(Executors.newFixedThreadPool(4)); // Set the thread pool size to 4
    }
    @PostConstruct
    public void addServicesToGrpcServer() {
        this.serverBuilder = grpcServerBuilder();

        Map<String, Object> grpcServiceBeans = grpcServiceDiscovery().discoverGrpcServices();
        for (Object serviceBean : grpcServiceBeans.values()) {
            serverBuilder.addService((BindableService) serviceBean);
        }

        serverBuilder.intercept(requestInterceptor);
        serverBuilder.intercept(responseInterceptor);
    }

    public GrpcServiceDiscovery grpcServiceDiscovery() {
        return new GrpcServiceDiscovery(context);
    }
    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public Server server() {
        return serverBuilder.build();
    }


}


