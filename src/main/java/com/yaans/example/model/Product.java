package com.yaans.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.enterprise.context.ApplicationScoped;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private int id;
    private String name;
    private boolean isOnSale;

}
