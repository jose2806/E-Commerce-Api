package com.E_Commerce.E_Commerce.Api.domain.cart;

import com.E_Commerce.E_Commerce.Api.domain.product.Product;
import com.E_Commerce.E_Commerce.Api.domain.user.User;

public record CartItemListData(
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
    public CartItemListData(Cart cart){
        this(cart.getId(), cart.getUser().getId(), cart.getUser().getName(), cart.getUser().getEmail(),
                cart.getProduct().getId(), cart.getProduct().getName(), cart.getProduct().getDescription(), cart.getProduct().getPrice() ,cart.getQuantity());
    }
}
