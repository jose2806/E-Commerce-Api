package com.E_Commerce.E_Commerce.Api.controller;

import com.E_Commerce.E_Commerce.Api.domain.product.*;
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
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity addProduct(@RequestBody @Valid ProductRegisterData data, UriComponentsBuilder uriComponentsBuilder){
        Product product = productRepository.save(new Product(data));
        ProductResponseData response = new ProductResponseData(product.getId(),
                product.getName(), product.getDescription(), product.getPrice(),
                product.getStock(),product.getCreatedAt(), product.getUpdatedAt());
        URI url = uriComponentsBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductListData>> productsList(){
        return ResponseEntity.ok(productRepository.findAll().stream()
                .map(ProductListData::new).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductResponseData> getProduct(@PathVariable Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Producto no encontrado"));
        var productData = new ProductResponseData(product.getId(), product.getName(),
                product.getDescription(), product.getPrice(), product.getStock(),
                product.getCreatedAt(), product.getUpdatedAt());
        return ResponseEntity.ok(productData);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductResponseData> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductUpdateData data){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Producto no encontrado"));
        product.update(data);
        return ResponseEntity.ok(new ProductResponseData(product.getId(), product.getName(),
                product.getDescription(), product.getPrice(), product.getStock(), product.getCreatedAt(),
                product.getUpdatedAt()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Producto no encontrado"));
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
}
