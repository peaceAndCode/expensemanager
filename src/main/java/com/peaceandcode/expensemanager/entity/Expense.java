package com.peaceandcode.expensemanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "tbl_expense")
public class Expense {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "expense_sequence")
  @SequenceGenerator(name = "expense_sequence",sequenceName = "expense_sequence",allocationSize = 1)
  private Long id;
  @NotBlank(message = "Title can't be blank")
  @Size(min = 2, max = 60,message = "The title must be between 2 and 60 characters")
  private String title;
  private String description;
  @NotNull(message = "Amount can't be null")
  @Min(value = 1,message = "An expense can't have an amount less than 1")
  private Double amount;
  @Lob
  private byte[] receipt;
  @ManyToOne
  @JoinColumn(name = "user_id",nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private User user;
  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Category category;
  @Column(name = "created_at")
  @CreationTimestamp
  private Timestamp createdAt;
  @Column(name = "updated_at")
  @UpdateTimestamp
  private Timestamp updatedAt;
}
