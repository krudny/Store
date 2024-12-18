package com.agh.store.service;

import com.agh.store.DTO.ItemDTO;
import com.agh.store.model.Item;
import com.agh.store.model.StoreProducts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StoreServiceTest {
    private StoreService storeService;
    private StoreProducts storeProducts;

    @BeforeEach
    void setUp() {
        storeProducts = new StoreProducts();
        storeService = new StoreService(storeProducts);
    }

    @Test
    void createInvalidProduct() {
        // given
        ItemDTO item1 = new ItemDTO("Test product 1", 10.0, 3, null);

        // when & then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            storeService.createProduct(item1);
        });
        assertEquals("Either required quantity or special price is missing, but not both.", exception.getMessage());
    }

    @Test
    void createProduct() {
        // given
        ItemDTO item1 = new ItemDTO("Test product 1", 10.0, 3, 5.0);

        // when
        storeService.createProduct(item1);

        // then
        assertEquals(1, storeProducts.getItems().size(), "Store should contain exactly one product after creation");
        Item createdItem = storeProducts.getItems().getFirst();
        assertEquals("Test product 1", createdItem.getName());
        assertEquals(10.0, createdItem.getNormalPrice());
        assertEquals(3, createdItem.getRequiredQuantity());
        assertEquals(5.0, createdItem.getSpecialPrice());
    }

    @Test
    void getStoreProducts() {
        // given
        Item item1 = new Item(1L, "Test product 1", 10.0);
        Item item2 = new Item(2L, "Test product 2", 20.0);
        Item item3 = new Item(3L, "Test product 3", 30.0);

        // when
        storeProducts.addItem(item1);
        storeProducts.addItem(item2);
        storeProducts.addItem(item3);
        List<Item> items = storeService.getStoreProducts();

        // then
        assertEquals(3, items.size());
        assertTrue(items.containsAll(List.of(item1, item2, item3)));
    }
}