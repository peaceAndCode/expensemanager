package com.peaceandcode.expensemanager.dto;

import com.peaceandcode.expensemanager.constant.Currency;
import com.peaceandcode.expensemanager.constant.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id=null;
    @NotBlank(message = "Name can't be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid name")
    private String name;
    @NotBlank(message = "Surname can't be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid surname")
    private String surname;
    @Column(unique = true)
    @NotBlank(message = "Email can't be blank")
    @Email(message = "Invalid email")
    private String email;
    @NotNull(message = "Currency can't be null")
    private Currency currency;
    @NotNull(message = "Role can't be null")
    private Role role = Role.USER;
    private String token=null;
}
