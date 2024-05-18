package com.peaceandcode.expensemanager.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionObj {
  private int statusCode;
  private String message;
  private Timestamp timestamp;
  private String exceptionName;
}
