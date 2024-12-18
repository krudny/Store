package com.agh.store.configurator;

import com.agh.store.model.Item;
import com.agh.store.model.StoreProducts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreConfiguratorTest {

    private StoreProducts storeProducts;
    private StoreConfigurator storeConfigurator;

    @BeforeEach
    void setUp() {
        storeProducts = new StoreProducts();
        storeConfigurator = new StoreConfigurator(storeProducts);
    }

    @Test
    void init_ShouldAddItemsWhenStoreProductsIsEmpty() {
        // given
        assertTrue(storeProducts.getItems().isEmpty(), "StoreProducts should initially be empty");

        // when
        storeConfigurator.init();

        // then
        assertEquals(3, storeProducts.getItems().size(), "StoreProducts should contain 3 items after initialization");

        // Verify the details of each added item
        Item item1 = storeProducts.getItems().getFirst();
        assertEquals("Truck", item1.getName());
        assertEquals(15.00, item1.getNormalPrice());
        assertEquals(4, item1.getRequiredQuantity());
        assertEquals(12.00, item1.getSpecialPrice());

        Item item2 = storeProducts.getItems().get(1);
        assertEquals("Teddy", item2.getName());
        assertEquals(5.00, item2.getNormalPrice());
        assertEquals(2, item2.getRequiredQuantity());
        assertEquals(4.50, item2.getSpecialPrice());

        Item item3 = storeProducts.getItems().get(2);
        assertEquals("Toy", item3.getName());
        assertEquals(10.00, item3.getNormalPrice());
        assertNull(item3.getSpecialPrice());
    }

    @Test
    void init_ShouldNotAddItemsWhenStoreProductsIsNotEmpty() {
        // given
        storeProducts.addItem(Item.builder()
                .name("Existing Item")
                .normalPrice(20.00)
                .build());
        assertFalse(storeProducts.getItems().isEmpty(), "StoreProducts should not be empty");

        // when
        storeConfigurator.init();

        // then
        assertEquals(1, storeProducts.getItems().size(), "StoreProducts should still contain only 1 item");
        assertEquals("Existing Item", storeProducts.getItems().getFirst().getName());
    }
}