package com.peaceandcode.expensemanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peaceandcode.expensemanager.constant.Currency;
import com.peaceandcode.expensemanager.constant.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Entity(name = "tbl_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  @NotBlank
  @Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid name")
  private String name;
  @NotBlank
  @Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid surname")
  private String surname;
  @Column(unique = true)
  @NotBlank
  @Email(message = "Invalid email")
  private String email;
  @JsonIgnore
  @NotBlank
  @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$",message = "invalid password")
  private String password;
  @NotNull
  private Currency currency;
  @NotNull
  private Role role;
  @CreationTimestamp
  @Column(name = "created_at")
  private Timestamp createdAt;
  @UpdateTimestamp
  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
