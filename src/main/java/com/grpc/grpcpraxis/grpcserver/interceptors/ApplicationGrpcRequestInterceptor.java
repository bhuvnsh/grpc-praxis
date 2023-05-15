package com.grpc.grpcpraxis.grpcserver.interceptors;

import com.grpc.grpcpraxis.grpcserver.services.LoginService;
import com.grpc.grpcpraxis.grpcserver.utility.RequestGrpcCallHandler;
import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationGrpcRequestInterceptor implements ServerInterceptor {
    private static final Metadata.Key<String> USER_NAME =
            Metadata.Key.of("user", Metadata.ASCII_STRING_MARSHALLER);

    private static final Metadata.Key<String> PASSWORD =
            Metadata.Key.of("password", Metadata.ASCII_STRING_MARSHALLER);

    private static final Metadata.Key<String> AUTHORIZATION =
            Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);

    private final LoginService loginService;

    @Autowired
    public ApplicationGrpcRequestInterceptor(final LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            Metadata headers,
            ServerCallHandler<ReqT, RespT> next) {

        log.info("Validating user token");
        String userName = headers.get(USER_NAME);
        String password = headers.get(PASSWORD);

// Check if the Authorization header is present
        String authorizationHeader = headers.get(AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            call.close(Status.UNAUTHENTICATED.withDescription("Invalid Authorization header"), headers);
            return new ServerCall.Listener<ReqT>() {};
        }

// Extract the token from the Authorization header
        String token = authorizationHeader.substring("Bearer ".length()).trim();

        if (!validateUser(userName, password)) {
            call.close(Status.UNAUTHENTICATED.withDescription("Invalid user credentials"), headers);
            return new ServerCall.Listener<ReqT>() {};
        }

        return Contexts.interceptCall(Context.current(), call, headers, new RequestGrpcCallHandler<ReqT, RespT>(next));

    }

    private boolean validateUser(String userName, String password) {
        log.info("user: {}", userName);
        return loginService.isValidUser(userName, password);
    }

}
