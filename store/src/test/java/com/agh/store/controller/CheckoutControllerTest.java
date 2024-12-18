package com.agh.store.controller;

import com.agh.store.DTO.AddToCartDTO;
import com.agh.store.model.Cart;
import com.agh.store.model.Item;
import com.agh.store.service.CheckoutService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CheckoutController.class)
class CheckoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheckoutService checkoutService;

    @Test
    void addToCart_ShouldReturnSuccessMessage() throws Exception {
        // given
        AddToCartDTO request = new AddToCartDTO(1L, 5);
        String requestJson = """
                {
                    "itemId": 1,
                    "quantity": 5
                }
                """;

        doNothing().when(checkoutService).addToCart(1L, 5);

        // when & then
        mockMvc.perform(post("/api/checkout/add_to_cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Item added to cart successfully!"));
    }

    @Test
    void getPrice_ShouldReturnPriceForGivenId() throws Exception {
        // given
        long itemId = 1L;
        String price = "$50.0";

        when(checkoutService.getPrice(itemId)).thenReturn(price);

        // when & then
        mockMvc.perform(get("/api/checkout/get_price/{id}", itemId))
                .andExpect(status().isOk())
                .andExpect(content().string(price));
    }

    @Test
    void getReceipt_ShouldReturnReceiptDetails() throws Exception {
        // given
        String receipt = "Receipt:\nItem 1 - 2 x $10.0\nTotal: $20.0";

        when(checkoutService.getReceipt()).thenReturn(receipt);

        // when & then
        mockMvc.perform(get("/api/checkout/get_receipt"))
                .andExpect(status().isOk())
                .andExpect(content().string(receipt));
    }
}
