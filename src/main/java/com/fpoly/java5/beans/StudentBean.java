package com.fpoly.java5.beans;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentBean {
  // Không rỗng
  @NotBlank(message = "Tên không được để trống")
  private String name;
  // Không rỗng
  // Đúng định dạng
  @NotBlank(message = "Email không được để trống")
  @Email(message = "Email không đúng định dạng")
  private String email;
  // Không rỗng
  // Phải có 10 ký tự
  @NotBlank(message = "Số điện thoại không được để trống")
  @Length(min = 10, max = 10, message = "Số điện thoại phải có 10 ký tự")
  private String phone;
  // Bắt buộc chọn
  @Min(value = 1, message = "Giới tính bắt buộc chọn")
  private int gender;
  // Nằm trong khoản từ 0 -> 10
  @Range(min = 0, max = 10, message = "Điểm phải nằm trong khoản từ 0 -> 10")
  private double point;
  // Bắt buộc chọn
  @Min(value = 0, message = "Ngành học bắt buộc chọn")
  private int major;
}
