package com.E_Commerce.E_Commerce.Api.domain.cartItem;

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
    public CartItemResponseData(CartItem item){
        this(item.getId(), item.getUser().getId(), item.getUser().getName(),
                item.getUser().getEmail(), item.getProduct().getId(), item.getProduct().getName(),
                item.getProduct().getDescription(), item.getProduct().getPrice(), item.getQuantity());
    }
}
