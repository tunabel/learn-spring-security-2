package com.example.springsecurity2.service.impl;

import com.example.springsecurity2.constant.RoleEnum;
import com.example.springsecurity2.dto.JwtResponse;
import com.example.springsecurity2.dto.LoginRequest;
import com.example.springsecurity2.dto.SignupRequest;
import com.example.springsecurity2.dto.TokenRefreshRequest;
import com.example.springsecurity2.dto.TokenRefreshResponse;
import com.example.springsecurity2.exception.InvalidRequestException;
import com.example.springsecurity2.exception.TokenRefreshException;
import com.example.springsecurity2.model.RefreshToken;
import com.example.springsecurity2.model.Role;
import com.example.springsecurity2.model.User;
import com.example.springsecurity2.repository.RoleRepository;
import com.example.springsecurity2.repository.UserRepository;
import com.example.springsecurity2.security.dto.UserDetailsImpl;
import com.example.springsecurity2.security.jwt.JwtUtils;
import com.example.springsecurity2.service.RefreshTokenService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthServiceImpl implements AuthService {

  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  UserRepository userRepository;
  @Autowired
  RoleRepository roleRepository;
  @Autowired
  PasswordEncoder encoder;
  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  RefreshTokenService refreshTokenService;

  @Override
  public JwtResponse getJwtFromLoginRequest(LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String username = (String) authentication.getPrincipal();
    UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(username);

    String jwt = jwtUtils.generateJwtToken(authentication);

    List<String> roles = userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList());

    RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

    return new JwtResponse(jwt,
        userDetails.getId(),
        userDetails.getUsername(),
        userDetails.getEmail(),
        roles,
        refreshToken.getToken());
  }

  @Override
  public Integer registerUser(SignupRequest signUpRequest) {
    if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
      throw new InvalidRequestException("Username already exists");
    }
    if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
      throw new InvalidRequestException("Email is already in use");
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
        signUpRequest.getEmail(),
        encoder.encode(signUpRequest.getPassword()));
    Set<String> strRoles = signUpRequest.getRoles();
    Set<Role> roles = new HashSet<>();
    if (strRoles == null) {
      Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
          .orElseThrow(() -> new InvalidRequestException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        RoleEnum roleEnum = RoleEnum.valueOfLabel(role);
        Role userRole = roleRepository.findByName(roleEnum)
            .orElseThrow(() -> new InvalidRequestException("Error: Role is not found."));
        roles.add(userRole);
      });
    }
    user.setRoles(roles);
    userRepository.save(user);
    return user.getId();
  }

  @Override
  public TokenRefreshResponse generateJwtFromRefreshTokenRequest(TokenRefreshRequest request) {

    String requestRefreshToken = request.getRefreshToken();
    return refreshTokenService.findByToken(requestRefreshToken)
        .map(refreshTokenService::verifyExpiration)
        .map(RefreshToken::getUser)
        .map(user -> {
          String token = jwtUtils.generateTokenFromUsername(user.getUsername());
          return new TokenRefreshResponse(token, requestRefreshToken);
        })
        .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
            "Refresh token is not in database!"));
  }
}
