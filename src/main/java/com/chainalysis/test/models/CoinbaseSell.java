package com.chainalysis.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;

// sub data of response of coinbase
public class CoinbaseSell {
    @JsonProperty("data")
    Data data;

    public CoinbaseSell() {

    }

    public Data getData() {
        return data;
    }
}
