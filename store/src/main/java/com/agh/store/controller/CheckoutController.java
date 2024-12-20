package com.agh.store.controller;

import com.agh.store.DTO.AddToCartDTO;
import com.agh.store.model.Item;
import com.agh.store.service.CheckoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.TreeMap;

@RestController
@RequestMapping("/api/checkout")
@AllArgsConstructor
public class CheckoutController {
    private CheckoutService checkoutService;

    @PostMapping("/add_to_cart")
    public ResponseEntity<String> addToCart(@Valid @RequestBody AddToCartDTO request) {
        checkoutService.addToCart(request.getItemId(), request.getQuantity());

        return ResponseEntity.ok("Item added to cart successfully!");
    }

    @GetMapping("/show_cart")
    public TreeMap<Item, Integer> showCart(){
        return checkoutService.showCart();
    }

    @GetMapping("/get_price/{id}")
    public String getPrice(@PathVariable long id){
        return checkoutService.getPrice(id);
    }

    @GetMapping("/get_receipt")
    public String getReceipt(){
        return checkoutService.getReceipt();
    }
}
