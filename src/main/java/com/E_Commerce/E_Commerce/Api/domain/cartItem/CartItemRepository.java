package com.E_Commerce.E_Commerce.Api.domain.cartItem;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    Optional<CartItem> findByUserIdAndProductId(Long id, Long id1);
}
