package com.org.spring.gym.app.repository;

import java.util.Optional;

import com.org.spring.gym.app.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);
  Page<User> findByCINContainingIgnoreCase(String cin, Pageable pageable);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
  Boolean existsByCIN(String CIN);
}
