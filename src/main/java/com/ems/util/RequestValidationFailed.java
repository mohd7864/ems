package com.ems.util;

import java.util.List;

import lombok.Data;

@Data
public class RequestValidationFailed {
	
	private List<ErrorResponse> errors;
	
	@Data
    public static class ErrorResponse {
        private String fieldName;
        private String message;
    }

}
