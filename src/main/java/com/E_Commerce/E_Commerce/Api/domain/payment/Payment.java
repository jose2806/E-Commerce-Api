package com.E_Commerce.E_Commerce.Api.domain.payment;

import com.E_Commerce.E_Commerce.Api.domain.order.Order;
import com.E_Commerce.E_Commerce.Api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Payment")
@Table(name = "payments")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Methods paymentReference;

    private StatusPayment statusPayment;

    public Payment(User user, Order order, Methods method) {
        this.user = user;
        this.order = order;
        this.paymentReference = method;
        this.statusPayment = StatusPayment.SUCCESS;
    }

    public void update(User user, Order order, Methods method) {
        this.user = user;
        this.order = order;
        this.paymentReference = method;
    }
}
