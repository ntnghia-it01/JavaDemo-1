package com.fpoly.java5.jpas;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.java5.entities.CartItemEntity;

public interface CartItemJpa extends JpaRepository<CartItemEntity, Integer>{
  
  // Tìm sản phẩm có thuộc giỏ hàng và giỏ hàng có thuộc sở hữu user hiện tại không?
  @Query(value = "SELECT * FROM cart_items WHERE product_id=?1 AND cart_id=?2", nativeQuery = true)
  public Optional<CartItemEntity> findByProductIdAndCartId(int prodId, int cartId);

  @Query(value = "SELECT * FROM cart_items WHERE id=?1 AND cart_id=?2", nativeQuery = true)
  public Optional<CartItemEntity> findByIdAndCartId(int id, int cartId);
}
