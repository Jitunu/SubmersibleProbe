package com.natwest.SubmersibleApplication.exception;

/**
 * Custom exception class for handling probe-related errors.
 * This exception is thrown when an invalid command provide
 */
public class InvalidCommandException extends RuntimeException{

    /**
     * Constructs a new InvalidCommandException with the specified error message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
