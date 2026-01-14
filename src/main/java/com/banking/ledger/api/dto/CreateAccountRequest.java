package com.banking.ledger.api.dto;

import com.banking.ledger.domain.AccountType;

public class CreateAccountRequest {

    private String userId;
    private AccountType accountType;

    // json
    public CreateAccountRequest() {
    }

    public CreateAccountRequest(String userId, AccountType accountType) {
        this.userId = userId;
        this.accountType = accountType;
    }

    public String getUserId() {return userId; }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public AccountType getAccountType() {return accountType;}
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}