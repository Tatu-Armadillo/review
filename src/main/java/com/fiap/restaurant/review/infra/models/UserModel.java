package com.fiap.restaurant.review.infra.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserModel {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "phone")
    private String phone;

    @Column(name = "user_name")
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "password")
    private String password;

}