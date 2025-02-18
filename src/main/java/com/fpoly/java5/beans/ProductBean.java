package com.fpoly.java5.beans;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductBean {
  private String name;
  private String desc;
  private int price;
  private int quantity;
  private int cat_id;
  private List<MultipartFile> images;
}
