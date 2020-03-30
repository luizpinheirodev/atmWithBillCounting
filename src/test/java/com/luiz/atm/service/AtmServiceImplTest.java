package com.luiz.atm.service;

import com.luiz.atm.exception.WithdrawNotEnoughMoneyException;
import com.luiz.atm.model.Bill;
import com.luiz.atm.model.BillDispenser;
import com.luiz.atm.model.dto.ResponseBillDto;
import com.luiz.atm.repository.BillDispenserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

public class AtmServiceImplTest {

    private BillDispenserRepository billDispenserRepository = Mockito.mock(BillDispenserRepository.class);
    private AtmServiceImpl atmService = new AtmServiceImpl(billDispenserRepository);

    @Test
    public void updateValue() {
        int value = 40;
        int amount = 5;
        int billAmount = 6;

        int result = atmService.updateValue(value, amount, billAmount);

        Assert.assertEquals(10, result);
        Assert.assertNotNull(result);
    }

    @Test
    public void createBillLine() {
        int amount = 5;
        int billType = 50;

        String billLine = atmService.createBillLine(amount, billType);

        Assert.assertEquals("5 notas de 50", billLine);
        Assert.assertNotNull(billLine);
    }

    @Test
    public void withdraw_insufficientBills() {
        int value = 1000;

        doReturn(50L).when(this.billDispenserRepository).sumTotalOfBills();

        Assertions.assertThrows(WithdrawNotEnoughMoneyException.class, () -> {
            atmService.withdraw(value);
        });
    }

    @Test
    public void withdraw_80() {
        int value = 80;
        BillDispenser billDispenser = new BillDispenser();
        billDispenser.setQuantity(1000);

        doReturn(1000L).when(this.billDispenserRepository).sumTotalOfBills();
        doReturn(billDispenser).when(this.billDispenserRepository).findBillDispenserByValue(anyInt());

        ResponseBillDto withdraw = atmService.withdraw(value);

        Assert.assertEquals("1 notas de 50", withdraw.getBills().get(0));
        Assert.assertEquals("1 notas de 20", withdraw.getBills().get(1));
        Assert.assertEquals("1 notas de 10", withdraw.getBills().get(2));
    }

    @Test
    public void withdraw_910with5hundredsAvailable() {
        int value = 910;
        BillDispenser billDispenserHundred = new BillDispenser();
        billDispenserHundred.setQuantity(5);
        BillDispenser billDispenserFifty = new BillDispenser();
        billDispenserFifty.setQuantity(10);
        BillDispenser billDispenserTen = new BillDispenser();
        billDispenserTen.setQuantity(5);

        doReturn(1000L).when(this.billDispenserRepository).sumTotalOfBills();
        doReturn(billDispenserHundred).when(this.billDispenserRepository).findBillDispenserByValue(Bill.HUNDRED.getValue());
        doReturn(billDispenserFifty).when(this.billDispenserRepository).findBillDispenserByValue(Bill.FIFTY.getValue());
        doReturn(billDispenserTen).when(this.billDispenserRepository).findBillDispenserByValue(Bill.TEN.getValue());

        ResponseBillDto withdraw = atmService.withdraw(value);

        Assert.assertEquals("5 notas de 100", withdraw.getBills().get(0));
        Assert.assertEquals("8 notas de 50", withdraw.getBills().get(1));
        Assert.assertEquals("1 notas de 10", withdraw.getBills().get(2));
    }

}