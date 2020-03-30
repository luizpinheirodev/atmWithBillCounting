package com.luiz.atm.exception;

public class WithdrawException extends RuntimeException {

    public WithdrawException() {
        super();
    }

    public WithdrawException(String message) {
        super(message);
    }

    public WithdrawException(String message, Throwable cause) {
        super(message, cause);
    }

    public WithdrawException(Throwable cause) {
        super(cause);
    }
}
