package com.peaceandcode.expensemanager.controller;

import com.peaceandcode.expensemanager.dto.UserDTO;
import com.peaceandcode.expensemanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<UserDTO> getLoggedUserDTO() {
        UserDTO user = userService.getLoggedUserDTO();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Secured("ADMIN")
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO user = userService.getUserDTOById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUserData(userDTO), HttpStatus.OK);
    }
}
