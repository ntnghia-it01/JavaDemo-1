package com.fpoly.java5.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.java5.beans.UserBean;
import com.fpoly.java5.entities.UserEntity;
import com.fpoly.java5.jpas.UserJPA;
import com.fpoly.java5.services.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {
  
  @Autowired
  UserJPA userJPA;

  @Autowired
  UserService userService;

  @GetMapping("/users")
  public String users(Model model){

    List<UserEntity> userEntities = userJPA.findAll();

    model.addAttribute("users", userEntities);

    return "/users.html";
  }

  @GetMapping("/add-user")
  public String addUser(
    @RequestParam("id") Optional<Integer> id,
    Model model
    // @RequestParam(name = "id", defaultValue = "0") int id
    // @RequestParam(name = "id", required = false) int id
  ){
    // Lấy giá trị id ra
    if(id.isPresent()){
      // id.get() lấy giá trị
      // Lấy dữ liệu của userenity từ id
      Optional<UserEntity> userEntity = userJPA.findById(id.get());
      // Chuyển entity qua form hiển thị dữ liệu vào input
      if(userEntity.isPresent()){
        // Convert entity to bean
        UserBean bean = new UserBean();
        bean.setUsername(userEntity.get().getUsername());
        bean.setPassword(userEntity.get().getPassword());
        bean.setName(userEntity.get().getName());
        bean.setEmail(userEntity.get().getEmail());
        model.addAttribute("user", bean);
      }
      
      // Đổi tiêu đề và button từ add user => update user
    }


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

    String result = userService.insertUser(userBean);

    if(result != null){
      // addAttri => error => result

      return "/user-form.html";
    }

    return "redirect:/users";
  }
}
