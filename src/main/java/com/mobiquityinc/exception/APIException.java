package com.mobiquityinc.exception;

/**
 * <code>APIException</code> is thrown if incorrect parameters are being passed.
 */
public class APIException extends RuntimeException {

    /**
     * constructor with message
     *
     * @param _message message the exception message
     */
    public APIException(String _message) {
        super(_message);
    }

    /**
     * constructor with  message and cause
     *
     * @param _message message the exception message
     * @param _cause   cause
     */
    public APIException(String _message, Throwable _cause) {
        super(_message, _cause);
    }
}

