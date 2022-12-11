package com.example.bankservice.security;


import com.example.bankservice.entity.User;
import com.example.bankservice.security.jwt.JwtUser;
import com.example.bankservice.security.jwt.JwtUserFactory;
import com.example.bankservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByName(username);

        if (user == null) throw new UsernameNotFoundException("User " + username + " was not found");

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("In loadUserByUsername - user username: {} has been created", username);

        return jwtUser;
    }


}
