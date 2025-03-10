package com.E_Commerce.E_Commerce.Api.domain.order;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRegisterData(
        @NotNull
        Long userId,
        @NotNull
        List<Long> cartItemsId,
        @NotNull
        Status status
) {
}
