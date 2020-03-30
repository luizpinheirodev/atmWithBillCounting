package com.luiz.atm.service;

import com.luiz.atm.exception.WithdrawNotEnoughMoneyWrongNumberFormatException;
import com.luiz.atm.model.BillDispenser;
import com.luiz.atm.model.dto.ResponseBillDto;
import com.luiz.atm.repository.BillDispenserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.luiz.atm.model.Bill.*;

@Service
public class AtmServiceImpl implements AtmService {

    private BillDispenserRepository billDispenserRepository;

    @Autowired
    public AtmServiceImpl(BillDispenserRepository billDispenserRepository) {
        this.billDispenserRepository = billDispenserRepository;
    }

    @Override
    public ResponseBillDto withdraw(int value) {
        // Validate if ATM has enough money
        Long totalAvailable = billDispenserRepository.sumTotalOfBills();
        if (totalAvailable < value) {
            throw new WithdrawNotEnoughMoneyWrongNumberFormatException("Insufficient quantity of bills");
        }

        ResponseBillDto billDto = new ResponseBillDto();
        List<String> bills = new ArrayList<>();

        // Bill type available
        final int[] billTypeOrdered = {HUNDRED.getValue(), FIFTY.getValue(), TWENTY.getValue(), TEN.getValue()};

        for (int i = 0; i < billTypeOrdered.length; i++) {
            int billAmount = billTypeOrdered[i];
            int amount = (int) Math.floor(value / billAmount);
            BillDispenser billDispenser = billDispenserRepository.findBillDispenserByValue(billAmount);
            if (billDispenser != null && amount > 0) {
                if (amount <= billDispenser.getQuantity()) {
                    value = updateValue(value, amount, billAmount);
                    bills.add(createBillLine(amount, billTypeOrdered[i]));
                    billDispenser.setQuantity(billDispenser.getQuantity() - amount);
                    billDispenserRepository.save(billDispenser);
                } else {
                    // there is not enough bills
                    value = updateValue(value, billDispenser.getQuantity(), billAmount);
                    bills.add(createBillLine(billDispenser.getQuantity(), billTypeOrdered[i]));
                    billDispenser.setQuantity(0);
                    billDispenserRepository.save(billDispenser);
                }
            }
        }
        billDto.setBills(bills);
        return billDto;
    }

    protected String createBillLine(int amount, int billType) {
        return new StringBuilder().append(amount).append(" notas de ").append(billType).toString();
    }

    protected int updateValue(int value, int amount, int billAmount) {
        return value - (amount * billAmount);
    }

}