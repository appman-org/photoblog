package org.appman.photoblog.core.exception;

/**
 * Created by Pieter on 5/5/2016.
 */
abstract public class AbstractPhotoBlogException extends Exception{
    public AbstractPhotoBlogException() {
        super();
    }

    public AbstractPhotoBlogException(String message) {
        super(message);
    }

    public AbstractPhotoBlogException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractPhotoBlogException(Throwable cause) {
        super(cause);
    }

    protected AbstractPhotoBlogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
