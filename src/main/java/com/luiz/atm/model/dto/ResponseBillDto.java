package com.luiz.atm.model.dto;

import java.util.List;

public class ResponseBillDto {

    private List<String> bills;

    public ResponseBillDto() {
    }

    public List<String> getBills() {
        return bills;
    }

    public void setBills(List<String> bills) {
        this.bills = bills;
    }
}
