package com.E_Commerce.E_Commerce.Api.domain.order;

import com.E_Commerce.E_Commerce.Api.domain.cartItem.CartItemResponseData;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record OrderResponseData(
        Long id,
        Long userId,
        List<CartItemResponseData> itemsId,
        Double total,
        Status status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
        )
{

}
