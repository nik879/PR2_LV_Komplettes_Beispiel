package org.campus02.web;

public class UrlLoaderException extends Exception {
    public UrlLoaderException() {
    }

    public UrlLoaderException(String message) {
        super(message);
    }

    public UrlLoaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public UrlLoaderException(Throwable cause) {
        super(cause);
    }

    public UrlLoaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
