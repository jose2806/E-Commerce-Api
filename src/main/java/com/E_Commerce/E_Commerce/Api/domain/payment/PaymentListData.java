package com.E_Commerce.E_Commerce.Api.domain.payment;

import com.E_Commerce.E_Commerce.Api.domain.cartItem.CartItemResponseData;
import com.E_Commerce.E_Commerce.Api.domain.order.Order;
import com.E_Commerce.E_Commerce.Api.domain.order.OrderResponseData;
import com.E_Commerce.E_Commerce.Api.domain.order.StatusOrder;

import java.util.stream.Collectors;

public record PaymentListData(
        Long id,
        Long userId,
        String username,
        String email,
        OrderResponseData order,
        Methods paymentReference,
        StatusPayment statusOrder
) {
    public PaymentListData(Payment payment){
        this(payment.getId(),payment.getUser().getId(), payment.getUser().getName(),
                payment.getUser().getEmail(), new OrderResponseData(payment.getOrder().getId(),
                        payment.getOrder().getUser().getId(), payment.getOrder().getItems().stream()
                        .map(item -> new CartItemResponseData(item)).collect(Collectors.toList()),payment.getOrder().getTotal(),
                        payment.getOrder().getStatusOrder(),payment.getOrder().getCreatedAt(),payment.getOrder().getUpdatedAt()),payment.getPaymentReference(),
                payment.getStatusPayment());
    }
}
