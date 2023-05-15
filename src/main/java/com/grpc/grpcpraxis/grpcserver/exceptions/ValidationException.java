package com.grpc.grpcpraxis.grpcserver.exceptions;

import org.springframework.core.NestedRuntimeException;

public class ValidationException extends NestedRuntimeException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}

