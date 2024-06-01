package com.peaceandcode.expensemanager.controller;

import com.peaceandcode.expensemanager.dto.AuthenticationDTO;
import com.peaceandcode.expensemanager.dto.UserLoginDTO;
import com.peaceandcode.expensemanager.dto.UserRegisterDTO;
import com.peaceandcode.expensemanager.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationDTO> register(@Valid @RequestBody UserRegisterDTO user){
   return new ResponseEntity<>(authenticationService.register(user),HttpStatus.OK);
  }
  @PostMapping("/login")
  public ResponseEntity<AuthenticationDTO> login(@Valid @RequestBody UserLoginDTO user){
    return new ResponseEntity<>(authenticationService.login(user),HttpStatus.OK);
  }

}
