package com.example.bankservice.service;

import com.example.bankservice.dto.BankTransactionDTO;
import com.example.bankservice.entity.BankTransaction;
import com.example.bankservice.entity.User;
import com.example.bankservice.entity.enums.BankTransactionStatus;
import com.example.bankservice.repository.BankTransactionRepository;
import com.example.bankservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    RequestService requestService;

    @Value("${bank.name}")
    private String bankName;


    public BankTransaction create(BankTransactionDTO dto) throws Exception {
        BankTransaction bt = new BankTransaction();
        bt.setBankTransactionStatus(BankTransactionStatus.PROCESSING);
        bt.setAmount(dto.getAmount());
        bt.setTransactTo(dto.getTransactTo());

        Optional<User> userOpt = userRepository.findById(dto.getUserId());

        if(userOpt.isEmpty()) throw new Exception("User is not found!");
        bt.setUser(userOpt.get());

        bankTransactionRepository.save(bt);
        requestService.sendRequest(bt); // send a req to central bank

        return bt;

    }

    public BankTransaction getById(Long id) throws Exception {
        Optional<BankTransaction> btOpt = bankTransactionRepository.findById(id);

        if(btOpt.isEmpty()) throw new Exception("Transaction is not found!");

        //checks here

        return btOpt.get();
    }

    @Transactional
    public List<BankTransaction> getAllByUserName(String name) {

        User user = userRepository.findUserByUsername(name);

        //user check here

        List<BankTransaction> list = bankTransactionRepository.findAllByUser(user);

        return list;
    }

    @Transactional
    public void updateTransactionStatus(Long transactionId, BankTransactionStatus status) {
        BankTransaction transaction = bankTransactionRepository.getById(transactionId);
        transaction.setBankTransactionStatus(status);

        bankTransactionRepository.save(transaction);
    }


}
