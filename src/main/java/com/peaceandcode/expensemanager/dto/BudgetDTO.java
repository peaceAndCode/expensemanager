package com.peaceandcode.expensemanager.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peaceandcode.expensemanager.entity.Category;
import com.peaceandcode.expensemanager.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;
@Data
public class BudgetDTO {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  @NotNull(message = "Budget amount can't be null")
  @Min(value = 1, message = "Budget amount can't be less than 1")
  private Double amount;
  @NotNull(message = "Budget date start can't be null")
  private Date start;
  @NotNull(message = "Budget date end can't be null")
  private Date end;
  private Long userId=null;
  @NotNull
  private Long categoryId;

}
