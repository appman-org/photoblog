package org.appman.photoblog.comments.exception;

import org.appman.photoblog.core.exception.AbstractPhotoBlogException;

/**
 * Created by Pieter on 6/26/2016.
 */
public class CommentsServiceException extends AbstractPhotoBlogException{
    public CommentsServiceException() {
        super();
    }

    public CommentsServiceException(String message) {
        super(message);
    }

    public CommentsServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommentsServiceException(Throwable cause) {
        super(cause);
    }

    protected CommentsServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
