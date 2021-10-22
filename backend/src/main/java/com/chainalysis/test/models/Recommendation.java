package com.chainalysis.test.models;

import java.util.List;

public class Recommendation {
    String exhangeName;
    double sellPrice;
    double buyPrice;

    List<Exchange> priceSource;
}
