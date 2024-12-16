package com.agh.store.service;

import com.agh.store.DTO.DiscountRequest;
import com.agh.store.model.DiscountKey;
import com.agh.store.model.DiscountRelations;
import com.agh.store.model.Item;
import com.agh.store.model.StoreProducts;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DiscountService {
    private final DiscountRelations discountRelations;
    private final StoreProducts storeProducts;

    public void addDiscount(@Valid DiscountRequest request) {
        Optional<Item> item1 =  storeProducts.getItemById(request.getItem1Id());
        Optional<Item> item2 =  storeProducts.getItemById(request.getItem2Id());

        if(item1.isEmpty() || item2.isEmpty()) {
            throw new IllegalArgumentException("One of the items does not exist.");
        }

        DiscountKey newDiscount = new DiscountKey(item1.get(), item2.get());

        if(discountRelations.getDiscountRelations().containsKey(newDiscount)) {
            throw new IllegalArgumentException("Discount relation already exists.");
        }

        discountRelations.addDiscountRelation(newDiscount, request.getValue());
    }

    public String showDiscounts() {
        if (discountRelations.getDiscountRelations().isEmpty()) {
            return "No discount relations available.";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Discount Relations:\n");

        for (Map.Entry<DiscountKey, Double> entry : discountRelations.getDiscountRelations().entrySet()) {
            builder.append(entry.getKey().toString())
                    .append("discount Value: ")
                    .append(entry.getValue())
                    .append("\n");
        }
        return builder.toString();
    }
}
