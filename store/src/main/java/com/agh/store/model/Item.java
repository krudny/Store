package com.agh.store.model;

import lombok.*;

import java.util.concurrent.atomic.AtomicLong;

@AllArgsConstructor
@Getter
@Builder
@ToString
public class Item {
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    private Long itemId;
    private String name;
    private Double normalPrice;
    private Integer requiredQuantity;
    private Double specialPrice;

    public Item(Long itemId, String name, Double normalPrice) {
        this.itemId = itemId;
        this.name = name;
        this.normalPrice = normalPrice;
    }

    public static class ItemBuilder {
        private long itemId = ID_GENERATOR.getAndIncrement();
    }
}
