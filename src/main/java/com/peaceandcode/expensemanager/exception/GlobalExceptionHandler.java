package com.peaceandcode.expensemanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
@ControllerAdvice
public class GlobalExceptionHandler {
  private static ResponseEntity<ExceptionObj> handleException(HttpStatus httpStatus, Exception ex){
    ExceptionObj exceptionObj = new ExceptionObj();

    exceptionObj.setStatusCode(httpStatus.value());
    exceptionObj.setMessage(ex.getMessage());
    exceptionObj.setTimestamp(new Timestamp(System.currentTimeMillis()));
    exceptionObj.setExceptionName(ex.getClass().getSimpleName());

    return new ResponseEntity<>(exceptionObj,httpStatus);
  }

  @ExceptionHandler(ResourceNotFound.class)
  public ResponseEntity<ExceptionObj> handleResourceNotFound(ResourceNotFound ex, WebRequest request){
    return handleException(HttpStatus.BAD_REQUEST, ex);
  }
  @ExceptionHandler(SecurityFilterException.class)
  public ResponseEntity<ExceptionObj> handleSecurityFilterException(SecurityFilterException ex, WebRequest request){
    return handleException(HttpStatus.UNAUTHORIZED,ex);
  }
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ExceptionObj> handleBadRequestException(BadRequestException ex, WebRequest request){
    return handleException(HttpStatus.BAD_REQUEST,ex);
  }
  @ExceptionHandler(AuthenticationFailed.class)
  public ResponseEntity<ExceptionObj> handleAuthenticationFailed(AuthenticationFailed ex, WebRequest request){
    return handleException(HttpStatus.UNAUTHORIZED,ex);
  }

}
