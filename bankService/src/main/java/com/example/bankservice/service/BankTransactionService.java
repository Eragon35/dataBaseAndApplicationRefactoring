package com.example.bankservice.service;

import com.example.bankservice.dto.BankTransactionDTO;
import com.example.bankservice.entity.BankTransaction;
import com.example.bankservice.entity.User;
import com.example.bankservice.entity.enums.BankTransactionStatus;
import com.example.bankservice.repository.BankTransactionRepository;
import com.example.bankservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BankTransactionService {

    @Autowired
    BankTransactionRepository bankTransactionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;


    @Autowired
    CentralBankService centralBankService;

    @Value("${bank.name}")
    private String bankName;


    public BankTransaction create(BankTransactionDTO dto){
        BankTransaction bt = new BankTransaction();
        bt.setBankTransactionStatus(BankTransactionStatus.PROCESSING);
        bt.setAmount(dto.getAmount());
        bt.setTransactTo(dto.getToBank() + "@" + dto.getToUser());

        Optional<User> userOpt = userRepository.findUserByUsername(dto.getFromUser());

        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found!");
        }
        bt.setUser(userOpt.get());

        dto.setFromBank(bankName);
        bankTransactionRepository.save(bt);
        boolean status = centralBankService.sendTransaction(dto); // send a req to central bank

        if(status){
            bt.setBankTransactionStatus(BankTransactionStatus.APPROVED);
            userService.updateAmount(userOpt.get().getUsername(), -1 * dto.getAmount());
        }else{
            bt.setBankTransactionStatus(BankTransactionStatus.DECLINED);
        }

        return bt;
    }


    public BankTransaction getById(Long id) {
        Optional<BankTransaction> btOpt = bankTransactionRepository.findById(id);

        if (btOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction is not found!");
        }

        //checks here

        return btOpt.get();
    }

    @Transactional
    public List<BankTransaction> getAllByUserName(String name) {

        Optional<User> userOpt = userRepository.findUserByUsername(name);

        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found");
        }

        return bankTransactionRepository.findAllByUser(userOpt.get());

    }

    @Transactional
    public void updateTransactionStatus(Long transactionId, BankTransactionStatus status) {
        Optional<BankTransaction> transactionOpt = bankTransactionRepository.findById(transactionId);

        if (transactionOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction is not found");
        }

        BankTransaction transaction = transactionOpt.get();
        transaction.setBankTransactionStatus(status);

        bankTransactionRepository.save(transaction);
    }


}
