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

  private String jwt;
  private Integer id;
  private String username;
  private String email;
  private List<String> roles;
}
