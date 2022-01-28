package com.org.spring.gym.app.repository;

import java.util.Optional;

import com.org.spring.gym.app.models.ERole;
import com.org.spring.gym.app.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
