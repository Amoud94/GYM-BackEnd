package com.org.spring.gym.app.repository;

import com.org.spring.gym.app.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
    Boolean existsByRef(String Ref);
    Boolean existsByName(String name);
    Page<Product> findByRefContainingIgnoreCase(String Ref, Pageable pageable);
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Product> findByCategoryContainingIgnoreCase(String category, Pageable pageable);
}
