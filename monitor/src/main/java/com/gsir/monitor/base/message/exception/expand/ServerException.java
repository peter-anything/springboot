package com.gsir.monitor.base.message.exception.expand;

import com.gsir.monitor.base.message.enums.ResponseCode;
import com.gsir.monitor.base.message.exception.GlobalException;

public class ServerException extends GlobalException {

    public ServerException(String message) {
        super(message, ResponseCode.ERROR.getCode());
    }
}
