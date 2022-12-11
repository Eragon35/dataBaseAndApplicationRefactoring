package com.example.bankservice.controller;

import com.example.bankservice.service.UserTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "user")
public class UserTransactionController {

    @Autowired
    UserTransactionService userTransactionService;

    @PostMapping("{id}/transactions")
    public Double countAllUserTransactions(@PathVariable Long id){
        return userTransactionService.countAllUserTTransactionsById(id);
    }
}
