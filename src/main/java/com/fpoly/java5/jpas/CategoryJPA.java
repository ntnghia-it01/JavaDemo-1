package com.fpoly.java5.jpas;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.java5.entities.Category;

public interface CategoryJPA extends JpaRepository<Category, Integer>{
  
}
