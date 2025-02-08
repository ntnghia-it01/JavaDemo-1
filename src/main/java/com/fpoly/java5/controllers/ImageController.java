package com.fpoly.java5.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {
  
  @GetMapping("/upload-image")
  public String uploadImage() {
    return "/upload-image.html";
  }

  @PostMapping("/upload-image")
  public String handleUploadImage(@RequestPart("file") MultipartFile file) {

    // Ten file luu tren may ca nhan
    System.out.println("File name: " + file.getOriginalFilename());

    // Kich thuoc file => byte
    System.out.println("File size: " + file.getSize());

    // Loai file => image/png, image/jpeg, image/gif
    System.out.println("File type: " + file.getContentType());

    // Truy cap vao thu muc images
    Path path = Paths.get("images");

    try {
      // Tao folder neu chua ton tai
      Files.createDirectories(path);

      String fileName = String.format("%s.%s", new Date().getTime(), file.getContentType().split("/")[1]);

      // Luu file vao thu muc images
      Files.copy(file.getInputStream(), path.resolve(fileName));

      // => /images-preview?name=123456.png

      return "redirect:/image-preview?name=" + fileName;

    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return "/upload-image.html";
  }

  @GetMapping("/image-preview")
  public String imagePreview(
    @RequestParam("name") String name,
    Model model
  ) {
    model.addAttribute("name", "/images/" + name);
    return "/image-preview.html";
  }
}
