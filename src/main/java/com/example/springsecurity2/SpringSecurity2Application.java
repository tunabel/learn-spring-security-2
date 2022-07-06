package com.example.springsecurity2;

import com.example.springsecurity2.constant.RoleEnum;
import com.example.springsecurity2.model.Role;
import com.example.springsecurity2.repository.RoleRepository;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurity2Application implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(SpringSecurity2Application.class, args);
  }


  @Autowired
  private RoleRepository repository;

  @Override
  public void run(String... args) throws Exception {
    if (repository.count() > 0) {
      return;
    }

    List<Role> roles = Arrays.stream(RoleEnum.values()).map(role -> new Role(role)).collect(
        Collectors.toList());
    repository.saveAll(roles);
  }
}
