package com.banking.ledger.domain.service.impl;

import com.banking.ledger.api.dto.AccountResponse;
import com.banking.ledger.api.dto.CreateAccountRequest;
import com.banking.ledger.domain.Account;
import com.banking.ledger.domain.service.AccountService;
import com.banking.ledger.domain.util.AccountNumberGenerator;
import com.banking.ledger.data.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public AccountResponse createAccount(CreateAccountRequest request) {
        if (request.getUserId() == null || request.getUserId().trim().isEmpty()) {
            throw new IllegalArgumentException("Service: User ID is required to create an account");
        }
        if (request.getAccountType() == null) {
            throw new IllegalArgumentException("Service: Account Type is required");
        }

        String newAccountNumber;
        do {
            newAccountNumber = AccountNumberGenerator.generate();
        } while (accountRepository.existsByAccountNumber(newAccountNumber));

        Account newAccount = new Account(
                request.getUserId(),
                newAccountNumber,
                request.getAccountType()
        );

        Account savedAccount = accountRepository.save(newAccount);

        return new AccountResponse(savedAccount);
    }

    @Override
    public AccountResponse getAccountById(UUID id) {
        return null;
    }

    @Override
    public List<AccountResponse> getAllAccounts() {
        return null;
    }
}