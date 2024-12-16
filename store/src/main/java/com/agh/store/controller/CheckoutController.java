package com.agh.store.controller;

import com.agh.store.DTO.AddToCartRequest;
import com.agh.store.model.Item;
import com.agh.store.service.CheckoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/checkout")
@AllArgsConstructor
public class CheckoutController {
    private CheckoutService checkoutService;

    @PostMapping("/add_to_cart")
    public void addToCart(@Valid @RequestBody AddToCartRequest request) {
        checkoutService.addToCart(request.getItemId(), request.getQuantity());
    }

    @GetMapping("/show_cart")
    public HashMap<Item, Integer> showCart(){
        return checkoutService.showCart();
    }
}
