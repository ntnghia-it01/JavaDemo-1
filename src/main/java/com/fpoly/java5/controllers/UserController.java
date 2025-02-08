package com.fpoly.java5.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fpoly.java5.beans.UserBean;
import com.fpoly.java5.entities.UserEntity;
import com.fpoly.java5.jpas.UserJPA;

import jakarta.validation.Valid;

@Controller
public class UserController {
  
  @Autowired
  UserJPA userJPA;

  @GetMapping("/users")
  public String users(Model model){

    List<UserEntity> userEntities = userJPA.findAll();

    model.addAttribute("users", userEntities);

    return "/users.html";
  }

  @GetMapping("/add-user")
  public String addUser(){

    return "/user-form.html";
  }

  @PostMapping("/add-user")
  public String handleAddUser(
    @Valid @ModelAttribute("user") UserBean userBean,
    Errors errors,
    Model model
  ){

    if(errors.hasErrors() || userBean.isAvatarError() != null){
      // Có lỗi => Hiển thị lỗi ở html

      // addAttri => avatarError => userBean.isAvatarError()
      return "/user-form.html";
    }
    
    // Không lỗi => lưu db => trở về danh sách

    return "/user-form.html";
  }
}
