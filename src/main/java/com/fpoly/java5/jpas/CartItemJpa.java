package com.fpoly.java5.jpas;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.java5.entities.CartItemEntity;

public interface CartItemJpa extends JpaRepository<CartItemEntity, Integer>{
  
}
