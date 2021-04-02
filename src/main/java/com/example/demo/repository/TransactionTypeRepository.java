package com.example.demo.repository;

import com.example.demo.model.TransactionType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository extends CrudRepository<TransactionType, Long> {

}