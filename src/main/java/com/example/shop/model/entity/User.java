package com.example.shop.model.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

//    @Column(name = "login")
    private String login;

//    @Column(name = "password")
    private String password;

//    @Column(name = "first_name")
    private String firstName;

//    @Column(name = "last_name")
    private String lastName;

//    @Column(name = "reg_date")
    private Date regDate;

//    @Column(name = "is_blocked")
    private boolean isBlocked;

//    @Column(name = "address")
    private String address;

//    @Column(name = "comments")
    private String comments;

//    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    // зачем, если и без этого работает???
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}