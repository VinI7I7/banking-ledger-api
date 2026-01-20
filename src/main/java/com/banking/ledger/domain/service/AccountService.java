package com.banking.ledger.domain.service;

import com.banking.ledger.api.dto.AccountResponse;
import com.banking.ledger.api.dto.CreateAccountRequest;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountResponse createAccount(CreateAccountRequest request);
    AccountResponse getAccountById(UUID id);
    List<AccountResponse> getAllAccounts();
}