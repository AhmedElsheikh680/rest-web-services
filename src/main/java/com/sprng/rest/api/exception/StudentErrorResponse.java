package com.sprng.rest.api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentErrorResponse {

    private int statusCode;

    private String message;

    private long timestamp;
}
