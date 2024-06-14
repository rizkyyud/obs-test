package com.obs.obs_test.exception;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private String status;
    private T data;
    private String message;
    private int code;
    private ErrorDetails error;

    public ApiResponse(String status, T data, String message, int code) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public ApiResponse(String status, ErrorDetails error, int code) {
        this.status = status;
        this.error = error;
        this.code = code;
    }

    @Data
    public static class ErrorDetails {
        private String message;
        private String details;

        public ErrorDetails(String message, String details) {
            this.message = message;
            this.details = details;
        }
    }
}
