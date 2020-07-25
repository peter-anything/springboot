package com.gsir.monitor.base.message.exception;

import com.gsir.monitor.base.message.enums.ResponseCode;

public class WeathertopRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -6370612186038915645L;

    private final ResponseCode response;

    public WeathertopRuntimeException(ResponseCode response) {
        this.response = response;
    }

    public ResponseCode getResponse() {
        return response;
    }
}
