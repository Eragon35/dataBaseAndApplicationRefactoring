package com.example.bankservice.service;

import com.example.bankservice.entity.User;
import com.example.bankservice.repository.BankTransactionRepository;
import com.example.bankservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserTransactionService {

    @Autowired
    BankTransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;


    @Transactional
    public Double countAllUserTransactionsByUsername(String username){

        Optional<User> userOpt = userRepository.findUserByUsername(username);

        if(userOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found");
        }

        return transactionRepository.countAllByUserId(userOpt.get().getId());
    }


    @Transactional
    public Double countAllUserTTransactionsById(Long userId){
        Optional<User> userOpt = userRepository.findById(userId);

        if(userOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found");
        }

        return transactionRepository.countAllByUserId(userOpt.get().getId());
    }



}
