package com.example.bankservice.service;

import com.example.bankservice.dto.UserDTO;
import com.example.bankservice.entity.User;
import com.example.bankservice.entity.UserRole;
import com.example.bankservice.entity.enums.UserStatus;
import com.example.bankservice.repository.UserRepository;
import com.example.bankservice.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    @Transactional
    public User create(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());

        //checks here

        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getEmail());

        UserRole userRole = userRoleRepository.findByName("USER");
        user.setUserRole(userRole);
        user.setUserStatus(UserStatus.ACTIVE);

        userRepository.save(user);

        return user;
    }

    public User getById(Long id) {
        User user = userRepository.getById(id);

        return user;

    }

    public User getByName(String name) {
        User user = userRepository.findUserByUsername(name);

        return user;
    }

    @Transactional
    public void update(UserDTO dto) throws Exception {
        Optional<User> opt = userRepository.findById(dto.getId());
        if (opt.isEmpty()) throw new Exception("User not found");

        User user = opt.get();

        user.setUsername(dto.getUsername() != null ? dto.getUsername() : user.getUsername());
        user.setPassword(dto.getPassword() != null ? dto.getPassword() : user.getPassword());
        user.setEmail(dto.getEmail() != null ? dto.getEmail() : user.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber() != null ? dto.getPhoneNumber() : user.getPhoneNumber());

        userRepository.save(user);

    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }


}
