package com.grpc.grpcpraxis.grpcserver.listener;

import io.grpc.ForwardingServerCallListener;
import io.grpc.ServerCall;
import org.slf4j.MDC;

import java.util.UUID;

public class RequestGrpcServerCallListener<ReqT> extends ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT> {

    private static final String REQUEST_TRACE_ID = "REQUEST_TRACE_ID";
    private final String traceId = UUID.randomUUID().toString();

    public RequestGrpcServerCallListener(ServerCall.Listener<ReqT> delegate) {
        super(delegate);
    }

        @Override
        public void onMessage(ReqT message) {
        MDC.put(REQUEST_TRACE_ID, traceId);
        try {
            super.onMessage(message);
        } finally {
            MDC.clear();
        }
    }

        @Override
        public void onHalfClose() {
        MDC.put(REQUEST_TRACE_ID, traceId);
        try {
            super.onHalfClose();
        } finally {
            MDC.clear();
        }
    }

        @Override
        public void onCancel() {
        MDC.put(REQUEST_TRACE_ID, traceId);
        try {
            super.onCancel();
        } finally {
            MDC.clear();
        }
    }

        @Override
        public void onComplete() {
        MDC.put(REQUEST_TRACE_ID, traceId);
        try {
            super.onComplete();
        } finally {
            MDC.clear();
        }
    }

        @Override
        public void onReady() {
        MDC.put(REQUEST_TRACE_ID, traceId);
        try {
            super.onReady();
        } finally {
            MDC.clear();
        }
    }

}
