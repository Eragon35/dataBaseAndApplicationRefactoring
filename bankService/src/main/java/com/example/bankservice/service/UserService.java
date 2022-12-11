package com.example.bankservice.service;

import com.example.bankservice.dto.UserDTO;
import com.example.bankservice.entity.User;
import com.example.bankservice.entity.UserRole;
import com.example.bankservice.entity.enums.UserStatus;
import com.example.bankservice.repository.UserRepository;
import com.example.bankservice.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        Optional<User> userOpt = userRepository.findUserByUsername(dto.getUsername());

        if (userOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this username is already exist");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
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
        Optional<User> userOpt = userRepository.findById(id);

        if(userOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found");
        }

        return userOpt.get();
    }

    public User getByName(String name) {
        Optional<User> userOpt = userRepository.findUserByUsername(name);

        if(userOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found");
        }

        return userOpt.get();
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

        Optional<User> userOpt = userRepository.findById(id);

        if(userOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found");
        }

        userRepository.deleteById(id);
    }


}
