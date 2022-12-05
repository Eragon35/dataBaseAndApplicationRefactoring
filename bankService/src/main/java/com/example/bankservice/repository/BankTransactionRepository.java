package com.example.bankservice.repository;

import com.example.bankservice.entity.BankTransaction;
import com.example.bankservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long> {
    List<BankTransaction> findAllByUser(User user);
}