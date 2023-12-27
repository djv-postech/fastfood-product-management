package com.fiap.postech.techchallenge.fastfoodproductmanagement.infra.persistence.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
