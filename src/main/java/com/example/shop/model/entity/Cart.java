package com.example.shop.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carts")
@Data
public class Cart {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;

    @Id
    @Column(name = "user_id")
    private int user_id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @ManyToMany
    @JoinTable(
            name = "carts_products",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")//,
//            foreignKey = @ForeignKey(name = "fk_carts_products_carts")
    )
    private List<Product> products;

}
