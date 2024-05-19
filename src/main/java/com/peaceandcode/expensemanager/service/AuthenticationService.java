package com.peaceandcode.expensemanager.service;

import com.peaceandcode.expensemanager.dto.AuthenticationDTO;
import com.peaceandcode.expensemanager.dto.UserLoginDTO;
import com.peaceandcode.expensemanager.dto.UserRegisterDTO;

public interface AuthenticationService {
  AuthenticationDTO register(UserRegisterDTO user);
  AuthenticationDTO login (UserLoginDTO user);
}
