package com.agh.store.model;

import lombok.Getter;
import org.springframework.stereotype.Component;


import java.util.Comparator;
import java.util.TreeMap;

@Component
@Getter
public class Cart {
    private TreeMap<Item, Integer> cart = new TreeMap<>(Comparator.comparing(Item::getName));

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
