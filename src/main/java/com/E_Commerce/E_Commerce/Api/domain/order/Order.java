package com.E_Commerce.E_Commerce.Api.domain.order;

import com.E_Commerce.E_Commerce.Api.domain.cart.Cart;
import com.E_Commerce.E_Commerce.Api.domain.user.Role;
import com.E_Commerce.E_Commerce.Api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Order")
@Table(name = "orders")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Cart> items;

    private double total;

    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
