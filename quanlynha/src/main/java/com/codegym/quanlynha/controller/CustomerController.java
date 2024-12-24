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
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping("/list")
    public ModelAndView showListCustomer(){
        ModelAndView modelAndView = new ModelAndView("customer/list");
        Iterable<Customer> customers = customerService.findAll();
        if (!customers.iterator().hasNext()){
            modelAndView.addObject("errorMessage","Chưa có khách hàng nào trong hệ thống");
            modelAndView.addObject("customers", customers);
            return modelAndView;
        }
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView showFormAdd(){
        ModelAndView modelAndView = new ModelAndView("customer/add");
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }

    @PostMapping("/add")
    public String addCustomer(@Validated @ModelAttribute Customer customer, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "customer/add";
        }
        customerService.save(customer);
        return "redirect:/customers/list";
    }

    @GetMapping("/view")
    public ModelAndView viewCustomer(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView("customer/view");
        Optional<Customer> customer = customerService.findById(id);
        modelAndView.addObject("customer", customer.get());
        return modelAndView;
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam Long id) {
        customerService.delete(id);
        return "redirect:/customers/list";
    }
}
