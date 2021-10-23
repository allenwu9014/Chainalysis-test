package com.chainalysis.test.models;

// interface for the all kinds of Exchanges
public interface Exchange {

    String getExchangeName();
    String getType();
    double getBid();
    double getAsk();
    void setType(String type);
    void setAsk(double ask);
    void setBid(double bid);



}
