package com.codegym.quanlynha.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduct;

    @ManyToOne(fetch = FetchType.LAZY)  // Tạo mối quan hệ với Customer
    @JoinColumn(name = "idCustomer")  // Khóa ngoại tham chiếu đến bảng Customer
    private Customer customer;

    @NotNull(message = "Tên khách hàng không được để trống")
    private String nameCustomer;

    @NotNull(message = "Ngày tạo không được để trống")
    private LocalDateTime dateTime;

    @NotNull(message = "Vai trò không được để trống")
    private String role;

    @NotNull(message = "Giá không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Giá phải lớn hơn 0")
    private Double price;

    @NotNull(message = "Diện tích không được để trống")
    private int dientich;

    public Product() {}

    public Product(Customer customer, String nameCustomer, LocalDateTime dateTime, String role, Double price, int dientich) {
        this.customer = customer;
        this.nameCustomer = nameCustomer;
        this.dateTime = dateTime;
        this.role = role;
        this.price = price;
        this.dientich = dientich;
    }
}
