package com.fpoly.java5.jpas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.java5.entities.UserEntity;

public interface UserJPA extends JpaRepository<UserEntity, Integer>{

  @Query(name = "SELECT * FROM users WHERE username=?1 OR email=?2", nativeQuery = true)
  public List<UserEntity> checkUsernameAndEmail(String username, String email);
}
