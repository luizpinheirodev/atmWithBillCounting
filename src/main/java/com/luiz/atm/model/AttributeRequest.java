package com.luiz.atm.model;

public enum AttributeRequest {
    VALUE("value");

    private String value;

    AttributeRequest(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
