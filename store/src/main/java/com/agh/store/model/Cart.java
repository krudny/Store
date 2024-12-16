package com.agh.store.model;

import lombok.Getter;
import org.springframework.stereotype.Component;


import java.util.HashMap;

@Component
@Getter
public class Cart {
    private HashMap<Item, Integer> cart = new HashMap<>();

    public void addItem(Item item, int quantity) {
        if (cart.containsKey(item)) {
            cart.put(item, cart.get(item) + quantity);
        } else {
            cart.put(item, quantity);
        }
    }

    public void removeItem(Item item) {
        cart.remove(item);
    }
}
