package com.sxalter.library.loginRegister.exception;

public class LoginException extends RuntimeException {

    private Integer code;

    private String message;

    public LoginException(String message) {
        super(message);
        this.code = 500;
        this.message = message;
    }

    public LoginException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public LoginException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
