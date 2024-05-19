package com.peaceandcode.expensemanager.exception;

public class AuthenticationFailed extends RuntimeException{
  private static final long serialVersionUID = 1L;
  public AuthenticationFailed(String message){
    super(message);
  }
}
