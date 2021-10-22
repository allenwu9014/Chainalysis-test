package com.chainalysis.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CEXExchange implements Exchange{

    final private String exchangeName = "CEX.IO";

    private String type;


    private double ask;

    private double bid;


    public CEXExchange() {}

    public CEXExchange(CEXExchange.Builder builder) {
        this.ask = builder.ask;
        this.bid = builder.bid;
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

    public static class Builder {


        @JsonProperty("ask")
        private double ask;
        @JsonProperty("bid")
        private double bid;

        public void setAsk(double ask) {
            this.ask = ask;
        }

        public void setBid(double bid) {
            this.bid = bid;
        }

        public CEXExchange build() {
            return new CEXExchange (this);
        }
    }
}