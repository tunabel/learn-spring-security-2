package com.example.springsecurity2.service;

import com.example.springsecurity2.dto.JwtResponse;
import com.example.springsecurity2.dto.LoginRequest;
import com.example.springsecurity2.dto.SignupRequest;
import com.example.springsecurity2.dto.TokenRefreshRequest;
import com.example.springsecurity2.dto.TokenRefreshResponse;

public interface AuthService {

  JwtResponse getJwtFromLoginRequest(LoginRequest loginRequest);

  Integer registerUser(SignupRequest signUpRequest);

  TokenRefreshResponse generateJwtFromRefreshTokenRequest(TokenRefreshRequest request);
}
