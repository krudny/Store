package com.agh.store.controller;

import com.agh.store.DTO.ItemRequest;
import com.agh.store.model.Item;
import com.agh.store.service.StoreService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
@AllArgsConstructor
public class StoreController {
    private StoreService storeService;

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@Valid @RequestBody ItemRequest request) {
        storeService.addProduct(request);

        return ResponseEntity.ok("Product added successfully. ");
    }

    @GetMapping("")
    public List<Item> getProducts() {
        return storeService.getProducts();
    }
}
