package com.chainalysis.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Result {
    @JsonProperty("XXBTZUSD")
    XXBTZUSD xxbtzusd;

    public XXBTZUSD getXxbtzusd() {
        return xxbtzusd;
    }
}
