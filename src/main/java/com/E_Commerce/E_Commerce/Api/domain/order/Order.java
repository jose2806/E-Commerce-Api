package com.E_Commerce.E_Commerce.Api.domain.order;

import com.E_Commerce.E_Commerce.Api.domain.cartItem.CartItem;
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
    private List<CartItem> items;

    private double total;

    private StatusOrder statusOrder;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public Order(User user, List<CartItem> cartItems, Double total) {
        this.user = user;
        this.items = cartItems;
        this.total = total;
        this.statusOrder = StatusOrder.PENDING;
        this.createdAt = LocalDateTime.now();
        this.updatedAt =LocalDateTime.now();
    }


    public void update(User user, List<CartItem> cartItems, Double total, StatusOrder statusOrder) {
        this.user = user;
        this.items = cartItems;
        this.statusOrder = statusOrder;
        this.total = total;
        this.updatedAt = LocalDateTime.now();
    }
}

