package com.grpc.grpcpraxis.grpcserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication//(exclude = { org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class, org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration.class, org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration.class, org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration.class })
public class GrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(GrpcServer.class, args);
        /*ServerBuilder grpcServerBuilder = context.getBean(ServerBuilder.class);
        final Server server = grpcServerBuilder.build();

        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            server.shutdown();
            System.err.println("*** server shut down");
        }));*/
    }
}
