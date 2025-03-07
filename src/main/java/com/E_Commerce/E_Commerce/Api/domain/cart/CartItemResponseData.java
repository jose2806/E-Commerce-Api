package com.E_Commerce.E_Commerce.Api.domain.cart;

import com.E_Commerce.E_Commerce.Api.domain.product.Product;
import com.E_Commerce.E_Commerce.Api.domain.user.User;

public record CartItemResponseData(
        Long id,
        Long userId,
        String name,
        String email,
        Long productId,
        String productName,
        String productDescription,
        Double price,
        int quantity
) {
}
