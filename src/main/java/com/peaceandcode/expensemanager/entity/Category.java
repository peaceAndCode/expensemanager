package com.peaceandcode.expensemanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  @NotBlank(message = "Category name can't be blank")
  @Pattern(regexp = "^[a-zA-Z]+$", message = "Category name")
  private String name;
  @CreationTimestamp
  @Column(name = "created_at")
  private Timestamp createdAt;
  @UpdateTimestamp
  @Column(name = "updated_at")
  private Timestamp updatedAt;
}
