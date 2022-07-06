package com.example.springsecurity2.repository;

import com.example.springsecurity2.model.RefreshToken;
import com.example.springsecurity2.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

  @Override
  Optional<RefreshToken> findById(Long id);

  Optional<RefreshToken> findByToken(String token);

  Integer deleteByUser(User user);
}
