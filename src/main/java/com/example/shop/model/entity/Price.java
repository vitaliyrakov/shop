package com.example.shop.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "price")
@Data
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "cost")
    private double cost;

    @Column(name = "dateP")
    private Date date;
}
