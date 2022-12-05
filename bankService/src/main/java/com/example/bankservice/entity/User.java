package com.example.bankservice.entity;

import com.example.bankservice.entity.enums.UserStatus;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String email;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private UserStatus userStatus;

}
