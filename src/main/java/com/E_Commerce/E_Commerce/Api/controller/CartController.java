package com.E_Commerce.E_Commerce.Api.controller;

import com.E_Commerce.E_Commerce.Api.domain.cart.*;
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
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity addCartItem(@RequestBody @Valid CartItemRegisterData data, UriComponentsBuilder uriComponentsBuilder){
        Product product = productRepository.findById(data.productId())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        User user = userRepository.findById(data.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        Cart cartItem = new Cart(user, product, data.quantity());
        Cart cartItemNew = cartRepository.save(cartItem);
        CartItemResponseData response = new CartItemResponseData(cartItemNew.getId(), cartItemNew.getUser().getId(), cartItemNew.getUser().getName(), cartItemNew.getUser().getEmail(),
                cartItemNew.getProduct().getId(), cartItemNew.getProduct().getName(), cartItemNew.getProduct().getDescription(), cartItemNew.getProduct().getPrice() ,cartItemNew.getQuantity());

        URI url = uriComponentsBuilder.path("/cart/{id}").buildAndExpand(cartItemNew.getId()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CartItemListData>> cartItemsList(){
       return ResponseEntity.ok(cartRepository.findAll().stream()
               .map(CartItemListData::new).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<CartItemResponseData> getCartItem(@PathVariable Long id){
        Cart cartItem = cartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item no encontrado"));
        var cartItemData = new CartItemResponseData(cartItem.getId(), cartItem.getUser().getId(), cartItem.getUser().getName(), cartItem.getUser().getEmail(),
                cartItem.getProduct().getId(), cartItem.getProduct().getName(), cartItem.getProduct().getDescription(), cartItem.getProduct().getPrice() ,cartItem.getQuantity());
        return ResponseEntity.ok(cartItemData);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CartItemResponseData> updateCartItem(@PathVariable Long id ,@RequestBody @Valid cartItemUpdateData data){
        Cart cartItem = cartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item no encontrado"));
        Product product = productRepository.findById(data.productId())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        User user = userRepository.findById(data.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        cartItem.update(user, product, data);
        return ResponseEntity.ok(new CartItemResponseData(cartItem.getId(), cartItem.getUser().getId(), cartItem.getUser().getName(), cartItem.getUser().getEmail(),
                cartItem.getProduct().getId(), cartItem.getProduct().getName(), cartItem.getProduct().getDescription(), cartItem.getProduct().getPrice() ,cartItem.getQuantity()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCartItem(@PathVariable Long id){
        Cart cartItem = cartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item no encontrado"));
        cartRepository.delete(cartItem);
        return ResponseEntity.noContent().build();
    }
}
