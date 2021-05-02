package org.campus02.web;

public class CacheMissException extends Exception {
    public CacheMissException() {
    }

    public CacheMissException(String message) {
        super(message);
    }

    public CacheMissException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheMissException(Throwable cause) {
        super(cause);
    }

    public CacheMissException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
