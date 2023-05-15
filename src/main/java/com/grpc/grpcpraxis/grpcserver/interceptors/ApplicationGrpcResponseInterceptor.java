package com.grpc.grpcpraxis.grpcserver.interceptors;

import io.grpc.ForwardingServerCall;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationGrpcResponseInterceptor implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> serverCall, Metadata headers, ServerCallHandler<ReqT, RespT> next) {

        // Add CORS headers to the response
        headers.put(Metadata.Key.of("Access-Control-Allow-Origin", Metadata.ASCII_STRING_MARSHALLER), "*");
        headers.put(Metadata.Key.of("Access-Control-Allow-Methods", Metadata.ASCII_STRING_MARSHALLER), "POST, GET, OPTIONS, DELETE");
        headers.put(Metadata.Key.of("Access-Control-Allow-Headers", Metadata.ASCII_STRING_MARSHALLER), "Content-Type, Authorization");

        return next.startCall(
                new ForwardingServerCall.SimpleForwardingServerCall(serverCall) {
                    @Override
                    public void sendMessage(Object message) {
                        log.info("Message sent to client");
                        super.sendMessage(message);
                    }
                },
                headers);
    }
}
