package com.E_Commerce.E_Commerce.Api.domain.payment;

import com.E_Commerce.E_Commerce.Api.domain.order.Order;
import com.E_Commerce.E_Commerce.Api.domain.order.StatusOrder;

public record PaymentResponse(
        Long id,
        Long userId,
        String username,
        String email,
        Order order,
        Methods paymentReference,
        StatusPayment statusOrder
) {
}
