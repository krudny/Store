package com.agh.store.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddToCartRequest {
    @NotNull(message = "ItemId should not be empty!")
    private long itemId;

    @Positive(message = "Quantity must be greater than 0")
    private int quantity;

}
