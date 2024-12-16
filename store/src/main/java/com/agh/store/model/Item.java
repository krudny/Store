package com.agh.store.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicLong;

@AllArgsConstructor
@Getter
@Builder
@ToString
public class Item {
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    private long itemId;
    private String name;
    private float normalPrice;
    private Integer requiredQuantity;
    private Float specialPrice;

    public static class ItemBuilder {
        private long itemId = ID_GENERATOR.getAndIncrement();
    }
}
