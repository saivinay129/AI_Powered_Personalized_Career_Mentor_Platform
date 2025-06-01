package com.backendproject.backend_project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backendproject.backend_project.entity.CareerAdvice;

public interface CareerAdviceRepo extends MongoRepository<CareerAdvice,String>{

}
