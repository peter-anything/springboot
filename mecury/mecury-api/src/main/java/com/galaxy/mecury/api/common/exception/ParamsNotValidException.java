package com.galaxy.mecury.api.common.exception;

import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.stream.Collectors;

@Data
public class ParamsNotValidException extends RuntimeException {
    private static final long serialVersionUID = -6979025550831621453L;

    private BindingResult bindingResult;

    public ParamsNotValidException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
