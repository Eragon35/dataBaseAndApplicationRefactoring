package com.example.bankservice.controller;

import com.example.bankservice.dto.UserDTO;
import com.example.bankservice.dto.interfaces.CreateUserForm;
import com.example.bankservice.dto.interfaces.UpdateUserForm;
import com.example.bankservice.entity.User;
import com.example.bankservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@Validated(CreateUserForm.class) @RequestBody UserDTO dto) {
        log.info("user {} has been created", dto.getUsername());
        return userService.create(dto);
    }

    @GetMapping(path = "/{id}")
    public User getUserByName(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PutMapping()
    public void updateUser(@Validated(UpdateUserForm.class) @RequestBody UserDTO dto) throws Exception {
        userService.update(dto);
        log.info("user {} has been updated", dto.getUsername());
    }

}
