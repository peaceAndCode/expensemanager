package com.peaceandcode.expensemanager.service;

import com.peaceandcode.expensemanager.dto.UserDTO;
import com.peaceandcode.expensemanager.entity.User;

public interface UserService {
  User loadUserByUsername(String username);
  User getLoggedUserDetail();
  UserDTO getLoggedUserDTO();
  User getUserById(Long id);
  UserDTO getUserDTOById(Long id);
  UserDTO updateUserData(UserDTO userDTO);
}
