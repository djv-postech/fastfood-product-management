package com.fiap.postech.techchallenge.fastfoodproductmanagement.application.config;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception.ProdutoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {ProdutoNotFoundException.class})
    protected ResponseEntity<ApiError> handlerEntityNotFound(ProdutoNotFoundException ex) {
        ApiError error = new ApiError(ex.getMessage(),HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<ApiError> handlerMercadoPagoQRCodeException(IllegalArgumentException ex) {
        ApiError error = new ApiError(ex.getMessage(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
