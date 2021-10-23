package com.chainalysis.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;

// sub data of response from Kraken
public class XETHZUSD {
    @JsonProperty("a")
    String[] ask;

    @JsonProperty("b")
    String[] bid;

    public String getAsk() {
        return ask[0];
    }

    public String getBid() {
        return bid[0];
    }
}