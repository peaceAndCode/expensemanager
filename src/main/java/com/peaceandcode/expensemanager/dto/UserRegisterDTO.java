package com.peaceandcode.expensemanager.dto;

import com.peaceandcode.expensemanager.constant.Currency;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {
  @NotBlank(message = "Name can't be blank")
  @Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid name")
  private String name;
  @NotBlank(message = "Surname can't be blank")
  @Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid surname")
  private String surname;
  @NotBlank(message = "Email can't be blank")
  @Email(message = "Invalid email")
  private String email;
  @NotBlank(message = "Password can't be blank")
  @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$",message = "invalid password")
  private String password;
  @NotNull(message = "Currency can't be null")
  private Currency currency;
}
