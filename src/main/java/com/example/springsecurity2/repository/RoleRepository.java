package com.example.springsecurity2.repository;

import com.example.springsecurity2.constant.RoleEnum;
import com.example.springsecurity2.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

  Optional<Role> findByName(RoleEnum name);

}
