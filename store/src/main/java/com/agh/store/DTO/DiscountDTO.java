package com.agh.store.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(hidden = true)
@AllArgsConstructor
public class DiscountDTO {
    @NotNull(message = "Item1Id cannot be empty")
    private Long item1Id;
    @NotNull(message = "Item2Id cannot be empty")
    private Long item2Id;
    @Positive(message = "Value must be greater than 0")
    private Double value;
}
