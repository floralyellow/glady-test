package com.glady.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glady.backend.entities.Transaction;
import com.glady.backend.entities.TransactionTypeEnum;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByEmiterIdAndTransactionType(Long emitterId, TransactionTypeEnum transactionType);
    List<Transaction> findByRecieverIdAndTransactionType(Long recieverId, TransactionTypeEnum transactionType);
    
    
}
