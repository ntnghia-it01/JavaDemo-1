package com.fpoly.java5.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fpoly.java5.beans.ProductBean;
import com.fpoly.java5.entities.Category;
import com.fpoly.java5.entities.Image;
import com.fpoly.java5.entities.Product;
import com.fpoly.java5.jpas.CategoryJPA;
import com.fpoly.java5.jpas.ImageJPA;
import com.fpoly.java5.jpas.ProductJPA;

@Service
public class ProductService {

  @Autowired
  CategoryJPA categoryJPA;

  @Autowired
  ProductJPA productJPA;

  @Autowired
  ImageJPA imageJPA;

  @Autowired
  ImageService imageService;

  public boolean saveProduct(ProductBean bean){
    try{

      // Convert bean to entity
      Product product = new Product();
      product.setName(bean.getName());
      product.setDesc(bean.getDesc());
      product.setPrice(bean.getPrice());
      product.setQuantity(bean.getQuantity());
      product.setActive(true);
      Optional<Category> cOptional = categoryJPA.findById(bean.getCat_id());
      product.setCategory(cOptional.isPresent() ? cOptional.get() : null);

      Product productSave = productJPA.save(product); // have id product

      for(MultipartFile file : bean.getImages()){
        String fileName = imageService.saveImage(file);
        Image image = new Image();
        image.setImage(fileName);
        image.setProduct(productSave);
        imageJPA.save(image);
      }

    }catch(Exception e){
      return false;
    }

    return true;
  }

  public boolean deleteProduct(int id){
    try{
      imageJPA.deleteByProdId(id);
      productJPA.deleteById(id);
    }catch(Exception e){
      return false;
    }

    return true;
  }
}
