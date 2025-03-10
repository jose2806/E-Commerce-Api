package com.E_Commerce.E_Commerce.Api.domain.cartItem;

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
    public CartItemListData(CartItem cart){
        this(cart.getId(), cart.getUser().getId(), cart.getUser().getName(), cart.getUser().getEmail(),
                cart.getProduct().getId(), cart.getProduct().getName(), cart.getProduct().getDescription(), cart.getProduct().getPrice() ,cart.getQuantity());
    }

}
