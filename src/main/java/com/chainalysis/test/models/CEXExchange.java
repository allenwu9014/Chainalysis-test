package com.chainalysis.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = CEXExchange.Builder.class)
public class CEXExchange implements Exchange{

    final private String exchangeName = "CEX.IO";

    private String type;


    private double ask;


    private double bid;

    @JsonProperty("link")
    private String link;

    public CEXExchange() {}

    public CEXExchange(CEXExchange.Builder builder) {
        this.ask = builder.ask;
        this.bid = builder.bid;
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

    public void setLink(String link) {
        this.link = link;
    }
    // initiate the model by builder()
    public static class Builder {


        @JsonProperty("ask")
        private Double ask;
        @JsonProperty("bid")
        private Double bid;

        @JsonProperty("link")
        private String link;



        public void setAsk(Double ask) {
            this.ask = ask;
        }

        public void setBid(Double bid) {
            this.bid = bid;
        }

        public CEXExchange build() {
            return new CEXExchange (this);
        }
    }
}