package com.glady.backend.services;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.glady.backend.entities.Account;
import com.glady.backend.entities.BalanceResponse;
import com.glady.backend.entities.Transaction;
import com.glady.backend.entities.TransactionTypeEnum;
import com.glady.backend.repositories.AccountRepository;
import com.glady.backend.repositories.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    public Optional<Integer> getMealBalance(Long userId) {
        return Optional.of(transactionRepository
        .findByEmiterIdAndTransactionType(userId,TransactionTypeEnum.meal)
        .stream()
        .filter((transaction) -> {
            return LocalDateTime.now().isAfter(transaction.getExpirationDate());
        })
        .map((transaction) -> {
            return transaction.getTransactionAmount();
        })
        .reduce(0, (subtotal, element) -> subtotal + element));
        
    } 

    public Optional<Integer> getBalanceOfAccount(Long userId, TransactionTypeEnum transactionType) {
        return Optional.of(
            transactionRepository
            .findByRecieverIdAndTransactionType(userId,transactionType)
            .stream()
            .filter((transaction) -> {
                System.out.println(transaction);
                if (transactionType != TransactionTypeEnum.personnalAccount)
                    return LocalDateTime.now().isBefore(transaction.getExpirationDate());
                return true;
            })
            .map((transaction) -> {
                return transaction.getTransactionAmount();
            })
            .reduce(0, (subtotal, element) -> subtotal + element) -
            transactionRepository
            .findByEmiterIdAndTransactionType(userId,transactionType)
            .stream()
            .filter((transaction) -> {
                return LocalDateTime.now().isAfter(transaction.getExpirationDate());
            })
            .map((transaction) -> {
                return transaction.getTransactionAmount();
            })
            .reduce(0, (subtotal, element) -> subtotal + element)
        );
        
    } 
    
    public Optional<BalanceResponse> getTotalBalanceByUserId(Long userId){
        Account user = accountRepository.findById(userId).orElse(null);
        if (user != null && user.isCompany() != true) {
            int giftBalance = this.getBalanceOfAccount(userId, TransactionTypeEnum.gift).orElse(0);
            int mealBalance = this.getBalanceOfAccount(userId, TransactionTypeEnum.meal).orElse(0);
            return Optional.of(new BalanceResponse(giftBalance,mealBalance));
        }
        return Optional.empty();
    }

    public ResponseEntity<?> transfertMoneyFromCompanyToUserAccount(Long userId, Long companyId, TransactionTypeEnum transactionType, int transactionAmount) {
        if(transactionType == TransactionTypeEnum.meal || transactionType == TransactionTypeEnum.gift) {
            Account company = accountRepository.findById(companyId).orElse(null);
            if (company != null && company.isCompany() != false) {
                Account user = accountRepository.findById(userId).orElse(null);
                if (user != null && user.isCompany() != true) {
                    int currentCompanyBalance = getBalanceOfAccount(company.getId(),TransactionTypeEnum.personnalAccount).get();
                    if(transactionAmount <= currentCompanyBalance) {
                        Transaction transaction = new Transaction(transactionType, company, user, transactionAmount);
                        transactionRepository.save(transaction);
                        return ResponseEntity.ok(transaction);
                    }
                    return ResponseEntity.badRequest().body( "There isn't enough balance in this company account, please refill it !");
                }
                return ResponseEntity.badRequest().body( "The user account provided isn t of a user or cannot be found !");
            }
            return ResponseEntity.badRequest().body( "The company account provided isn t of a company or cannot be found !");
        }
        return ResponseEntity.badRequest().body( "This method cannot be used to refill company account !");
    } 

}
