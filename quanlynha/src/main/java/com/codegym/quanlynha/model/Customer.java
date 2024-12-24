package com.codegym.quanlynha.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "tên không được để trống")
    private String name;

    @NotBlank(message = "số điện thoại không được để trống")
    private String phone;

    @NotBlank(message = "email không được để trống")
    private String email;
}
