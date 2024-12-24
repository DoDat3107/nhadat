package com.codegym.quanlynha.controller;

import com.codegym.quanlynha.model.Customer;
import com.codegym.quanlynha.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping("/list")
    public ModelAndView showListCustomer(){
        ModelAndView modelAndView = new ModelAndView("customer/list");
        Iterable<Customer> customer = customerService.findAll();
        if (!customer.iterator().hasNext()){
            modelAndView.addObject("errorMessage","Chưa có khách hàng nào trong hệ thống");
            modelAndView.addObject("customers", customer);
            return modelAndView;
        }
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showFormAdd(){
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public String addCustomer(@Validated @ModelAttribute Customer customer, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "/customer/create";
        }
        customerService.save(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/info")
    public ModelAndView viewCustomer(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView("/customer/info");
        Optional<Customer> customer = customerService.findById(id);
        modelAndView.addObject("customer", customer.get());
        return modelAndView;
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam Long id) {
        customerService.delete(id);
        return "redirect:/customer/list";
    }
}
