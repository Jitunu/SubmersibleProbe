package com.natwest.SubmersibleApplication.exception;

/**
 * Custom exception class for handling probe-related errors.
 * This exception is thrown when the probe encounters an invalid operation,
 * such as moving outside the grid or into an obstacle.
 */
public class ProbeException extends RuntimeException{

    /**
     * Constructs a new ProbeException with the specified error message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public ProbeException(String message) {
        super(message);
    }
}
