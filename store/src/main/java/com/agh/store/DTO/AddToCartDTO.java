package com.agh.store.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Schema(hidden = true)
@Data
public class AddToCartDTO {
    @NotNull(message = "ItemId should not be empty!")
    private Long itemId;

    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;

}
