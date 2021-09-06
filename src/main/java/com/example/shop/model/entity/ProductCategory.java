package com.example.shop.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Data
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

}
