package com.E_Commerce.E_Commerce.Api.domain.product;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Product")
@Table(name = "products")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(@Valid ProductRegisterData data) {
        this.name = data.name();
        this.description = data.description();
        this.price = data.price();
        this.stock = data.stock();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void update(@Valid ProductUpdateData data) {
        boolean isUpdated = false;

        if (data.name() != null && !data.name().equals(this.name)) {
            this.name = data.name();
            isUpdated = true;
        }
        if (data.description() != null && !data.description().equals(this.description)) {
            this.description = data.description();
            isUpdated = true;
        }
        if (data.price() != this.price) {
            this.price = data.price();
            isUpdated = true;
        }
        if (data.stock() != this.stock) {
            this.stock = data.stock();
            isUpdated = true;
        }

        if (isUpdated) {
            this.updatedAt = LocalDateTime.now();
        }
    }
}
