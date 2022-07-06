package com.example.springsecurity2.service;

import javax.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {

  @Transactional
  UserDetails loadUserByUsername(String username);
}
