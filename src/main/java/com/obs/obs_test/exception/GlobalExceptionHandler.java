package com.obs.obs_test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(ResourceNotFoundException ex,
            WebRequest request) {
        ApiResponse.ErrorDetails errorDetails = new ApiResponse.ErrorDetails(ex.getMessage(),
                "The resource was not found.");
        ApiResponse<Void> response = new ApiResponse<>("error", errorDetails, HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadRequestException(BadRequestException ex, WebRequest request) {
        ApiResponse.ErrorDetails errorDetails = new ApiResponse.ErrorDetails(ex.getMessage(),
                "Invalid input provided.");
        ApiResponse<Void> response = new ApiResponse<>("error", errorDetails, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGlobalException(Exception ex, WebRequest request) {
        ApiResponse.ErrorDetails errorDetails = new ApiResponse.ErrorDetails("Internal Server Error",
                ex.getMessage());
        ApiResponse<Void> response = new ApiResponse<>("error", errorDetails, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
