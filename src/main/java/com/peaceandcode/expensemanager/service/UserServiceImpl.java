package com.peaceandcode.expensemanager.service;

import com.peaceandcode.expensemanager.constant.Role;
import com.peaceandcode.expensemanager.dto.UserDTO;
import com.peaceandcode.expensemanager.entity.User;
import com.peaceandcode.expensemanager.exception.BadRequestException;
import com.peaceandcode.expensemanager.exception.ResourceNotFound;
import com.peaceandcode.expensemanager.mapper.UserMapper;
import com.peaceandcode.expensemanager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final JWTService jwtService;

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
  public UserDTO getLoggedUserDTO() {
    User user = getLoggedUserDetail();
    return userMapper.dto(user);
  }

  @Override
  public User getUserById(Long id) {
    return userRepository.findById(id)
      .orElseThrow(()->new ResourceNotFound("User not found with id: "+id));
  }

  @Override
  public UserDTO getUserDTOById(Long id) {
    User user = getUserById(id);
    return userMapper.dto(user);
  }

  @Override
  public UserDTO updateUserData(UserDTO userDTO) {
    User loggedUser = getLoggedUserDetail();
    boolean isLoggedUserAdmin = loggedUser.getRole().equals(Role.ADMIN);
    User user = isLoggedUserAdmin ? getUserById(userDTO.getId()) : loggedUser;
    String password = user.getPassword();
    Long id = !isLoggedUserAdmin ? loggedUser.getId() : userDTO.getId();

    if( isLoggedUserAdmin && id == null) {
      throw new BadRequestException("You must provide an id to update user data");
    }
    user = userMapper.entity(userDTO);
    user.setId(id);
    user.setPassword(password);

    userRepository.save(user);

    UserDTO retUser = userMapper.dto(user);

    Map<String,Object> claims = new HashMap<>();

    claims.put("name", retUser.getName());
    claims.put("surname",retUser.getSurname());
    claims.put("email",retUser.getEmail());
    claims.put("currency",retUser.getCurrency());
    claims.put("role",retUser.getRole());

    String token = isLoggedUserAdmin ? null : jwtService.generateToken(claims,user);
    retUser.setToken(token);

    return retUser;
  }
}
