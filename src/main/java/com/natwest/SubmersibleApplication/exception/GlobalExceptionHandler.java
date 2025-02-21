package com.natwest.SubmersibleApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * GlobalExceptionHandler handles exceptions across the entire application.
 * It provides centralized exception handling for different types of errors,
 * ensuring proper HTTP responses are returned to the client.
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles custom ProbeException and returns an internal server error response.
     *
     * @param ex the ProbeException instance
     * @return ResponseEntity containing an error message with HTTP status 500 (Internal Server Error)
     */
    @ExceptionHandler(ProbeException.class)
    public ResponseEntity<ErrorResponse> handleProbeException(ProbeException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles custom ProbeNotInitializedException and returns an internal server error response.
     *
     * @param ex the ProbeNotInitializedException instance
     * @return ResponseEntity containing an error message with HTTP status 500 (Internal Server Error)
     */
    @ExceptionHandler(ProbeNotInitializedException.class)
    public ResponseEntity<ErrorResponse> handleProbeNotInitializedException(ProbeNotInitializedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles custom InvalidCommandException and returns an internal server error response.
     *
     * @param ex the InvalidCommandException instance
     * @return ResponseEntity containing an error message with HTTP status 400 (Bad Request)
     */
    @ExceptionHandler(InvalidCommandException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCommandException(InvalidCommandException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles IllegalArgumentException, typically thrown for invalid input parameters,
     * and returns a bad request response.
     *
     * @param ex the IllegalArgumentException instance
     * @return ResponseEntity containing an error message with HTTP status 400 (Bad Request)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles IllegalStateException, typically thrown when an operation is attempted
     * in an incorrect state, and returns an internal server error response.
     *
     * @param ex the IllegalStateException instance
     * @return ResponseEntity containing an error message with HTTP status 500 (Internal Server Error)
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles any generic exceptions and returns an internal server error response.
     *
     * @param ex the generic Exception instance
     * @return ResponseEntity containing an error message with HTTP status 500 (Internal Server Error)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
