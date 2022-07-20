package com.yaans.example.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Product {

    private int id;
    private String name;
    private boolean isOnSale;

}
