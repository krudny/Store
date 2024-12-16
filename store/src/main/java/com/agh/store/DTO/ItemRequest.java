package com.agh.store.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ItemRequest {
    @NotBlank(message = "Name should not be empty!")
    private String name;

    @Positive(message = "Price must be greater than 0")
    private float normalPrice;

    private Integer requiredQuantity;
    private float specialPrice;
}