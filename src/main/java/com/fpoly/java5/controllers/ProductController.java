package com.fpoly.java5.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fpoly.java5.beans.ProductBean;
import com.fpoly.java5.entities.Category;
import com.fpoly.java5.jpas.CategoryJPA;
import com.fpoly.java5.jpas.ProductJPA;

@Controller
public class ProductController {

  @Autowired
  CategoryJPA categoryJPA;

  @Autowired
  ProductJPA productJPA;

  @GetMapping("/user/products")
  public String products(Model model){
    model.addAttribute("products", productJPA.findAll());
    return "/products.html";
  }
  
  @GetMapping("/product-form")
  public String productForm(){
    return "/product-form.html";
  }

  @PostMapping("/product-form")
  public String handleProductForm(ProductBean productBean){
    // Check error
    return "/product-form.html";
  }

  @ModelAttribute(name = "categories")
  public List<Category> getCategories(){
    return categoryJPA.findAll();
  }
}
