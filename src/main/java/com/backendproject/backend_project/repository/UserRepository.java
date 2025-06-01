package com.backendproject.backend_project.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backendproject.backend_project.entity.User;

public interface UserRepository extends MongoRepository<User, String>{
    Optional<User> findByEmail(String email);
}
