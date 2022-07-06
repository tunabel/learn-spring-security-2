package com.example.springsecurity2.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

  private String password;
  private String username;
  private String email;
  private Set<String> roles;

}
