package com.example.springsecurity2.service.impl;

import com.example.springsecurity2.model.User;
import com.example.springsecurity2.repository.UserRepository;
import com.example.springsecurity2.security.dto.UserDetailsImpl;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  @Transactional
  public UserDetailsImpl loadUserByUsername(String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not Found with username: " + username));
    return UserDetailsImpl.build(user);
  }
}
