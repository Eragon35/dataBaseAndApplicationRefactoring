package com.example.bankservice.security.jwt;


import com.example.bankservice.entity.User;
import com.example.bankservice.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                convertRoles(user.getUserRole())
        );
    }

    private static List<GrantedAuthority> convertRoles(UserRole role) {

        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(role.getName()));

        return list;
    }
}
