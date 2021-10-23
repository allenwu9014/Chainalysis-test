package com.chainalysis.test.services;


import com.chainalysis.test.models.Exchange;
import com.chainalysis.test.models.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


@Service
public class RecommendationService {

    @Autowired
    @Qualifier("PriorityQueueETHMax")
    private PriorityQueue<Exchange> priorityQueueETHMax;
    @Autowired
    @Qualifier("PriorityQueueBTCMax")
    private PriorityQueue<Exchange> priorityQueueBTCMax;
    @Autowired
    @Qualifier("PriorityQueueETHMin")
    private PriorityQueue<Exchange> priorityQueueETHMin;
    @Autowired
    @Qualifier("PriorityQueueBTCMin")
    private PriorityQueue<Exchange> priorityQueueBTCMin;

    @Autowired
    @Qualifier("BTCExchanges")
    private List<Exchange> exchangesBTC;

    @Autowired
    @Qualifier("ETHExchanges")
    private List<Exchange> exchangesETH;



    // add a exchange data with BTC data and add to PQ
    public boolean addBTC(Exchange exchange) {
        priorityQueueBTCMax.offer(exchange);
        priorityQueueBTCMin.offer(exchange);
        return exchangesBTC.add(exchange);
    }
    // add a exchange data with ETH data and add to PQ
    public boolean addETH(Exchange exchange) {
        priorityQueueETHMax.offer(exchange);
        priorityQueueETHMin.offer(exchange);
        return exchangesETH.add(exchange);
    }


    // ETH ASK recommendation
    private Exchange recommendETHBuy() {

        return priorityQueueETHMin.poll();
    }

    // ETH BID recommendation
    private Exchange recommendETHSell() {

        return priorityQueueETHMax.poll();
    }

    // BTC ASK recommendation
    private Exchange recommendBTCBuy() {


        return priorityQueueBTCMin.poll();
    }
    // BTC BID recommendation
    private Exchange recommendBTCSell() {

        return priorityQueueBTCMax.poll();
    }

    // clear the queue for next Recommendation
    private boolean removeAllPQs() {
        while (!priorityQueueBTCMax.isEmpty()) {
            priorityQueueBTCMax.poll();
        }
        while (!priorityQueueETHMax.isEmpty()) {
            priorityQueueETHMax.poll();
        }
        while (!priorityQueueBTCMin.isEmpty()) {
            priorityQueueBTCMin.poll();
        }
        while (!priorityQueueETHMin.isEmpty()) {
            priorityQueueETHMin.poll();
        }
        return priorityQueueBTCMax.isEmpty()
                && priorityQueueETHMax.isEmpty()
                && priorityQueueBTCMin.isEmpty()
                && priorityQueueETHMin.isEmpty();
    }

    // clear the list for next Recommendation
    private boolean removeAllLists() {
        while (!exchangesBTC.isEmpty()) {
            exchangesBTC.remove(exchangesBTC.size() - 1);
        }
        while (!exchangesETH.isEmpty()) {
            exchangesETH.remove(exchangesETH.size() - 1);
        }
        return exchangesBTC.isEmpty() && exchangesETH.isEmpty();
    }

    // output result
    public Summary getRecommend() {
        Summary summary = new Summary(new ArrayList<>(exchangesBTC),new ArrayList<>(exchangesETH), recommendBTCSell(),
                recommendBTCBuy(), recommendETHSell(), recommendETHBuy());
        removeAllLists();
        removeAllPQs();
        return summary;
    }

}
