package com.luiz.atm.util;

import com.luiz.atm.exception.WithdrawException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NumberUtilTest {

    @Test
    public void toNumber_String() {
        String number = "abc";
        Assertions.assertThrows(WithdrawException.class, () -> {
            NumberUtil.toNumber(number);
        });
    }

    @Test
    public void toNumber_EmptyString() {
        String number = "";
        Assertions.assertThrows(WithdrawException.class, () -> {
            NumberUtil.toNumber(number);
        });
    }

    @Test
    public void toNumber_InvalidValueReturn0() {
        String number = "15";

        int result = NumberUtil.toNumber(number);

        Assert.assertEquals(0, result);
        Assert.assertNotNull(result);
    }

    @Test
    public void toNumber_Success() {
        String number = "50";
        int result = NumberUtil.toNumber(number);

        Assert.assertEquals(50, result);
        Assert.assertNotNull(result);
    }

}
