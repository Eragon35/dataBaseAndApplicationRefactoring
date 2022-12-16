package com.example.bankservice.controller;

import com.example.bankservice.dto.BankTransactionDTO;
import com.example.bankservice.dto.CentralBankDTO;
import com.example.bankservice.service.CentralBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "cb")
public class CentralBankController {


    @Autowired
    CentralBankService service;

    @PutMapping("/bank")
    public void updateFinances(CentralBankDTO dto){
        service.updateBankFinances(dto);
    }

    @PutMapping("/user")
    public void receiveAmount(BankTransactionDTO dto){
        service.updateUserFinances(dto);
    }

}
