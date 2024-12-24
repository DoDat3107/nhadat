package com.codegym.quanlynha.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "MGD-\\d{4}", message = "Mã giao dịch phải theo định dạng MGD-XXXX.")
    @NotBlank(message = "mã giao dịch không được để trống!")
    @Column(name = "transaction_code", unique = true)
    private String transactionCode;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Future(message = "Ngày giao dịch phải lớn hơn thời gian hiện tại.")
    @Column(name = "transaction_date")
    @NotNull(message = "ngày giao dịch không được để trống!")
    private LocalDate transactionDate;
    @NotBlank(message = "loại dịch vụ không được để trống!")
    @Column(name = "service_type")
    private String serviceType;
    @NotNull(message = "đơn giá không được để trống!")
    @Min(value = 0, message = "Đơn giá phải lớn hơn 500.000 VND.")
    @Column(name = "price")
    private BigDecimal price;
    @Min(value = 10, message = "Diện tích phải lớn hơn 20 m².")
    @NotNull(message = "diện tích không được để trống!")
    @Column(name = "area")
    private BigDecimal area;
}