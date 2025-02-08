package com.fpoly.java5.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.java5.beans.LoginBean;

@Controller
public class AuthController {

  @GetMapping("/login")
  public String login() {
    return "/login.html";
  }

  @PostMapping("/login")
  public String handleLogin(
    @RequestParam(name = "username") String username,
    @RequestParam(name = "password") String password,
    // LoginBean loginBean,
    Model model
  ){
    model.addAttribute("username", username);
    model.addAttribute("password", password);

    // Kiểm tra username không được để trống => nếu rỗng trả về login và lỗi
    // "      " => rỗng
    // Kiểm tra password có ít nhất 6 ký tự => nếu không trả về login và lỗi

    if(username.isBlank()){
      model.addAttribute("errorUsername", "Username is required");
    }

    if(password.length() < 6 || password.isBlank()){
      model.addAttribute("errorPassword", "Password must be at least 6 characters");
    }
    
    return "/login.html";
  }
}
