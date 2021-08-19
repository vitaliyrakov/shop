package com.example.shop.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "products")
@Data
//@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @JoinColumn(name = "price")
    private BigDecimal price;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "orders_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    List<Order> orders;

    @ManyToMany
    @JoinTable(
            name = "carts_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_id")
    )
    private List<Cart> carts;

    public String getCustomers() {
        return orders.stream()
                .map(o -> o.getUser())
                .distinct()
                .map(c -> c.getFirstName())
                .sorted()
                .collect(Collectors.joining(", "));
    }

}
