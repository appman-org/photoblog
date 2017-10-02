package org.appman.photoblog.core.exception;

/**
 * Created by Pieter on 5/6/2016.
 */
abstract public class ServiceLayerException extends AbstractPhotoBlogException{

    public ServiceLayerException() {
        super();
    }

    public ServiceLayerException(String message) {
        super(message);
    }

    public ServiceLayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceLayerException(Throwable cause) {
        super(cause);
    }

    protected ServiceLayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
