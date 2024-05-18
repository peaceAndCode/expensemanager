package com.peaceandcode.expensemanager.config;

import com.peaceandcode.expensemanager.service.JWTService;
import com.peaceandcode.expensemanager.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@AllArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
  private final JWTService jwtService;
  private final UserService userService;
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    final String authorization = request.getHeader("Authorization");
    final String jwt;
    final String userEmail;

    if(authorization == null || !authorization.startsWith("Bearer ")){
      filterChain.doFilter(request,response);
      return;
    }
    jwt = authorization.substring(7);
    userEmail = jwtService.getUsername(jwt);

    if(userEmail.isEmpty() || SecurityContextHolder.getContext().getAuthentication() != null){
      filterChain.doFilter(request,response);
      return;
    }

    UserDetails userDetails = userService.loadUserByUsername(userEmail);

    if(jwtService.isTokenValid(jwt,userDetails)){
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        userDetails,
        null,
        userDetails.getAuthorities()
      );

      authenticationToken.setDetails(
        new WebAuthenticationDetailsSource().buildDetails(request)
      );

      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    filterChain.doFilter(request,response);
  }
}
