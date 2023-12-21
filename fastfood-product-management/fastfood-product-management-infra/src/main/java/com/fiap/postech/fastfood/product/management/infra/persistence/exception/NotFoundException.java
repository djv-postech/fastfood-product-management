package com.fiap.postech.fastfood.product.management.infra.persistence.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
