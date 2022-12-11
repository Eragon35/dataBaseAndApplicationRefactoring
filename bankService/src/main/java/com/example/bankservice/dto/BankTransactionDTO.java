package com.example.bankservice.dto;

import lombok.Data;

@Data
public class BankTransactionDTO {
    private String fromBank;
    private String fromUser;
    private Double amount;
    private String toBank;
    private String toUser;
}
