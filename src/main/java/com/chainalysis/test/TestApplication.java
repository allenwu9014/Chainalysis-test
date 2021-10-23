package com.chainalysis.test;

import com.chainalysis.test.extra.*;
import com.chainalysis.test.models.Exchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }



    @Bean
    public RestTemplateJSON getRestTemplateJSON() {
        return new RestTemplateJSON();
    }


    //max heap for the BTC BID
    @Bean(name = "PriorityQueueBTCMax")
    public PriorityQueue<Exchange> getPriorityQueueBTCMax() {

        return new PriorityQueue<Exchange>(
                new Comparator<Exchange>() {
                    @Override
                    public int compare(Exchange e1, Exchange e2) {
                        if (e1.getBid() == e2.getBid()) {
                            return 0;
                        }
                        return e1.getBid() < e2.getBid() ? 1 : -1;
                    }

                });
    }
    //max heap for the ETH BID
    @Bean(name = "PriorityQueueETHMax")
    public PriorityQueue<Exchange> getPriorityQueueETHMax() {

        return new PriorityQueue<Exchange>(
                new Comparator<Exchange>() {
                    @Override
                    public int compare(Exchange e1, Exchange e2) {
                        if (e1.getBid() == e2.getBid()) {
                            return 0;
                        }
                        return e1.getBid() < e2.getBid() ? 1 : -1;
                    }

                });
    }
    //min heap for the BTC ASK
    @Bean(name = "PriorityQueueBTCMin")
    public PriorityQueue<Exchange> getPriorityQueueBTCMin() {
        return new PriorityQueue<Exchange>(new Comparator<Exchange>() {
            @Override
            public int compare(Exchange e1, Exchange e2) {
                if (e1.getAsk() == e2.getAsk()) {
                    return 0;
                }
                return e1.getAsk() < e2.getAsk() ? -1 : 1;
            }
        });
    }
    //min heap for the ETH ASK
    @Bean(name = "PriorityQueueETHMin")
    public PriorityQueue<Exchange> getPriorityQueueETHMin() {
        return new PriorityQueue<Exchange>(new Comparator<Exchange>() {
            @Override
            public int compare(Exchange e1, Exchange e2) {
                if (e1.getAsk() == e2.getAsk()) {
                    return 0;
                }
                return e1.getAsk() < e2.getAsk() ? -1 : 1;
            }
        });
    }

    // list of all ETH exchanges
    @Bean(name = "ETHExchanges")
    public List<Exchange> getETHExchange() {
        return new ArrayList<Exchange>();
    }
    // list of all BTC exchanges
    @Bean(name = "BTCExchanges")
    public List<Exchange> getBTCExchange() {
        return new ArrayList<Exchange>();
    }
}
