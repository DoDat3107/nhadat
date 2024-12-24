package com.codegym.quanlynha.controller;

import com.codegym.quanlynha.model.Customer;
import com.codegym.quanlynha.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    // Hiển thị danh sách khách hàng
    @GetMapping("/list")
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "/customer/list";
    }

    // Hiển thị form thêm khách hàng
    @GetMapping("/create")
    public String showAddForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "/customer/create";
    }

    // Xử lý thêm khách hàng
    @PostMapping("/create")
    public String addCustomer(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/customers/list";
    }

    // Hiển thị form chỉnh sửa khách hàng
    @GetMapping("/update/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid customer ID: " + id));
        model.addAttribute("customer", customer);
        return "/customer/update";
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer customer) {
        customer.setIdCustomer(Math.toIntExact(id));
        customerService.save(customer);
        return "redirect:/customer/update";
    }

    // Xử lý xóa khách hàng
    @PostMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.remove(id);
        return "redirect:/customer/list";
    }
}