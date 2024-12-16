package com.agh.store.controller;

import com.agh.store.DTO.DiscountDTO;
import com.agh.store.service.DiscountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discounts")
@AllArgsConstructor
public class DiscountController {
    private final DiscountService discountService;

    @PostMapping("/add")
    public ResponseEntity<String> addDiscount(@Valid @RequestBody DiscountDTO request) {
        discountService.addDiscount(request);

        return ResponseEntity.ok("Discount added successfully!");
    }

    @GetMapping("show_all")
    public String showAllDiscounts() {
        return discountService.showDiscounts();
    }

}
