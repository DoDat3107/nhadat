package com.codegym.quanlynha.repository;

import com.codegym.quanlynha.model.Customer;
import com.codegym.quanlynha.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

}
