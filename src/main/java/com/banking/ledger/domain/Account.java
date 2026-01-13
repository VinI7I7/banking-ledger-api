package com.banking.ledger.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;


    // void constructor JPA
    protected Account(){
    }

    public Account(String accountNumber, AccountType accountType){
            this.accountNumber = accountNumber;
            this.accountType = accountType;
            this.balance = BigDecimal.ZERO;
            this.status = AccountStatus.ACTIVE;
            this.createdAt = LocalDateTime.now();
            this.updatedAt = LocalDateTime.now();
    }

    public void deposit(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO)<=0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }
        if (this.status == AccountStatus.CLOSED){
            throw new IllegalArgumentException("Cannot deposit into a closed account");
        }
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (this.status  != AccountStatus.ACTIVE) {
            throw new IllegalArgumentException("Cannot withdraw. This account is not active");
        }
        if (amount.compareTo(BigDecimal.ZERO)<=0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero");
        }
        if (this.balance.compareTo(amount)<0){
            throw new IllegalArgumentException("insufficient funds");
        }
        this.balance = this.balance.subtract(amount);
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }


    public UUID getId(){return id; }
    public String getAccountNumber(){return accountNumber; }
    public BigDecimal getBalance(){return balance; }
    public AccountType getAccountType(){return accountType; }
    public AccountStatus getAccountStatus(){return status; }
    public LocalDateTime getLocalDateTime(){return createdAt; }
}