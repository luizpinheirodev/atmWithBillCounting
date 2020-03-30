package com.luiz.atm.util;

import com.luiz.atm.exception.WithdrawWrongNumberFormatException;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.function.Predicate;

public class NumberUtil {

    public static int toValidNumber(String number) {
        final Optional<String> maybeValue = Optional.of(number).filter(isStringNotEmpty());
        ;
        final String value = maybeValue.orElse(null);

        try {
            int withdraw = Integer.parseInt(value);
            if (withdraw % 10 == 0) return withdraw;
            throw new WithdrawWrongNumberFormatException("Invalid value. Bills allowed are 10, 20, 50 and 100");
        } catch (NumberFormatException e) {
            throw new WithdrawWrongNumberFormatException("Wrong format");
        }
    }

    private static Predicate<String> isStringNotEmpty() {
        return p -> !StringUtils.isEmpty(p) || !StringUtils.isEmpty(p.trim());
    }


}
