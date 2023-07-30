package com.example.run_scheduler.dto;

import lombok.Data;

@Data
public class ValidationErrorDetails {

    private String field;
    private String message;

    public ValidationErrorDetails(String field, String message) {
        this.field = field;
        this.message = message;
    }

}
