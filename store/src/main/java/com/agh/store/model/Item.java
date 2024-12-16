package com.agh.store.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Item {
    private long itemId;
    private String name;
    private float normalPrice;
    private Integer requiredQuantity;
    private float specialPrice;
}
