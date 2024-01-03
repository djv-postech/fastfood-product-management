package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception;

public class ProdutoNotFoundException extends RuntimeException {
    public ProdutoNotFoundException(String message) {
        super(message);
    }
}
