package com.example.demo.service;

import com.example.demo.model.TransactionType;

import java.util.List;

public interface ITransactionTypeService {

    List<TransactionType> findAll();
}