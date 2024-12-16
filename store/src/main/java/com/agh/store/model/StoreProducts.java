package com.agh.store.model;


import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Component
public class StoreProducts {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(long itemId) {
        this.items.removeIf(item -> item.getItemId() == itemId);
    }

    public boolean itemExists(long itemId) {
        return this.items.stream().anyMatch(item -> item.getItemId() == itemId);
    }

    public Optional<Item> getItemById(long itemId) {
        return this.items.stream().filter(item -> item.getItemId() == itemId).findFirst();
    }
}
