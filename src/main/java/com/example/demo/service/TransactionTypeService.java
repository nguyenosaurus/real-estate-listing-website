package com.example.demo.service;

import com.example.demo.model.TransactionType;
import com.example.demo.repository.TransactionTypeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionTypeService implements ITransactionTypeService {

    @Autowired
    private TransactionTypeRepository repository;

    @Override
    public List<TransactionType> findAll() {

        List<TransactionType> transaction_types = (List<TransactionType>) repository.findAll();

        return transaction_types;
    }
}