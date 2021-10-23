package com.chainalysis.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;



public class KrakenExchange implements Exchange{

    final private String exchangeName = "Kraken";

    private String type;


    private double ask;
    private double bid;

    @JsonProperty("link")
    private String link;

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

    @Override
    public void setLink(String link) {
        this.link = link;
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




