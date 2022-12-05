package com.example.bankservice.dto;

import com.example.bankservice.dto.interfaces.CreateUserForm;
import com.example.bankservice.dto.interfaces.UpdateUserForm;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Data
public class UserDTO {

    @NotNull(groups = {UpdateUserForm.class}, message = "id is required")
    private Long id;
    @NotNull(groups = {CreateUserForm.class}, message = "Username is required")
    private String username;
    @NotNull(groups = {CreateUserForm.class}, message = "Password is required")
    private String password;

    @Email
    private String email;
    private String phoneNumber;

}
