package com.heuron.heuroncloud.domain.common.exception;

public class BusinessException extends RuntimeException {
    private final int status;
    private final String message;

    public BusinessException(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

}
