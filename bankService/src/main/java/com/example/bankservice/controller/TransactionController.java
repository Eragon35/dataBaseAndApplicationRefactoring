package com.example.bankservice.controller;

import com.example.bankservice.dto.BankTransactionDTO;
import com.example.bankservice.entity.BankTransaction;
import com.example.bankservice.service.BankTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "transactions")
public class TransactionController {

    @Autowired
    BankTransactionService bankTransactionService;

    @PostMapping
    public BankTransaction createTransaction(@RequestBody BankTransactionDTO dto) throws Exception {
        return bankTransactionService.create(dto);
    }

    @GetMapping(path = "/{id}")
    public BankTransaction getTransactionById(@PathVariable Long id) throws Exception {
        return bankTransactionService.getById(id);

    }

    @PostMapping(path = "users/{user-id}")
    public List<BankTransaction> getTransactionByUserId(@PathVariable("user-id") String username) {
        return bankTransactionService.getAllByUserName(username);
    }


}
