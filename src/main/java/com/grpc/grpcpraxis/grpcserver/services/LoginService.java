package com.grpc.grpcpraxis.grpcserver.services;

public interface LoginService {
    boolean isValidUser(String userName, String password);
}
