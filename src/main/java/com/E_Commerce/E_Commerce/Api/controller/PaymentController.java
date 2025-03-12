package com.E_Commerce.E_Commerce.Api.controller;

import com.E_Commerce.E_Commerce.Api.domain.cartItem.CartItemResponseData;
import com.E_Commerce.E_Commerce.Api.domain.order.Order;
import com.E_Commerce.E_Commerce.Api.domain.order.StatusOrder;
import com.E_Commerce.E_Commerce.Api.domain.order.OrderRepository;
import com.E_Commerce.E_Commerce.Api.domain.order.OrderResponseData;
import com.E_Commerce.E_Commerce.Api.domain.payment.*;
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
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity addPayment(@RequestBody @Valid PaymentRegisterData data, UriComponentsBuilder uriComponentsBuilder){
        Order order = orderRepository.findById(data.orderId())
                .orElseThrow(() -> new EntityNotFoundException("Orden no encontrada"));
        User user = userRepository.findById(data.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        Payment payment = paymentRepository.save(new Payment(user,order,data.method()));
        if(payment.getStatusPayment() == StatusPayment.SUCCESS){
            payment.getOrder().update(payment.getUser(),payment.getOrder().getItems(), payment.getOrder().getTotal(), StatusOrder.PAID);
        }
        PaymentResponseData response = new PaymentResponseData(payment.getId(), payment.getUser().getId(),
                payment.getUser().getName(), payment.getUser().getEmail(),new OrderResponseData(payment.getOrder().getId(),
                payment.getOrder().getUser().getId(),payment.getOrder().getItems().stream().map(item -> new CartItemResponseData(item)).collect(Collectors.toList()),
                payment.getOrder().getTotal(),payment.getOrder().getStatusOrder(),payment.getOrder().getCreatedAt(),payment.getOrder().getUpdatedAt()),payment.getPaymentReference(),
                payment.getStatusPayment());
        URI url = uriComponentsBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PaymentListData>> paymentList(){
        return ResponseEntity.ok(paymentRepository.findAll().stream()
                .map(PaymentListData::new).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<PaymentResponseData> getPayment(@PathVariable Long id){
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Pago no encontrado"));
        var paymentData = new PaymentResponseData(payment.getId(), payment.getUser().getId(),
                payment.getUser().getName(),payment.getUser().getEmail(),new OrderResponseData(payment.getOrder().getId(),
                payment.getOrder().getUser().getId(),payment.getOrder().getItems().stream().map(item -> new CartItemResponseData(item)).collect(Collectors.toList()),
                payment.getOrder().getTotal(),payment.getOrder().getStatusOrder(),payment.getOrder().getCreatedAt(),payment.getOrder().getUpdatedAt()),payment.getPaymentReference(),
                payment.getStatusPayment());
        return ResponseEntity.ok(paymentData);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PaymentResponseData> updatePayment(@PathVariable Long id, @RequestBody @Valid PaymentUpdateData data){
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Pago no encontrado"));
        User user = userRepository.findById(data.userId())
                .orElseThrow(()->new EntityNotFoundException("Usuario no encontrado"));
        Order order = orderRepository.findById(data.orderId())
                .orElseThrow(()->new EntityNotFoundException("Orden no encontrada"));
        payment.update(user,order,data.method());
        return ResponseEntity.ok(new PaymentResponseData(payment.getId(), payment.getUser().getId(),
                payment.getUser().getName(),payment.getUser().getEmail(),new OrderResponseData(payment.getOrder().getId(),
                payment.getOrder().getUser().getId(),payment.getOrder().getItems().stream().map(item -> new CartItemResponseData(item)).collect(Collectors.toList()),
                payment.getOrder().getTotal(),payment.getOrder().getStatusOrder(),payment.getOrder().getCreatedAt(),payment.getOrder().getUpdatedAt()),payment.getPaymentReference(),
                payment.getStatusPayment()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePayment(@PathVariable Long id){
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado"));
        paymentRepository.delete(payment);
        return ResponseEntity.noContent().build();
    }
}
