package com.chainalysis.test.controllers;

import com.chainalysis.test.extra.RestTemplateJSON;
import com.chainalysis.test.models.*;
import com.chainalysis.test.services.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CurrentPricesController {

    @Autowired
    private RecommendationService recommendationService;


    @Autowired
    private RestTemplateJSON restTemplateJSON;

    @GetMapping(value = "/recommend")
    public Summary getRecommendation() {




        //Kraken
        KrakenExchange krakenExchangeBTC = restTemplateJSON.getForObject("https://api.kraken.com/0/public/Ticker?pair=BTCUSD", KrakenExchange.class);
        krakenExchangeBTC.setType("BTC");
        krakenExchangeBTC.setAsk((Double.valueOf(krakenExchangeBTC.getResult().getXxbtzusd().getAsk())));
        krakenExchangeBTC.setBid((Double.valueOf(krakenExchangeBTC.getResult().getXxbtzusd().getBid())));
        krakenExchangeBTC.setLink("https://kraken.com");
        recommendationService.addBTC(krakenExchangeBTC);


        KrakenExchange  krakenExchangeETH = restTemplateJSON.getForObject("https://api.kraken.com/0/public/Ticker?pair=ETHUSD", KrakenExchange.class);
        krakenExchangeETH.setType("ETH");
        krakenExchangeETH.setAsk((Double.valueOf(krakenExchangeETH.getResult().getXethzusd().getAsk())));
        krakenExchangeETH.setBid((Double.valueOf(krakenExchangeETH.getResult().getXethzusd().getBid())));
        krakenExchangeETH.setLink("https://kraken.com");
        recommendationService.addETH(krakenExchangeETH);





      //  CEX.IO
      // for accessing cex.io wiht 403 Forbidden
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-agent", "SomeUserAgent");
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<CEXExchange> cexBTCTmp = restTemplateJSON.exchange("https://cex.io/api/ticker/BTC/USD", HttpMethod.GET, entity, CEXExchange.class);
        CEXExchange cexExchangeBTC = cexBTCTmp.getBody();
        cexExchangeBTC.setType("BTC");
        cexExchangeBTC.setLink("https://cex.io");
        recommendationService.addBTC(cexExchangeBTC);

        ResponseEntity<CEXExchange> cexETHTmp = restTemplateJSON.exchange("https://cex.io/api/ticker/ETH/USD", HttpMethod.GET, entity, CEXExchange.class);
        CEXExchange cexExchangeETH = cexETHTmp.getBody();
        cexExchangeETH.setType("ETH");
        cexExchangeETH.setLink("https://cex.io");
        recommendationService.addETH(cexExchangeETH);



        //Coinbase
        CoinbaseSell coinbaseSellBTC = restTemplateJSON.getForObject("https://api.coinbase.com/v2/prices/BTC-USD/sell", CoinbaseSell.class);
        CoinbaseBuy coinbaseBuyBTC = restTemplateJSON.getForObject("https://api.coinbase.com/v2/prices/BTC-USD/buy", CoinbaseBuy.class);
        Exchange  coinbaseExchangeBTC = new CoinbaseExchange(Double.valueOf(coinbaseBuyBTC.getData().getAmount()), Double.valueOf(coinbaseSellBTC.getData().getAmount()));
        coinbaseExchangeBTC.setType("BTC");
        coinbaseExchangeBTC.setLink("https://coinbase.com");
        recommendationService.addBTC(coinbaseExchangeBTC);


        CoinbaseSell coinbaseSellETH = restTemplateJSON.getForObject("https://api.coinbase.com/v2/prices/ETH-USD/sell", CoinbaseSell.class);
        CoinbaseBuy coinbaseBuyETH = restTemplateJSON.getForObject("https://api.coinbase.com/v2/prices/ETH-USD/buy", CoinbaseBuy.class);
        Exchange  coinbaseExchangeETH= new CoinbaseExchange(Double.valueOf(coinbaseBuyETH.getData().getAmount()), Double.valueOf(coinbaseSellETH.getData().getAmount()));
        coinbaseExchangeETH.setType("ETH");
        coinbaseExchangeETH.setLink("https://coinbase.com");
        recommendationService.addETH(coinbaseExchangeETH);


        // Gemini
        Exchange geminiExchangeBTC = restTemplateJSON.getForObject("https://api.gemini.com/v1/pubticker/BTCUSD", GeminiExchange.class);
        geminiExchangeBTC.setType("BTC");
        geminiExchangeBTC.setLink("https://gemini.com");
        recommendationService.addBTC(geminiExchangeBTC);

        Exchange  geminiExchangeETH = restTemplateJSON.getForObject("https://api.gemini.com/v1/pubticker/ETHUSD", GeminiExchange.class);
        geminiExchangeETH.setType("ETH");
        geminiExchangeETH.setLink("https://gemini.com");
        recommendationService.addETH(geminiExchangeETH);

       return recommendationService.getRecommend();
    }



}
