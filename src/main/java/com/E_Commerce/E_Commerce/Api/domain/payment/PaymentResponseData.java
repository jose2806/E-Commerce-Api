package com.E_Commerce.E_Commerce.Api.domain.payment;

import com.E_Commerce.E_Commerce.Api.domain.order.OrderResponseData;

public record PaymentResponseData(
        Long id,
        Long userId,
        String username,
        String email,
        OrderResponseData order,
        Methods paymentReference,
        StatusPayment statusPayment
) {
}
