package com.agh.store.service;

import com.agh.store.DTO.ItemDTO;
import com.agh.store.model.Item;
import com.agh.store.model.StoreProducts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class StoreService {
    private final StoreProducts storeProducts;

    public void createProduct(ItemDTO request) {
        if ((request.getRequiredQuantity() != null) ^ (request.getSpecialPrice() != null)) {
            throw new IllegalArgumentException("Either required quantity or special price is missing, but not both.");
        }

        Item newItem = Item.builder()
                        .name(request.getName())
                        .normalPrice(request.getNormalPrice())
                        .requiredQuantity(request.getRequiredQuantity())
                        .specialPrice(request.getSpecialPrice())
                        .build();

        storeProducts.addItem(newItem);
    }

    public List<Item> getStoreProducts() {
        return storeProducts.getItems();
    }
}
