package com.peaceandcode.expensemanager.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peaceandcode.expensemanager.entity.Category;
import com.peaceandcode.expensemanager.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
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
public class ExpenseDTO {
  @NotBlank(message = "Title can't be blank")
  @Size(min = 2, max = 60,message = "The title must be between 2 and 60 characters")
  private String title;
  private String description;
  @NotNull(message = "Amount can't be null")
  @Min(value = 1,message = "An expense can't have an amount less than 1")
  private Double amount;
  private byte[] receipt=null;
  private  Long userId=null;
  @NotNull(message = "Category can't be null")
  private Long categoryId;

}
