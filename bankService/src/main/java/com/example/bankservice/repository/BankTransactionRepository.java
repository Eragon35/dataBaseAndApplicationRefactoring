package com.example.bankservice.repository;

import com.example.bankservice.entity.BankTransaction;
import com.example.bankservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long> {
    List<BankTransaction> findAllByUser(User user);

    @Query(value = "select sum(amount) from bank_transaction where user_id=?1", nativeQuery = true)
    Double countAllByUserId(Long userId);


}