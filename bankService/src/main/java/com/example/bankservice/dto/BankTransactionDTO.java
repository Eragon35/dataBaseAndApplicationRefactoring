package com.example.bankservice.dto;

import lombok.Data;

@Data
public class BankTransactionDTO {
    private String bankOwner;
    private String username;
    private Double amount;
    private String toBank;
    private String toUser;
}
