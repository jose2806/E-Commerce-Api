package com.E_Commerce.E_Commerce.Api.domain.order;

import com.E_Commerce.E_Commerce.Api.domain.cartItem.CartItemResponseData;

import java.time.LocalDateTime;
import java.util.List;


public record OrderResponseData(
        Long id,
        Long userId,
        List<CartItemResponseData> itemsId,
        Double total,
        StatusOrder statusOrder,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
        )
{

}
