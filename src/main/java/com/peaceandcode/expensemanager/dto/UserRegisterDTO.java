package com.peaceandcode.expensemanager.dto;

import com.peaceandcode.expensemanager.constant.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {
  private String name;
  private String surname;
  private String email;
  private String password;
  private Currency currency;
}
