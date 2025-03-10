package com.E_Commerce.E_Commerce.Api.domain.cartItem;

import com.E_Commerce.E_Commerce.Api.domain.product.Product;
import com.E_Commerce.E_Commerce.Api.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "CartItem")
@Table(name = "cart_items")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    public CartItem(User user, Product product, @NotNull int quantity) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }


    public void update(User user, Product product, int quantity) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }
}
