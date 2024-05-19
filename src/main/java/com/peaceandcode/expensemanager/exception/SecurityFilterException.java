package com.peaceandcode.expensemanager.exception;

import java.io.Serial;

public class SecurityFilterException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;

  public SecurityFilterException(String message){
    super(message);
  }
}
