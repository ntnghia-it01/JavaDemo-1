package com.fpoly.java5.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "username", length = 100, nullable = false)
  private String username;

  @Column(name = "password", length = 255, nullable = false)
  private String password;

  @Column(name = "name", nullable = false, columnDefinition = "nvarchar(255)")
  private String name;

  @Column(name = "email", length = 100, nullable = false)
  private String email;

  @Column(name = "avatar", length = 255, nullable = false)
  private String avatar;

  @Column(name = "is_active", nullable = false)
  private boolean isActive;

  @Column(name = "role", nullable = false)
  private int role;
}