package com.grpc.grpcpraxis.grpcserver.utility;

import com.grpc.grpcpraxis.grpcserver.listener.RequestGrpcServerCallListener;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;

public class RequestGrpcCallHandler<R, S> implements ServerCallHandler<R, S> {

    private final ServerCallHandler handler;
    public RequestGrpcCallHandler(final ServerCallHandler handler) {
        this.handler = handler;
    }

    @Override
    public ServerCall.Listener<R> startCall(ServerCall<R, S> call, Metadata headers) {
        return new RequestGrpcServerCallListener<R>(handler.startCall(call, headers)) ;
    }
}
