package com.codegym.quanlynha.controller;

import com.codegym.quanlynha.model.Product;
import com.codegym.quanlynha.service.ICustomerService;
import com.codegym.quanlynha.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("product", productService.findAll());
        return "product/list";
    }

    // Hiển thị form thêm sản phẩm
    @GetMapping("/create")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("customer", customerService.findAll());  // Lấy danh sách khách hàng
        return "/product/create";
    }

    // Xử lý thêm sản phẩm
    @PostMapping("/create")
    public String addProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/product/create";
    }

    // Hiển thị form chỉnh sửa sản phẩm
    @GetMapping("/update/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
        model.addAttribute("product", product);
        model.addAttribute("customers", customerService.findAll());  // Lấy danh sách khách hàng
        return "product/update";
    }

    // Xử lý cập nhật sản phẩm
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        product.setIdProduct(Math.toIntExact(id));
        productService.save(product);
        return "redirect:/product/update";
    }

    // Xử lý xóa sản phẩm
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.remove(id);
        return "redirect:/product/list";
    }
}
