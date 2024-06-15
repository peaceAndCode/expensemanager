package com.peaceandcode.expensemanager.exception;

import lombok.NonNull;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
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
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ExceptionObj> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
    return handleException(HttpStatus.BAD_REQUEST,ex);
  }
  @ExceptionHandler(InternalAuthenticationServiceException.class)
  public ResponseEntity<ExceptionObj> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException ex, WebRequest request){
    return handleException(HttpStatus.UNAUTHORIZED,ex);
  }
  @ExceptionHandler(AuthenticationFailed.class)
  public ResponseEntity<ExceptionObj> handleInternalAuthenticationFailed(AuthenticationFailed ex, WebRequest request){
    return handleException(HttpStatus.UNAUTHORIZED,ex);
  }
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ExceptionObj> handleAccessDeniedException(AccessDeniedException ex, WebRequest request){
    return handleException(HttpStatus.UNAUTHORIZED,ex);
  }
  @ExceptionHandler(ResourceNotCreated.class)
  public ResponseEntity<ExceptionObj> handleResourceNotCreated(ResourceNotCreated ex, WebRequest request){
    return handleException(HttpStatus.BAD_REQUEST,ex);
  }
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ExceptionObj> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,WebRequest request){
    return handleException(HttpStatus.BAD_REQUEST,ex);
  }
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionObj> handleGenericException(Exception ex,WebRequest request){
    return handleException(HttpStatus.INTERNAL_SERVER_ERROR,ex);
  }

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(
    MethodArgumentNotValidException ex,
    @NonNull HttpHeaders headers,
    @NonNull HttpStatusCode status,
    @NonNull WebRequest request
  ) {

    Map<String,Object> body = new HashMap<>();
    List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

    body.put("timestamp", new Timestamp(System.currentTimeMillis()));
    body.put("statusCode",HttpStatus.BAD_REQUEST.value());
    body.put("errors",errors);

    return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
  }

}
