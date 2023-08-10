package com.empresa.transportemayor.repository;

import com.empresa.transportemayor.models.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

  Optional<UserEntity> findByUsername(String username);

  Boolean existsByUsername(String username);
}
