package com.agh.store.controller;

import com.agh.store.DTO.AddToCartRequest;
import com.agh.store.model.Item;
import com.agh.store.service.CheckoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/checkout")
@AllArgsConstructor
public class CheckoutController {
    private CheckoutService checkoutService;

    @PostMapping("/add_to_cart")
    public ResponseEntity<String> addToCart(@Valid @RequestBody AddToCartRequest request) {
        checkoutService.addToCart(request.getItemId(), request.getQuantity());

        return ResponseEntity.ok("Item added to cart successfully!");
    }

    @GetMapping("/show_cart")
    public HashMap<Item, Integer> showCart(){
        return checkoutService.showCart();
    }

    @GetMapping("/total_price")
    public double checkout(){
        return checkoutService.checkout();
    }
}
