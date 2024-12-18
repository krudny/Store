package com.agh.store.model;

import lombok.Getter;
import org.springframework.stereotype.Component;


import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Component
public class DiscountRelations {
    private final Map<DiscountKey, Double> discountRelations = new LinkedHashMap<>();

    public void addDiscountRelation(DiscountKey newDiscount, Double value) {
        discountRelations.put(newDiscount, value);
    }
}
