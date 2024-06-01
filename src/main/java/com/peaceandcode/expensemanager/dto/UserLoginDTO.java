package com.peaceandcode.expensemanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
  @NotBlank(message = "Username can't be blank")
  private String username;
  @NotBlank(message = "Password can't be blank")
  private String password;
}
