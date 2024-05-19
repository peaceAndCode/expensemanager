package com.peaceandcode.expensemanager.service;

import com.peaceandcode.expensemanager.constant.Role;
import com.peaceandcode.expensemanager.dto.AuthenticationDTO;
import com.peaceandcode.expensemanager.dto.UserLoginDTO;
import com.peaceandcode.expensemanager.dto.UserRegisterDTO;
import com.peaceandcode.expensemanager.entity.User;
import com.peaceandcode.expensemanager.exception.AuthenticationFailed;
import com.peaceandcode.expensemanager.exception.BadRequestException;
import com.peaceandcode.expensemanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JWTService jwtService;
  private final AuthenticationManager authenticationManager;
  @Override
  public AuthenticationDTO register(UserRegisterDTO user) {
    User newUser = User
      .builder()
      .name(user.getName())
      .surname(user.getSurname())
      .email(user.getEmail())
      .password(passwordEncoder.encode(user.getPassword()))
      .currency(user.getCurrency())
      .role(Role.USER)
      .build();

    if(user.getEmail().equals(newUser.getEmail())){
      throw new BadRequestException("Username already exists");
    }

    try{
      userRepository.save(newUser);
    }catch (Exception ex){
      throw new BadRequestException("Error during user saving: "+ex.getMessage());
    }
    return AuthenticationDTO
      .builder()
      .token(jwtService.generateToken(generateClaims(newUser),newUser))
      .build();

  }

  private static Map<String, Object> generateClaims(User user){
    Map<String,Object> claims = new HashMap<>();

    claims.put("name",user.getName());
    claims.put("surname",user.getSurname());
    claims.put("email",user.getEmail());
    claims.put("currency",user.getCurrency());
    claims.put("role",user.getRole());

    return claims;
  }

  @Override
  public AuthenticationDTO login(UserLoginDTO user) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
    );

    if(authentication.isAuthenticated()){
      User userDetails = userRepository.findByEmail(user.getUsername())
        .orElseThrow(()->new AuthenticationFailed("Authentication failed for username: "+user.getUsername()));

      return AuthenticationDTO
        .builder()
        .token(jwtService.generateToken(generateClaims(userDetails),userDetails))
        .build();
    }

    throw new AuthenticationFailed("Authentication failed for username: "+user.getUsername());
  }
}
