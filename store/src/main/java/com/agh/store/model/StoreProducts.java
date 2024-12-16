package com.agh.store.model;


import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
}
