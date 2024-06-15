package com.peaceandcode.expensemanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "tbl_budget")
public class Budget {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "budget_sequence")
  @SequenceGenerator(name = "budget_sequence",sequenceName = "budget_sequence",allocationSize = 1)
  private Long id;
  @NotNull(message = "Budget amount can't be null")
  @Min(value = 1, message = "Budget amount can't be less than 1")
  private Double amount;
  @NotNull(message = "Budget date start can't be null")
  @Column(name = "startDate")
  private Date start;
  @NotNull(message = "Budget date end can't be null")
  @Column(name = "endDate")
  private Date end;
  @ManyToOne
  @JoinColumn(name = "user_id",nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private User user;
  @ManyToOne
  @JoinColumn(name = "category_id",nullable = false)
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
