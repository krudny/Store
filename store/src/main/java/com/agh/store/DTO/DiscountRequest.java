package com.agh.store.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class DiscountRequest {
    @NotNull(message = "Item1Id cannot be empty")
    private Long item1Id;
    @NotNull(message = "Item2Id cannot be empty")
    private Long item2Id;
    @Positive(message = "Value must be greater than 0")
    private Double value;
}
