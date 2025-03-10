package com.E_Commerce.E_Commerce.Api.controller;

import com.E_Commerce.E_Commerce.Api.domain.cartItem.CartItem;
import com.E_Commerce.E_Commerce.Api.domain.cartItem.CartItem;
import com.E_Commerce.E_Commerce.Api.domain.cartItem.CartItemRepository;
import com.E_Commerce.E_Commerce.Api.domain.cartItem.CartItemResponseData;
import com.E_Commerce.E_Commerce.Api.domain.order.Order;
import com.E_Commerce.E_Commerce.Api.domain.order.OrderRegisterData;
import com.E_Commerce.E_Commerce.Api.domain.order.OrderRepository;
import com.E_Commerce.E_Commerce.Api.domain.order.OrderResponseData;
import com.E_Commerce.E_Commerce.Api.domain.product.Product;
import com.E_Commerce.E_Commerce.Api.domain.product.ProductRepository;
import com.E_Commerce.E_Commerce.Api.domain.user.User;
import com.E_Commerce.E_Commerce.Api.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity addOrder(@RequestBody @Valid OrderRegisterData data, UriComponentsBuilder uriComponentsBuilder){
        User user = userRepository.findById(data.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        List<CartItem> cartItems = cartItemRepository.findAllById(data.cartItemsId());
        System.out.println(cartItems);
        double total = 0;
        for (CartItem item : cartItems ) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
            total += (product.getPrice() * item.getQuantity());
        }

        Order order = orderRepository.save(new Order(user,cartItems,total,data));
        OrderResponseData response = new OrderResponseData(order.getId(), order.getUser().getId(),
                order.getItems().stream().map(item -> new CartItemResponseData(item)).collect(Collectors.toList()),
                order.getTotal(), order.getStatus(), order.getCreatedAt(), order.getUpdatedAt());

        URI url = uriComponentsBuilder.path("/order/{id}").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteOrder(@PathVariable Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orden no encontrada"));
        orderRepository.delete(order);
        return ResponseEntity.noContent().build();
    }
}
