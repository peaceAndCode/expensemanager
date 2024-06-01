package com.peaceandcode.expensemanager.service;

import com.peaceandcode.expensemanager.entity.User;

public interface UserService {
  User loadUserByUsername(String username);
  User getLoggedUserDetail();
  User getUserById(Long id);
}
