package org.example.exception;

public class CommonCommandException extends RuntimeException {

    public CommonCommandException(String message) {
        super(message);
    }

    public CommonCommandException(Exception e) {
        super(e);
    }
}
