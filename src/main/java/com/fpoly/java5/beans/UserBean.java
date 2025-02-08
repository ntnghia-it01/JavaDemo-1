package com.fpoly.java5.beans;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserBean {
  // Không rỗng
  @NotBlank(message = "Username không rỗng")
  private String username;
  // Không rỗng
  // Tối thiểu 6 ký tự
  @NotBlank(message = "Password không rỗng")
  @Length(min = 6, message = "Password tối thiểu 6 ký tự")
  private String password;
  // Không rỗng
  // Đúng định dạng
  @NotBlank(message = "Email không rỗng")
  @Email(message = "Email không đúng định dạng")
  private String email;
  // Không rỗng
  @NotBlank(message = "Name không rỗng")
  private String name;

  // Viết func để kiểm tra lỗi
  // Bắt buộc chọn file
  // Độ lớn tối đa là 20MB
  private MultipartFile avatar; // ll != null

  public String isAvatarError(){

    if(avatar.isEmpty()){
      return "Avatar bắt buộc";
    }

    double size = avatar.getSize() / 1024 / 1024;

    if(size > 20){
      return "Độ lớn tối đa là 20MB";
    }


    return null;
  }
}
