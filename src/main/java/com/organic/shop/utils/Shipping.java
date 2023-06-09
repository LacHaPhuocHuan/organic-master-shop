package com.organic.shop.utils;

public enum Shipping {

    FREESHIP(100.0),
    NOTFREESHIP(0.0),
    FIFTYPERCENT(50.0);
    private Double percent;
    Shipping(Double percent) {
        this.percent = percent;
    }

    public Double getPercent() {
        return percent;
    }
}
