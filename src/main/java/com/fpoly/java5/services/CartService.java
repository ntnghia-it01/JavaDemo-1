package com.fpoly.java5.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.java5.entities.CartEntity;
import com.fpoly.java5.entities.CartItemEntity;
import com.fpoly.java5.entities.Product;
import com.fpoly.java5.entities.UserEntity;
import com.fpoly.java5.jpas.CartItemJpa;
import com.fpoly.java5.jpas.CartJPA;
import com.fpoly.java5.jpas.ProductJPA;
import com.fpoly.java5.jpas.UserJPA;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class CartService {

  @Autowired
  HttpServletRequest request;

  @Autowired
  CartJPA cartJPA;

  @Autowired
  UserJPA userJPA;

  @Autowired
  CartItemJpa cartItemJPA;

  @Autowired
  ProductJPA productJPA;

  private UserEntity getUser(){
    Cookie[] cookies = request.getCookies();
    if(cookies == null){
      return null;
    }
    for(Cookie cookie : cookies){
      if(cookie.getName().equals("user_id")){
        Optional<UserEntity> userOptional = userJPA.findById(Integer.parseInt(cookie.getValue()));
        return userOptional.isPresent() ? userOptional.get() : null;
      }
    }
    return null;
  }

  private CartEntity getCart(){
    try{
      UserEntity userEntity = getUser();
      if(userEntity != null && userEntity.getCart() != null){
        return userEntity.getCart();
      }
      CartEntity cartEntity = new CartEntity();
      cartEntity.setUser(userEntity);
      return cartJPA.save(cartEntity);
    }catch(Exception e){
      return null;
    }
  }
  
  public List<CartItemEntity> getList(){
    CartEntity cartEntity = getCart();
    return cartEntity != null ? cartEntity.getCartItems() : new ArrayList<>();
  }

  public boolean addToCart(int prodId){
    try{
      CartEntity cartEntity = getCart();
      Optional<CartItemEntity> cartItemOptional = cartItemJPA.findByProductIdAndCartId(prodId, cartEntity.getId());

      if(cartItemOptional.isPresent()){
        // Cập nhật lại số lượng sp trong giỏ hàng
        CartItemEntity cartItemEntity = cartItemOptional.get();
        cartItemEntity.setQuantity(cartItemOptional.get().getQuantity() + 1);
        cartItemJPA.save(cartItemEntity);
      }else{
        // Thêm mới
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setCart(cartEntity);
        Optional<Product> productOptional = productJPA.findById(prodId);
        if(!productOptional.isPresent()){
          return false;
        }
        cartItemEntity.setProduct(productOptional.get());
        cartItemEntity.setQuantity(1);
        cartItemJPA.save(cartItemEntity);
      }

    }catch(Exception e){
      return false;
    }

    // Cách 1: 
    // Viết lệnh sql ở JPA
    // SELECT * FROM cart_items WHERE product_id=?1 AND cart_id=?2
    // => Optional

    // Cách 2:
    // cartEntity.getCartItems() => item list
    // for => list => product_id == prodId
    //

    // => Tính độ phức tạp của code => O(1) -> O(n)

    // TH 1: sản phẩm chưa có trong giỏ hàng
    // Insert 1 sản phẩm mới với số lượng là 1
    // TH 2: Sản phẩm đã có trong giỏ hàng
    // Update số cart item theo sản phẩm và tăng số lượng 
    // lên 1

    return true;
  }

  public boolean deleteCartItem(int cartItemId){
    try{
      // Cart item id có thuộc sở hữu của user hiện tại không?
      CartEntity cartEntity = getCart();
      // Có cart_id và cart item id
      // Viết lệnh JPA
      // SELECT * FROM cart_items WHERE id=?1 AND cart_id=?2

      Optional<CartItemEntity> cartItemOptional = cartItemJPA.findByIdAndCartId(cartItemId, cartEntity.getId());
      if(!cartItemOptional.isPresent()){
        return false;
      }

      cartItemJPA.deleteById(cartItemId);
    }catch(Exception e){
      return false;
    }
    return true;
  }

  public boolean updateQuantityCartItem(int cartItemId, int quantity){

    try{
      CartEntity cartEntity = getCart();
      Optional<CartItemEntity> cartItemOptional = cartItemJPA.findByIdAndCartId(cartItemId, cartEntity.getId());
      if(!cartItemOptional.isPresent()){
        return false;
      }

      if(quantity <= 0){
        deleteCartItem(cartItemId);
        return true;
      }

      CartItemEntity cartItemEntity = cartItemOptional.get();
      cartItemEntity.setQuantity(quantity);
      cartItemJPA.save(cartItemEntity);
    }catch(Exception e){
      return false;
    }
    return true;
  }
}
