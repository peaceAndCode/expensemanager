package com.peaceandcode.expensemanager.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JWTService {
  SecretKey getSigningKey();
  <T> T extractClaim(String token, Function<Claims,T> claimResolver);
  String getUsername(String token);
  String generateToken(Map<String,Object> extraClaims, UserDetails userDetails);
  String generateToken(UserDetails userDetails);
  boolean isTokenValid(String token, UserDetails userDetails);
  boolean isTokenExpired(String token);
  Date extractExpirationDate(String token);

}
