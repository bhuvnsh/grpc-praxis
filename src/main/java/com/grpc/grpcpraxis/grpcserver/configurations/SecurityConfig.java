package com.grpc.grpcpraxis.grpcserver.configurations;

import com.grpc.grpcpraxis.grpcserver.interceptors.ApiRestRequestInterceptor;
import com.grpc.grpcpraxis.grpcserver.interceptors.ApiRestResponseInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private ApiRestRequestInterceptor restRequestInterceptor;

    @Autowired
    public ApiRestResponseInterceptor restResponseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(restResponseInterceptor);
        registry.addInterceptor(restRequestInterceptor);
    }
}
