package com.fpoly.java5.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fpoly.java5.beans.StudentBean;
import com.fpoly.java5.entities.MajorEntity;

import jakarta.validation.Valid;

@Controller
public class StudentController {

  @GetMapping("/add-student")
  public String addStudent(@ModelAttribute("student") StudentBean studentBean) {
    return "/add-student.html";
  }

  @PostMapping("/add-student")
  public String handleAddStudent(
      @Valid @ModelAttribute("student") StudentBean studentBean,
      Errors errors
    ) {

    // Not run

    return "/add-student.html";
  }


  @ModelAttribute("majors")
  public List<MajorEntity> getMajor(){
    List<MajorEntity> majors = new ArrayList<>();
    majors.add(new MajorEntity(1, "PTPM (Java)"));
    majors.add(new MajorEntity(2, "PTPM (C#)"));
    majors.add(new MajorEntity(3, "Game"));
    majors.add(new MajorEntity(4, "TKTW (FE)"));
    majors.add(new MajorEntity(5, "TKTW (BE)"));

    return majors;
  }
}
