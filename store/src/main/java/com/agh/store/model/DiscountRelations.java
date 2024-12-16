package com.agh.store.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Getter
@Component
public class DiscountRelations {
    private final HashMap<DiscountKey, Double> discountRelations = new HashMap<>();

    public void addDiscountRelation(DiscountKey newDiscount, Double value) {
        discountRelations.put(newDiscount, value);
    }
}
