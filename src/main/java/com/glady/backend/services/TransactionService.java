package com.glady.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glady.backend.entities.Transaction;
import com.glady.backend.entities.TransactionTypeEnum;
import com.glady.backend.repositories.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> mealTransactionList(Long userId) {
        return transactionRepository.findByEmiterIdAndTransactionType(userId,TransactionTypeEnum.meal);
    } 

}
