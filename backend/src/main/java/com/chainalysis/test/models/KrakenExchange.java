package com.chainalysis.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

//@JsonDeserialize(builder = KrakenExchange.Builder.class)

public class KrakenExchange implements Exchange{

    final private String exchangeName = "Kraken";

    private String type;

    private double ask;
    private double bid;

    @JsonProperty("result")
    private Result result;

    private String[] error;



    public KrakenExchange() {}

    public Result getResult() {
        return result;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    //    public KrakenExchange(KrakenExchange.Builder builder) {
//        this.ask = builder.ask;
//        this.bid = builder.bid;
//        this.result = builder.result;
//        this.error = builder.error;
//    }
//
//
//
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
//
//    public static class Builder {
//
//
//
//        private double ask;
//
//        private double bid;
//
//        @JsonProperty("result")
//        private Result result;
//
//        @JsonProperty("error")
//        private String[] error;
//
//        public void setAsk(double ask) {
//            this.ask = ask;
//        }
//
//        public void setBid(double bid) {
//            this.bid = bid;
//        }
//
//        public void setResult(Result result) {this.result = result;}
//
//        public void setError(String[] error) {
//            this.error = error;
//        }
//
//        public KrakenExchange build() {
//            return new KrakenExchange (this);
//        }
//    }




}