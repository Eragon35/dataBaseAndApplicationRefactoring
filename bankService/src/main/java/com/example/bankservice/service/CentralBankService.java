package com.example.bankservice.service;

import com.example.bankservice.dto.BankTransactionDTO;
import com.example.bankservice.dto.CentralBankDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class CentralBankService {

    @Value("${central_bank.url}")
    String url; // URL центр-банка


    @Value("${bank.name}")
    String bankName;
    @Value("${bank.key_token}")
    String token; // URL центр-банка

    @Autowired
    UserService userService;


    public boolean sendTransaction(BankTransactionDTO dto) {
        try {
            // create headers
            HttpHeaders headers = new HttpHeaders();
            // set `content-type` header
            headers.setContentType(MediaType.APPLICATION_JSON);
            // set `accept` header
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            Map<String, Object> map = new HashMap<>();
            map.put("fromBank", dto.getFromBank());
            map.put("fromUser", dto.getFromUser());
            map.put("amount", dto.getAmount());
            map.put("toBank", dto.getToBank());
            map.put("toUser", dto.getToUser());
            map.put("bank_token", token);

            // build the request
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

            RestTemplate restTemplate = new RestTemplate();


            // send POST request
            ResponseEntity<BankTransactionDTO> response = restTemplate.postForEntity(url, entity, BankTransactionDTO.class);

            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }


    }

    public void updateBankFinances(CentralBankDTO dto) {
        userService.updateAmount(bankName, dto.getAmount());

    }
    public void updateUserFinances(BankTransactionDTO dto) {
        userService.updateAmount(dto.getToUser(), dto.getAmount());

    }
}
