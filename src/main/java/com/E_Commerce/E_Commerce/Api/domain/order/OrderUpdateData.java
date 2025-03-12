package com.E_Commerce.E_Commerce.Api.domain.order;

import java.util.List;

public record OrderUpdateData(
        Long userId,
        List<Long> cartItemsId,
        StatusOrder statusOrder
) {
}
