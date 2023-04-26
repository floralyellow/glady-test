package com.glady.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.glady.backend.entities.BalanceResponse;
import com.glady.backend.entities.TransactionTypeEnum;
import com.glady.backend.services.TransactionService;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = {"/get-balance"}, method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<?> getBalance(@RequestBody Long userId) {
        Optional<BalanceResponse> test = this.transactionService.getTotalBalanceByUserId(userId);
        if (test.isPresent()){
            return ResponseEntity.ok(test.get());
        }
        return ResponseEntity.badRequest().body("The given id is not a valid user id !");
    }

    @RequestMapping(value = {"/send-money-to-account"}, method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<?> sendMoneyToAccount(@RequestBody Long userId, @RequestBody  Long companyId, @RequestBody TransactionTypeEnum type, @RequestBody int amount) {
        return this.transactionService.transfertMoneyFromCompanyToUserAccount(userId, companyId, type, amount);
    }


    @RequestMapping(value = {"/test"}, method = RequestMethod.GET)
    public Optional<String> test() {
        return Optional.of("Hello World");
    }

}
