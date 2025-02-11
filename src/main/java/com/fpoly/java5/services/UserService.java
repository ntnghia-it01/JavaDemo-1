package com.fpoly.java5.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.java5.beans.UserBean;
import com.fpoly.java5.entities.UserEntity;
import com.fpoly.java5.jpas.UserJPA;

@Service
public class UserService {
  
  @Autowired
  UserJPA userJPA;

  @Autowired
  ImageService imageService;

  public String insertUser(UserBean userBean){
    try{

      // Kiểm tra username và email có tồn tại chưa

      List<UserEntity> userEntities = userJPA.findByUsernameAndEmail(userBean.getUsername(), userBean.getEmail());
      if(userEntities.size() > 0){
        return "Username và email đã tồn tại";
      }

      // Lưu file vào project => fileName

      String fileName = imageService.saveImage(userBean.getAvatar());

      if(fileName == null){
        return "Error image";
      }

      // insert
      // convert bean to entity
      // Gson
      UserEntity userEntity = new UserEntity();
      userEntity.setUsername(userBean.getUsername());
      userEntity.setPassword(userBean.getPassword());
      userEntity.setName(userBean.getName());
      userEntity.setEmail(userBean.getEmail());
      userEntity.setAvatar(fileName);
      userEntity.setActive(true);
      userEntity.setRole(1);
      userJPA.save(userEntity);

    }catch(Exception e){
      return "Có lỗi trong quá trình thêm user";
    }

    return null;
  }
}
