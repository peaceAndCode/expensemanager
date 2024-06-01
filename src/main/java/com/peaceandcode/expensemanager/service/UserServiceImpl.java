package com.peaceandcode.expensemanager.service;

import com.peaceandcode.expensemanager.entity.User;
import com.peaceandcode.expensemanager.exception.ResourceNotFound;
import com.peaceandcode.expensemanager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
  private final UserRepository userRepository;
  @Override
  public User loadUserByUsername(String username) {
    return userRepository.findByEmail(username)
      .orElseThrow(()-> new ResourceNotFound("User not found with username: "+username));
  }

  @Override
  public User getLoggedUserDetail() {
    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
    return loadUserByUsername(userName);
  }

  @Override
  public User getUserById(Long id) {
    return userRepository.findById(id)
      .orElseThrow(()->new ResourceNotFound("User not found with id: "+id));
  }
}
