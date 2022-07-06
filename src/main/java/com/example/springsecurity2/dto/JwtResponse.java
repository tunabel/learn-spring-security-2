package com.example.springsecurity2.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

  private String token;
  private Integer id;
  private String username;
  private String email;
  private List<String> roles;

  private String type = "Bearer";
  private String refreshToken;

  public JwtResponse(String token, Integer id, String username, String email, List<String> roles,
      String refreshToken) {
    this.token = token;
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles;
    this.refreshToken = refreshToken;
  }
}
