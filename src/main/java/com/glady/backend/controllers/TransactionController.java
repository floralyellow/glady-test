package com.glady.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.glady.backend.services.TransactionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = {"/get-balance"}, method = RequestMethod.POST)
    public void getBalance(@RequestBody Long userId) {
        this.transactionService.mealTransactionList(userId);
        
    }

    @RequestMapping(value = {"/send-money-to-account"}, method = RequestMethod.POST)
    public Optional<String> getMoviesAndActors(@RequestBody Long userId) {
        return Optional.of("Hello World");
    }


    @RequestMapping(value = {"/test"}, method = RequestMethod.GET)
    public Optional<String> test() {
        return Optional.of("Hello World");
    }

}
