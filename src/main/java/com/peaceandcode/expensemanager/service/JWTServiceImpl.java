package com.peaceandcode.expensemanager.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JWTServiceImpl implements JWTService{
  private static final String SECRET_KEY = "741e946218c49b516286785ccd22e7de34ed13cf01cc2c0a3637827dd25dbace";
  @Override
  public SecretKey getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    Key key = Keys.hmacShaKeyFor(keyBytes);
    return new SecretKeySpec(key.getEncoded(),key.getAlgorithm());
  }

  @Override
  public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
    Claims claims = extractAllClaims(token);
    return claimResolver.apply(claims);
  }

  private Claims extractAllClaims(String jwt){
    return Jwts
      .parser()
      .verifyWith(getSigningKey())
      .build()
      .parseSignedClaims(jwt)
      .getPayload();
  }

  @Override
  public String getUsername(String token) {
    return extractClaim(token,Claims::getSubject);
  }

  @Override
  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return Jwts
      .builder()
      .claims(extraClaims)
      .issuedAt(new Date(System.currentTimeMillis()))
      .expiration(new Date(System.currentTimeMillis()))
      .subject(userDetails.getUsername())
      .signWith(getSigningKey())
      .compact();
  }

  @Override
  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  @Override
  public boolean isTokenValid(String token, UserDetails userDetails) {
    String tokenUsername = getUsername(token);
    return userDetails.getUsername().equals(tokenUsername) && !isTokenExpired(token);
  }

  @Override
  public boolean isTokenExpired(String token) {
    return extractExpirationDate(token).before(new Date(System.currentTimeMillis()));
  }

  @Override
  public Date extractExpirationDate(String token) {
    return extractClaim(token,Claims::getExpiration);
  }
}
