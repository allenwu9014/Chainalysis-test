package com.chainalysis.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;

// sub data of response of coinbase
public class CoinbaseBuy {
    @JsonProperty("data")
    Data data;

    public CoinbaseBuy () {

    }
    public Data getData() {
        return data;
    }
}
