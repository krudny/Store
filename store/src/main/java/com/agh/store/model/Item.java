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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Item ID: ").append(itemId).append(", ")
                .append("Name: ").append(name).append(", ")
                .append("Normal Price: ").append(String.format("%.2f", normalPrice)).append(" PLN");

        if (requiredQuantity != null && specialPrice != null) {
            builder.append(", Required Quantity: ").append(requiredQuantity)
                    .append(", Special Price: ").append(String.format("%.2f", specialPrice)).append(" PLN");
        }
        return builder.toString();
    }
}
