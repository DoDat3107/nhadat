package com.codegym.quanlynha.service;

import com.codegym.quanlynha.model.Customer;
import com.codegym.quanlynha.repository.ICustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService{
    private final ICustomerRepository iCustomerRepository;
    public CustomerService(ICustomerRepository iCustomerRepository){
        this.iCustomerRepository = iCustomerRepository;
    }
    @Override
    public Iterable<Customer> findAll() {
        return iCustomerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return iCustomerRepository.findById(id);
    }

    @Override
    public void save(Customer customer) {
        iCustomerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        iCustomerRepository.deleteById(id);
    }
}
