package org.appman.wdbase.exception;

/**
 * Created by Pieter on 10/21/2016.
 */
public class WebdriverTestRunTimeException extends RuntimeException {

    public WebdriverTestRunTimeException() {
        super();
    }

    public WebdriverTestRunTimeException(String message) {
        super(message);
    }

    public WebdriverTestRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebdriverTestRunTimeException(Throwable cause) {
        super(cause);
    }

    protected WebdriverTestRunTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
