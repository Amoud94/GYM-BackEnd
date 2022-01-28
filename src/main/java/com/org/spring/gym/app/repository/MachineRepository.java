package com.org.spring.gym.app.repository;

import com.org.spring.gym.app.models.Machine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends MongoRepository<Machine,String> {
    Boolean existsByRef(String Ref);
    Page<Machine> findByRefContainingIgnoreCase(String Ref, Pageable pageable);
    Page<Machine> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
