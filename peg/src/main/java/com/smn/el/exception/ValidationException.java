package com.smn.el.exception;

public class ValidationException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final ErrorCodes error;

    public ValidationException() {
        super();
        this.error = ErrorCodes.INTERNAL_ERROR;
    }

    public ValidationException(String message) {
        super(message);
        this.error = ErrorCodes.INTERNAL_ERROR;
    }

    public ValidationException(ErrorCodes error) {
        super(error.getDescription());
        this.error = error;
    }

    public ValidationException(String message, ErrorCodes error) {
        super(message);
        this.error = error;
    }

    public ValidationException(ErrorCodes error, Throwable cause) {
        super(cause);
        this.error = error;
    }

    public ValidationException(String message, ErrorCodes error, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public ErrorCodes getError() {
        return error;
    }

}

