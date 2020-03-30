package com.luiz.atm.exception;

public class WithdrawWrongNumberFormatException extends RuntimeException {

    public WithdrawWrongNumberFormatException() {
        super();
    }

    public WithdrawWrongNumberFormatException(String message) {
        super(message);
    }

    public WithdrawWrongNumberFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public WithdrawWrongNumberFormatException(Throwable cause) {
        super(cause);
    }
}
