package com.E_Commerce.E_Commerce.Api.domain.order;

import com.E_Commerce.E_Commerce.Api.domain.cartItem.CartItemResponseData;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record OrderListData(
        Long id,
        Long userId,
        String username,
        String email,
        List<CartItemResponseData> items,
        Double total,
        StatusOrder statusOrder,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public OrderListData(Order order){
        this(order.getId(),order.getUser().getId(),order.getUser().getName(),order.getUser().getEmail(),order.getItems().stream()
                        .map(item -> new CartItemResponseData(item.getId(),item.getUser().getId(),item.getUser().getName(),
                                item.getUser().getEmail(), item.getProduct().getId(),item.getProduct().getName(), item.getProduct().getDescription(),
                                item.getProduct().getPrice(), item.getQuantity())).collect(Collectors.toList()), order.getTotal(),
                order.getStatusOrder(), order.getCreatedAt(), order.getUpdatedAt());
    }

}
