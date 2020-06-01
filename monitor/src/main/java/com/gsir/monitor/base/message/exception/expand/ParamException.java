package com.gsir.monitor.base.message.exception.expand;

import com.gsir.monitor.base.message.enums.ResponseCode;
import com.gsir.monitor.base.message.exception.GlobalException;

public class ParamException extends GlobalException {

    public ParamException(String message){
        super(message, ResponseCode.PARAM_ERROR_CODE.getCode());
    }
}
