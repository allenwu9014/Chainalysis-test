package com.chainalysis.test.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// response structure for /recommend call
public class Summary {

    @JsonProperty("exchangeBTCList")
    List<Exchange> exchangeBTCList;
    @JsonProperty("exchangeETHList")
    List<Exchange> exchangesETHList;
    @JsonProperty("recommendBTCSell")
    Exchange recommendBTCSell;
    @JsonProperty("recommendBTCBuy")
    Exchange recommendBTCBuy;
    @JsonProperty("recommendETHSell")
    Exchange recommendETHSell;
    @JsonProperty("recommendETHBuy")
    Exchange recommendETHBuy;

    public Summary(List<Exchange> exchangeBTCList, List<Exchange> exchangesETHList,
                   Exchange recommendSell, Exchange recommendBuy,
                   Exchange recommendETHSell, Exchange recommendETHBuy
                   ) {
        this.exchangeBTCList = exchangeBTCList;
        this.exchangesETHList = exchangesETHList;
        this.recommendBTCSell = recommendSell;
        this.recommendBTCBuy = recommendBuy;
        this.recommendETHSell = recommendETHSell;
        this.recommendETHBuy = recommendETHBuy;
    }


}

