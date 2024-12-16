package com.agh.store.service;

import com.agh.store.DTO.ItemRequest;
import com.agh.store.model.Item;
import com.agh.store.model.StoreProducts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class StoreService {
    private final StoreProducts storeProducts;

    public void addProduct(ItemRequest request) {
        Item newItem = Item.builder()
                        .name(request.getName())
                        .normalPrice(request.getNormalPrice())
                        .requiredQuantity(request.getRequiredQuantity())
                        .specialPrice(request.getSpecialPrice())
                        .build();

        storeProducts.addItem(newItem);
    }

    public List<Item> getProducts() {
        return storeProducts.getItems();
    }
}
