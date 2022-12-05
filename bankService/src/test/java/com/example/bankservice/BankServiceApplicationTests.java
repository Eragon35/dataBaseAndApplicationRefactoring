package com.example.bankservice;

import com.example.bankservice.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BankServiceApplicationTests {

    @Test
    void contextLoads() {
        User user = new User();

        user.setUsername("test1");
        user.setPassword("testPWD");
        user.setEmail("emeal@g.com");
//        user.set
    }

}
