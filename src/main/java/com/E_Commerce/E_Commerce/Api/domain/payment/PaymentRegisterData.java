package com.E_Commerce.E_Commerce.Api.domain.payment;

public record PaymentRegisterData(
        Long userId,
        Long orderId,
        Methods method
) {
}
