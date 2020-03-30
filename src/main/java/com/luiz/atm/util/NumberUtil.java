package com.luiz.atm.util;

import com.luiz.atm.exception.WithdrawException;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.function.Predicate;

public class NumberUtil {

    public static int toNumber(String number){
        final Optional<String> maybeValue = Optional.of(number).filter(isStringNotEmpty());;
        final String value = maybeValue.orElse(null);

        try{
            int withdraw = Integer.parseInt(value);
            return (withdraw % 10 == 0) ? withdraw : 0;
        }catch (NumberFormatException e){
            throw new WithdrawException("Wrong format");
        }
    }

    private static Predicate<String> isStringNotEmpty() {
        return p -> !StringUtils.isEmpty(p) || !StringUtils.isEmpty(p.trim());
    }


}
