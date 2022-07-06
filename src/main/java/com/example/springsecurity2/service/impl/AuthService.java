package com.example.springsecurity2.service.impl;

import com.example.springsecurity2.dto.JwtResponse;
import com.example.springsecurity2.dto.LoginRequest;
import com.example.springsecurity2.dto.SignupRequest;

public interface AuthService {

  JwtResponse getJwtFromLoginRequest(LoginRequest loginRequest);

  Integer registerUser(SignupRequest signUpRequest);
}
