package com.ems.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ems.util.RequestValidationFailed;
import com.ems.util.RequestValidationFailed.ErrorResponse;

@ControllerAdvice
@RestController
public class ErrorHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();

		List<ErrorResponse> errorDetails = new ArrayList<>();
		for (FieldError fieldError : errors) {
			ErrorResponse error = new ErrorResponse();
			error.setFieldName(fieldError.getField());
			error.setMessage(fieldError.getDefaultMessage());
			errorDetails.add(error);
		}

		RequestValidationFailed req = new RequestValidationFailed();
		req.setErrors(errorDetails);
		return new ResponseEntity<>(req, HttpStatus.BAD_REQUEST);
	}

}
