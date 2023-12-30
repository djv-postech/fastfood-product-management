package com.fiap.postech.techchallenge.fastfoodproductmanagement.application.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class ApiError {

    private String message;
    private int status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> fields;

    public ApiError(String message, int statusCode){
        this.message = message;
        this.status = statusCode;
    }
}
