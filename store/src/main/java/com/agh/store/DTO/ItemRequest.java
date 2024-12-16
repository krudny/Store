package com.agh.store.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ItemRequest {
    @NotBlank(message = "Name should not be empty!")
    private String name;

    @Positive(message = "Price must be greater than 0")
    private double normalPrice;

    @Positive(message = "Required quantity must be greater than 0")
    private Integer requiredQuantity;

    @Positive(message = "Special price must be greater than 0")
    private Double specialPrice;
}