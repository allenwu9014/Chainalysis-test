package com.chainalysis.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;


public class CoinbaseExchange implements Exchange{

    final private String exchangeName = "Coinbase";

    private String type;


    @JsonProperty("ask")
    private double ask;
    @JsonProperty("bid")
    private double bid;
    @JsonProperty("link")
    private String link;



    public CoinbaseExchange() {}

    public CoinbaseExchange(double ask, double bid) {
        this.ask = ask;
        this.bid = bid;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public void setAsk(double ask) {
        this.ask = ask;
    }

    @Override
    public void setBid(double bid) {
        this.bid = bid;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public String getType() {
        return type;
    }

    public double getAsk() {
        return ask;
    }

    public double getBid() {
        return bid;
    }


}