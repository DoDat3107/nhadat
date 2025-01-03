package com.codegym.quanlynha.controller;

import com.codegym.quanlynha.model.Customer;
import com.codegym.quanlynha.model.Transaction;
import com.codegym.quanlynha.service.CustomerService;
import com.codegym.quanlynha.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CustomerService customerService;
    @GetMapping("/list")
    public ModelAndView showTransList(@RequestParam(value = "customerName", required = false) String customerName) {
        ModelAndView modelAndView = new ModelAndView("transaction/list");
        Iterable<Transaction> transactions;

        if (customerName != null && !customerName.isEmpty()) {
            transactions = transactionService.findByCustomerName(customerName);
            modelAndView.addObject("customerName", customerName);
        } else {
            transactions = transactionService.findAll();
        }
        if (!transactions.iterator().hasNext()) {
            modelAndView.addObject("errorMessage", "Không tìm thấy giao dịch nào.");
        }
        modelAndView.addObject("transactions", transactions);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showFormAdd(){
        ModelAndView modelAndView = new ModelAndView("/transaction/create");
        Iterable<Customer> list = customerService.findAll();
        modelAndView.addObject("list", list);
        modelAndView.addObject("transaction", new Transaction());
        return modelAndView;
    }

    @PostMapping("/create")
    public String addTransaction(@Validated @ModelAttribute Transaction transaction, BindingResult bindingResult, @RequestParam Long idCustomer, Model model){
        Iterable<Customer> list = customerService.findAll();
        if (bindingResult.hasErrors()){
            model.addAttribute("list", list);
            return "/transaction/create";
        }
        model.addAttribute("list", list);
        Optional<Customer> customerOptional = customerService.findById(idCustomer);
        Customer customer = customerOptional.get();
        transaction.setCustomer(customer);
        transactionService.save(transaction);
        return "redirect:/transaction/list";
    }

    @GetMapping("/info")
    public ModelAndView viewTransaction(@RequestParam Long id){
        Optional<Transaction> transaction = transactionService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/transaction/info");
        modelAndView.addObject("transaction", transaction.get());
        return modelAndView;
    }

    @GetMapping("/delete")
    public String deleteTransaction(@RequestParam Long id){
        transactionService.delete(id);
        return "redirect:/transaction/list";
    }
}
