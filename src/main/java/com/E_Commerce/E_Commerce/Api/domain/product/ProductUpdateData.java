package com.E_Commerce.E_Commerce.Api.domain.product;

import jakarta.validation.constraints.PositiveOrZero;

public record ProductUpdateData(
        String name,
        String description,
        @PositiveOrZero
        Double price,
        @PositiveOrZero
        int stock
) {

}
