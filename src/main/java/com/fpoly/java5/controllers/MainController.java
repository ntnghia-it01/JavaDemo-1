package com.fpoly.java5.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

//    @GetMapping
//    @PostMapping

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Autowired
    HttpSession session;

    @Autowired
    ServletContext context;

    @GetMapping("/")
    public String home(Model model, @RequestParam(name = "action", defaultValue = "-1") int act) {
//	 TH 1
//	model.addAttribute("msg", request.getParameter("action"));
//	 TH 2
	model.addAttribute("msg", act);

	if (act != -1) {
	    try {
		response.sendRedirect("/user");
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

	return "/index.html";
    }

    @GetMapping("/user")
    public String user() {
	return "/user/user-info.html";
    }

    // Tạo 1 trang có đường dẫn là /user (@GetMapping("/user") va file user.html)
    // Trong trang home thực hiển kiểm tra action
    // Nếu có truyển action lên thì chuyển qua đường dẫn /use redirect
    // Nếu không có truyền thì hiện giao diện ở index
}
