package org.appman.photoblog.core.exception;

/**
 * Created by Pieter on 5/6/2016.
 */
public class ObjectModelValidationException extends AbstractPhotoBlogException {

    public ObjectModelValidationException() {
        super();
    }

    public ObjectModelValidationException(String message) {
        super(message);
    }

    public ObjectModelValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectModelValidationException(Throwable cause) {
        super(cause);
    }

    protected ObjectModelValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
