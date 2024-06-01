package com.peaceandcode.expensemanager.exception;

import java.io.Serial;

public class ResourceNotCreated extends RuntimeException{
  @Serial
  private static final long serialVersionUID = 1L;

  public ResourceNotCreated(String message){
    super(message);
  }
}
