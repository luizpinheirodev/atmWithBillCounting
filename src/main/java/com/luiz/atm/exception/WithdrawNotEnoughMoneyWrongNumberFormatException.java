package com.luiz.atm.exception;

/**
 * @author Dmitriy Chelyadin
 *
 * Exception that says that there is not enough money on account for withdrawing.
 * Extends from RuntimeException, so that transactions can rollback at the point of exception.
 */
public class WithdrawNotEnoughMoneyWrongNumberFormatException extends WithdrawWrongNumberFormatException {

    public WithdrawNotEnoughMoneyWrongNumberFormatException() {
        super();
    }

    public WithdrawNotEnoughMoneyWrongNumberFormatException(String message) {
        super(message);
    }

    public WithdrawNotEnoughMoneyWrongNumberFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public WithdrawNotEnoughMoneyWrongNumberFormatException(Throwable cause) {
        super(cause);
    }
}
