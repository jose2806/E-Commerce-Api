package com.E_Commerce.E_Commerce.Api.domain.product;

import java.time.LocalDateTime;

public record ProductListData(
        Long id,
        String name,
        String description,
        Double price,
        int stock,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public ProductListData(Product product){
        this(product.getId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getStock(), product.getCreatedAt(),
                product.getUpdatedAt());
    }
}
