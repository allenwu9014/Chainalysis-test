package com.chainalysis.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// sub data of response from Kraken
public class Result {
    @JsonProperty("XXBTZUSD")
    XXBTZUSD xxbtzusd;

    @JsonProperty("XETHZUSD")
    XETHZUSD xethzusd;

    public XXBTZUSD getXxbtzusd() {
        return xxbtzusd;
    }
    public XETHZUSD getXethzusd() {
        return xethzusd;
    }
}
