package com.luiz.atm.model;

public enum Bill {

    HUNDRED("Hundred", 100),
    FIFTY("Fifty", 50),
    TWENTY("Twenty", 20),
    TEN("Ten", 10);

    private String name;
    private Integer value;

    Bill(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }
}
