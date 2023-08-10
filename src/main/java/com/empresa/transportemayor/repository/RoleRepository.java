package com.empresa.transportemayor.repository;

import com.empresa.transportemayor.models.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

  Optional<Role> findByName(String name);
}
