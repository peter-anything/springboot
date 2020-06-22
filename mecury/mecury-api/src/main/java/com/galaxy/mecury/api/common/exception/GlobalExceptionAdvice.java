package com.galaxy.mecury.api.common.exception;

import com.galaxy.mecury.api.common.ResponseCode;
import com.galaxy.mecury.api.common.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseData defaultException(HttpServletRequest request, Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        log.error(sw.toString());
        return new ResponseData(ResponseCode.EXCEPTION.getCode(), ResponseCode.EXCEPTION.getMessage(), null);
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    public ResponseData notFoundException(HttpServletRequest request, Exception e) {
        log.error(e.getStackTrace().toString());
        return new ResponseData(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage(), null);
    }

    @ExceptionHandler(value = ParamsNotValidException.class)
    @ResponseBody
    public ResponseData paramsNotValidException(HttpServletRequest request, ParamsNotValidException e) {
        log.error(e.getStackTrace().toString());
        BindingResult bindingResult = e.getBindingResult();
        List<String> result = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseData(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage(), result);
    }
}
