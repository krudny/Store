package com.agh.store.service;

import com.agh.store.DTO.DiscountDTO;
import com.agh.store.model.DiscountKey;
import com.agh.store.model.DiscountRelations;
import com.agh.store.model.Item;
import com.agh.store.model.StoreProducts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DiscountServiceTest {

    private DiscountRelations discountRelations;
    private StoreProducts storeProducts;
    private DiscountService discountService;

    @BeforeEach
    void setUp() {
        discountRelations = new DiscountRelations();
        storeProducts = mock(StoreProducts.class);
        discountService = new DiscountService(discountRelations, storeProducts);
    }

    @Test
    void shouldThrowExceptionWhenItemDoesNotExist() {
        // given
        DiscountDTO request = new DiscountDTO(1L, 2L, 10.0);
        when(storeProducts.getItemById(1L)).thenReturn(Optional.empty());
        when(storeProducts.getItemById(2L)).thenReturn(Optional.of(new Item(2L, "Test product 2", 20.0)));

        // when & then
        assertThrows(IllegalArgumentException.class, () -> discountService.addDiscount(request));
    }

    @Test
    void shouldThrowExceptionWhenDiscountRelationAlreadyExists() {
        // given
        DiscountDTO request = new DiscountDTO(1L, 2L, 10.0);
        Item item1 = new Item(1L, "Test product 1", 10.0);
        Item item2 = new Item(2L, "Test product 2", 20.0);
        DiscountKey discountKey = new DiscountKey(item1, item2);

        DiscountRelations discountRelationsMock = mock(DiscountRelations.class);
        DiscountService discountServiceMock = new DiscountService(discountRelationsMock, storeProducts);
        when(storeProducts.getItemById(1L)).thenReturn(Optional.of(item1));
        when(storeProducts.getItemById(2L)).thenReturn(Optional.of(item2));
        when(discountRelationsMock.getDiscountRelations())
                .thenReturn(new LinkedHashMap<>(Map.of(discountKey, 10.0)));

        // when & then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> discountServiceMock.addDiscount(request)
        );

        assertEquals("Discount relation already exists.", exception.getMessage());
    }

    @Test
    void shouldAddDiscountSuccessfully() {
        // given
        DiscountDTO request = new DiscountDTO(1L, 2L, 10.0);
        Item item1 = new Item(1L, "Test product 1", 10.0);
        Item item2 = new Item(2L, "Test product 2", 20.0);
        when(storeProducts.getItemById(1L)).thenReturn(Optional.of(item1));
        when(storeProducts.getItemById(2L)).thenReturn(Optional.of(item2));

        // when
        discountService.addDiscount(request);

        // then
        DiscountKey key = new DiscountKey(item1, item2);
        assertTrue(discountRelations.getDiscountRelations().containsKey(key));
        assertEquals(10.0, discountRelations.getDiscountRelations().get(key));
    }

    @Test
    void shouldReturnMessageForNoDiscounts() {
        // when
        String result = discountService.showDiscounts();

        // then
        assertEquals("No discount relations available.", result);
    }

    @Test
    void shouldShowDiscounts() {
        // given
        Item item1 = new Item(1L, "Test product 1", 10.0);
        Item item2 = new Item(2L, "Test product 2", 20.0);
        Item item3 = new Item(3L, "Test product 3", 30.0);
        DiscountKey key1 = new DiscountKey(item1, item2);
        DiscountKey key2 = new DiscountKey(item2, item3);

        DiscountRelations discountRelationsMock = mock(DiscountRelations.class);
        Map<DiscountKey, Double> discounts = new LinkedHashMap<>();
        discounts.put(key1, 10.0);
        discounts.put(key2, 20.0);
        when(discountRelationsMock.getDiscountRelations()).thenReturn(discounts);

        DiscountService discountServiceMock = new DiscountService(discountRelationsMock, storeProducts);

        // when
        String result = discountServiceMock.showDiscounts();

        // then
        String expected = """
                Discount Relations:
                Buy Test product 1 and Test product 2 to get discount Value: 10.0
                Buy Test product 2 and Test product 3 to get discount Value: 20.0
                """;

        assertEquals(expected, result);
    }
}
