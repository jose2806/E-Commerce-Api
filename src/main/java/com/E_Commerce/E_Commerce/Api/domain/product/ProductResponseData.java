package com.E_Commerce.E_Commerce.Api.domain.product;

import java.time.LocalDateTime;

public record ProductResponseData(
        Long id,
        String name,
        String description,
        Double price,
        int stock,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
