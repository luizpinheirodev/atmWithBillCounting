package com.luiz.atm.util;

import com.luiz.atm.exception.WithdrawWrongNumberFormatException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NumberUtilTest {

    @Test
    public void toNumber_String() {
        String number = "abc";
        Assertions.assertThrows(WithdrawWrongNumberFormatException.class, () -> {
            NumberUtil.toValidNumber(number);
        });
    }

    @Test
    public void toNumber_EmptyString() {
        String number = "";
        Assertions.assertThrows(WithdrawWrongNumberFormatException.class, () -> {
            NumberUtil.toValidNumber(number);
        });
    }

    @Test
    public void toNumber_InvalidValue() {
        String number = "15";

        Assertions.assertThrows(WithdrawWrongNumberFormatException.class, () -> {
            NumberUtil.toValidNumber(number);
        });
    }

    @Test
    public void toNumber_Success() {
        String number = "50";
        int result = NumberUtil.toValidNumber(number);

        Assert.assertEquals(50, result);
        Assert.assertNotNull(result);
    }

}
