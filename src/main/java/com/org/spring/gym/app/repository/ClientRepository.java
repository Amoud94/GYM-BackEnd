package com.org.spring.gym.app.repository;

import com.org.spring.gym.app.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<Client,String> {
    Boolean existsByCIN(String CIN);
    Boolean existsByEmail(String email);
    Page<Client> findByCINContainingIgnoreCase(String cin, Pageable pageable);
    Page<Client> findByFirstnameContainingIgnoreCase(String firstname, Pageable pageable);
}
