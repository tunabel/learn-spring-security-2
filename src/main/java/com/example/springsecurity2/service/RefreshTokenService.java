package com.example.springsecurity2.service;

import com.example.springsecurity2.model.RefreshToken;
import java.util.Optional;
import javax.transaction.Transactional;

public interface RefreshTokenService {

  Optional<RefreshToken> findByToken(String token);

  RefreshToken createRefreshToken(Integer userId);

  RefreshToken verifyExpiration(RefreshToken token);

  @Transactional
  int deleteByUserId(Integer userId);
}
