package com.chainalysis.test.models;

public interface Exchange {
    String getExchangeName();
    String getType();
    double getBid();
    double getAsk();
}
