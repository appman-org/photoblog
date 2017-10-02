package org.appman.photoblog.core.service;

import org.appman.photoblog.core.exception.ServiceLayerException;

/**
 * Created by Pieter on 5/6/2016.
 */
public class FileReaderServiceException extends ServiceLayerException {


    public FileReaderServiceException() {
        super();
    }

    public FileReaderServiceException(String message) {
        super(message);
    }

    public FileReaderServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileReaderServiceException(Throwable cause) {
        super(cause);
    }

    protected FileReaderServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
