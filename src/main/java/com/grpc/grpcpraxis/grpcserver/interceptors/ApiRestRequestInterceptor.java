package com.grpc.grpcpraxis.grpcserver.interceptors;

import com.grpc.grpcpraxis.grpcserver.services.LoginService;
import io.grpc.Metadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
@Component
public class ApiRestRequestInterceptor implements HandlerInterceptor {

    private static final Metadata.Key<String> USER_NAME =
            Metadata.Key.of("user", Metadata.ASCII_STRING_MARSHALLER);

    private static final Metadata.Key<String> PASSWORD =
            Metadata.Key.of("password", Metadata.ASCII_STRING_MARSHALLER);

    private static final Metadata.Key<String> AUTHORIZATION =
            Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);

    private final LoginService loginService;

    @Autowired
    public ApiRestRequestInterceptor(final LoginService loginService) {
        this.loginService = loginService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Validating user token");
        String userName = request.getHeader(USER_NAME.name());
        String password = request.getHeader(PASSWORD.name());
        // Check if the Authorization header is present
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            String bearerToken = authorizationHeader.substring(7);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid Authorization header");
            return false;
        }

        // Extract the token from the Authorization header
        String token = authorizationHeader.substring("Bearer ".length()).trim();

        if (!validateUser(userName, password)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid user credentials");
            return false;
        }

        return true;
    }

    private boolean validateUser(String userName, String password) {
        log.info("user: {}", userName);
        return loginService.isValidUser(userName, password);
    }

}

