package com.example.bankservice.entity;

import com.example.bankservice.entity.enums.BankTransactionStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "bank_transaction")
public class BankTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String transactTo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"password", "email", "phoneNumber", "userRole", "userStatus"})
    private User user;

    @Setter(AccessLevel.NONE)
    private Date created;

    @Enumerated(EnumType.STRING)
    @Column(name = "bank_transaction_status")
    private BankTransactionStatus bankTransactionStatus;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

}
