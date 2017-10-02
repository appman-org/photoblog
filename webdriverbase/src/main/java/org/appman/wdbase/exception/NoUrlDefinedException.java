package org.appman.wdbase.exception;

/**
 * Created by Pieter on 10/25/2016.
 */
public class NoUrlDefinedException extends WebdriverTestRunTimeException {
    public NoUrlDefinedException() {
        super();
    }

    public NoUrlDefinedException(String message) {
        super(message);
    }

    public NoUrlDefinedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoUrlDefinedException(Throwable cause) {
        super(cause);
    }

    protected NoUrlDefinedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
