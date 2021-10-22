package com.chainalysis.test.controllers;

import com.chainalysis.test.extra.RestTemplateJSON;
import com.chainalysis.test.models.CEXExchange;
import com.chainalysis.test.models.GeminiExchange;
import com.chainalysis.test.models.KrakenExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrentPricesController {

//    @Autowired
//    private RestTemplate restTemplate;


    @Autowired
    private RestTemplateJSON restTemplateJSON;

//    @GetMapping(value = "/recommend")
//    public GeminiExchange getRecommendation() {
//
//       GeminiExchange geminiExchange = restTemplate.getForObject("https://api.gemini.com/v1/pubticker/BTCUSD", GeminiExchange.class);
//        geminiExchange.setType("BTC");
//       return geminiExchange;
//    }


    @GetMapping(value = "/recommend")
    public KrakenExchange getRecommendation() {

        KrakenExchange krakenExchange = restTemplateJSON.getForObject("https://api.kraken.com/0/public/Ticker?pair=BTCUSD", KrakenExchange.class);
        krakenExchange.setType("BTC");
       krakenExchange.setAsk((Double.valueOf(krakenExchange.getResult().getXxbtzusd().getAsk())));
        krakenExchange.setBid((Double.valueOf(krakenExchange.getResult().getXxbtzusd().getBid())));
        return krakenExchange;
    }

//    @GetMapping(value = "/recommend")
//    public CEXExchange getRecommendation() {
//
//        //for accessing cex.io wiht 403 Forbidden
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("User-agent", "SomeUserAgent");
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
//
//
//        CEXExchange cexExchange = restTemplateJSON.exchange("https://cex.io/api/ticker/BTC/USD", HttpMethod.GET, entity, CEXExchange.class).getBody();
//        cexExchange.setType("BTC");
//       return cexExchange;
//    }
}
