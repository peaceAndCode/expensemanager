package com.peaceandcode.expensemanager.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Service
public class JWTServiceImpl implements JWTService{
  private final String secretKey;

  public JWTServiceImpl(@Value("${jwt.secret}") String secretKey) {
    this.secretKey = secretKey;
  }
  @Override
  public SecretKey getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
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
      .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
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
