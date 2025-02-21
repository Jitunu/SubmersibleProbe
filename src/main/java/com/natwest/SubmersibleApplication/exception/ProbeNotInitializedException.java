package com.natwest.SubmersibleApplication.exception;

/**
 * Custom exception class for handling probe-related errors.
 * This exception is thrown when the probe not initialized
 */
public class ProbeNotInitializedException extends RuntimeException{

    /**
     * Constructs a new ProbeNotInitializedException with the specified error message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public ProbeNotInitializedException(String message) {
        super(message);
    }
}
