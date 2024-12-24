package com.codegym.quanlynha.service;

import com.codegym.quanlynha.model.Customer;
import com.codegym.quanlynha.model.Product;
import com.codegym.quanlynha.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository ICustomerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return ICustomerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return ICustomerRepository.findById(id);
    }

    @Override
    public void save(Customer customer) {
        ICustomerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        ICustomerRepository.deleteById(id);
    }

}
