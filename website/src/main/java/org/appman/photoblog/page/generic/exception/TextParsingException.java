package org.appman.photoblog.page.generic.exception;

import org.appman.photoblog.core.exception.ServiceLayerException;

/**
 * Created by Pieter on 6/23/2016.
 */
public class TextParsingException extends ServiceLayerException{
    public TextParsingException() {
        super();
    }

    public TextParsingException(String message) {
        super(message);
    }

    public TextParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextParsingException(Throwable cause) {
        super(cause);
    }

    protected TextParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
