package com.example.bankservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "test")
public class TestController {


    @Value("${bank.name}")
    private String bankName;

    @GetMapping
    public ResponseEntity<String> test(){
        return ResponseEntity.ok(bankName);
    }




}
