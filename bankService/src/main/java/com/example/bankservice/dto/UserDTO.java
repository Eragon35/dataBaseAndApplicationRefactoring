package com.example.bankservice.dto;

import com.example.bankservice.dto.interfaces.CreateUserForm;
import com.example.bankservice.dto.interfaces.UpdateUserForm;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
public class UserDTO {

    @NotNull(groups = {UpdateUserForm.class}, message = "id is required")
    private Long id;
    @NotBlank(groups = {CreateUserForm.class}, message = "Username is required")
    private String username;
    @NotBlank(groups = {CreateUserForm.class}, message = "Password is required")
    private String password;

    @Email
    private String email;

    @Pattern(regexp = "(\\+7|8)[0-9]{9}")
    private String phoneNumber;

}
