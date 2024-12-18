package com.agh.store.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(hidden = true)
@AllArgsConstructor
public class ItemDTO {
    @NotBlank(message = "Name should not be empty!")
    private String name;

    @Positive(message = "Price must be greater than 0")
    private double normalPrice;

    @Positive(message = "Required quantity must be greater than 0")
    private Integer requiredQuantity;

    @Positive(message = "Special price must be greater than 0")
    private Double specialPrice;
}