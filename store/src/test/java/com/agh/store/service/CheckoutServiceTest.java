package com.agh.store.service;

import com.agh.store.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CheckoutServiceTest {
    private Cart cart;
    private StoreProducts storeProducts;
    private DiscountRelations discountRelations;
    private CheckoutService checkoutService;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        storeProducts = mock(StoreProducts.class);
        discountRelations = new DiscountRelations();
        checkoutService = new CheckoutService(cart, storeProducts, discountRelations);
    }

    @Test
    void addToCart() {
        // given
        Item item = new Item(5L, "Test product", 10.0);

        // when
        when(storeProducts.getItemById(5)).thenReturn(Optional.of(item));
        cart.addItem(item, 5);

        // then
        assertEquals(1, cart.getCart().size(), "The cart should contain 1 item.");
        assertTrue(cart.getCart().containsKey(item), "The cart should contain the added item.");
        assertEquals(5, cart.getCart().get(item), "The quantity of the product in the cart should be 5.");
    }

    @Test
    void addToCartMultipleItems() {
        // given
        Item item1 = new Item(5L, "Test product 1", 10.0);
        Item item2 = new Item(6L, "Test product 2", 20.0);

        // when
        when(storeProducts.getItemById(5)).thenReturn(Optional.of(item1));
        when(storeProducts.getItemById(6)).thenReturn(Optional.of(item2));
        cart.addItem(item1, 5);
        cart.addItem(item2, 3);

        // then
        assertEquals(2, cart.getCart().size(), "The cart should contain 2 items.");
        assertTrue(cart.getCart().containsKey(item1), "The cart should contain the added item 1.");
        assertTrue(cart.getCart().containsKey(item2), "The cart should contain the added item 2.");
        assertEquals(5, cart.getCart().get(item1), "The quantity of product 1 in the cart should be 5.");
        assertEquals(3, cart.getCart().get(item2), "The quantity of product 2 in the cart should be 3.");
    }

    @Test
    void showCart() {
        // given
        Item item1 = new Item(5L, "Test product 1", 10.0);
        Item item2 = new Item(6L, "Test product 2", 20.0);
        cart.addItem(item1, 5);
        cart.addItem(item2, 3);

        // when
        Map<Item, Integer> result = checkoutService.showCart();

        // then
        assertEquals(2, result.size(), "The cart should contain 2 items.");
        assertTrue(result.containsKey(item1), "The cart should contain the added item 1.");
        assertTrue(result.containsKey(item2), "The cart should contain the added item 2.");
        assertEquals(5, result.get(item1), "Item 1 should have a quantity of 5.");
        assertEquals(3, result.get(item2), "Item 2 should have a quantity of 3.");

    }

    @Test
    void getNormalPrice() {
        // given
        Item item1 = new Item(5L, "Test product 1", 10.0);

        // when
        when(storeProducts.getItemById(5)).thenReturn(Optional.of(item1));
        String result = checkoutService.getPrice(5);

        // then
        assertEquals("Normal price 10.0PLN", result, "The price should be 10.0PLN.");
    }

    @Test
    void getSpecialPrice() {
        // given
        Item item1 = new Item(5L, "Test product 1", 10.0, 3, 5.0);

        // when
        when(storeProducts.getItemById(5)).thenReturn(Optional.of(item1));
        String result = checkoutService.getPrice(5);

        // then
        assertEquals("Normal price 10.0PLN, special price 5.0PLN with required quantity of 3", result,
                "The price should be 10.0PLN, special price 5.0PLN with required quantity of 3.");
    }

    @Test
    void pairDiscounts() {
        // given
        Item item1 = new Item(5L, "Test product 1", 10.0, 3, 5.0);
        Item item2 = new Item(6L, "Test product 2", 20.0);
        Item item3 = new Item(7L, "Test product 3", 30.0, 2, 25.0);

        DiscountKey key = new DiscountKey(item1, item2);
        discountRelations.addDiscountRelation(key, 5.0);

        cart.addItem(item1, 3);
        cart.addItem(item2, 2);
        cart.addItem(item3, 1);

        // when
        double result = checkoutService.calculatePairDiscount();

        // then
        assertEquals(15.0, result, "The discount should be 15.0PLN.");
    }

    @Test
    void getReceipt() {
        // given
        Item item1 = new Item(5L, "Test product 1", 10.0, 3, 5.0);
        Item item2 = new Item(6L, "Test product 2", 20.0);
        Item item3 = new Item(7L, "Test product 3", 30.0, 2, 25.0);

        cart.addItem(item1, 3);
        cart.addItem(item2, 2);
        cart.addItem(item3, 1);

        // when
        String result = checkoutService.getReceipt();

        // then
        String expectedReceipt =
                "Test product 1 x 3 = 15.0PLN\n" +
                "Test product 2 x 2 = 40.0PLN\n" +
                "Test product 3 x 1 = 30.0PLN\n" +
                "Discount from pair items: 0.0PLN\n" +
                "Total price: 85.0PLN";

        assertEquals(expectedReceipt, result, "The receipt should match the expected output.");

    }
}