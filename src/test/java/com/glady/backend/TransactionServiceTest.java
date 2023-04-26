package com.glady.backend;

import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.glady.backend.services.TransactionService;
import com.glady.backend.entities.Account;
import com.glady.backend.entities.BalanceResponse;
import com.glady.backend.entities.Transaction;
import com.glady.backend.entities.TransactionTypeEnum;
import com.glady.backend.repositories.AccountRepository;
import com.glady.backend.repositories.TransactionRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionServiceTest {

    @Autowired
    private TransactionService myService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    Account company;
    Account user;
    Account userBis;

    @BeforeAll
    public void setUp() {
        // create Company
        company = new Account("company", true);
        accountRepository.save(company);
        // create Users
        user = new Account("user", false);
        accountRepository.save(user);
        userBis = new Account("user 2", false);
        accountRepository.save(userBis);
        // create Transactions
        Transaction refillCompanyCard = new Transaction(TransactionTypeEnum.personnalAccount, company,200000);
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(0, refillCompanyCard);
        transactions.add(0, new Transaction(TransactionTypeEnum.meal, company,userBis,15000));
        transactionRepository.saveAll(transactions);
    }
    @Test
    public void testGetCompanyBalanceOK() {
        int balance = myService.getBalanceOfAccount(company.getId(), TransactionTypeEnum.personnalAccount).orElse(0);
        assertEquals(200000, balance);
    }

    @Test
    public void testTransfertMoneyFromCompanyToUserAccountOK() {
        myService.transfertMoneyFromCompanyToUserAccount(
            user.getId(),
            company.getId(),
            TransactionTypeEnum.gift,
            30000
        );
        assertEquals(3, transactionRepository.findAll().size());
    }

    @Test
    public void testTransfertMoneyFromCompanyToUserAccountKO() {
        assertEquals(2, transactionRepository.findAll().size());
            myService.transfertMoneyFromCompanyToUserAccount(
            user.getId(),
            company.getId(),
            TransactionTypeEnum.gift,
            300000
        );
        assertEquals(2, transactionRepository.findAll().size());
    }

    @Test
    public void testTotalBalanceByUserIdWithValidId() {
        BalanceResponse balance = myService.getTotalBalanceByUserId(userBis.getId()).get();
        assertEquals(0, balance.getGiftBalance());
        assertEquals(16000, balance.getMealBalance());
    }

    @Test
    public void testGetTotalBalanceByUserIdWithNotValidId() {
        Optional<BalanceResponse> balance = myService.getTotalBalanceByUserId(company.getId());
        assertFalse(balance.isPresent());
    }

    @Test
    public void testRefillMealBalanceAndGetMealBalanceOK() {
        int balanceBefore = myService.getBalanceOfAccount(userBis.getId(), TransactionTypeEnum.meal).orElse(0);
        assertEquals(15000, balanceBefore);
        myService.transfertMoneyFromCompanyToUserAccount(
            userBis.getId(),
            company.getId(),
            TransactionTypeEnum.meal,
            1000
        );
        int balanceAfter = myService.getBalanceOfAccount(userBis.getId(), TransactionTypeEnum.meal).orElse(0);
        assertEquals(16000, balanceAfter);
    }

    @Test
    public void testGetGiftBalance() {
        int balance = myService.getBalanceOfAccount(userBis.getId(), TransactionTypeEnum.gift).orElse(0);
        assertEquals(0, balance);
    }


    @AfterAll
    void tearDown() {
        accountRepository.deleteAll();
        transactionRepository.deleteAll();
    }
}
