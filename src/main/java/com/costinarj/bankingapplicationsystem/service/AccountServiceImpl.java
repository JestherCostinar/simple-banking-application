package com.costinarj.bankingapplicationsystem.service;

import com.costinarj.bankingapplicationsystem.entity.Account;
import com.costinarj.bankingapplicationsystem.repository.AccountRepository;
import com.costinarj.bankingapplicationsystem.service.data.AccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    private AccountRepository accountRepository;

    private AccountServiceHelper accountServiceHelper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountServiceHelper accountServiceHelper) {
        this.accountRepository = accountRepository;
        this.accountServiceHelper = accountServiceHelper;
    }

    @Override
    public AccountDto createAccount(AccountDto account) {

        Account savedAccount = accountRepository.save(accountServiceHelper.toAccount(account));
        log.info("Account created successfully");

        return accountServiceHelper.toAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);

        if (account.isEmpty()) {
            log.error("Account not found with id={}", id);
            throw new RuntimeException("Account not found");
        }

        return accountServiceHelper.toAccountDto(account.get());
    }

    @Override
    public AccountDto depositAmount(Long id, double amount) {
        if (amount <= 0) {
            log.error("Amount must be greater than zero");
            throw new RuntimeException("Amount must be greater than zero");
        }

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Account not found with id={}", id);
                    return new RuntimeException("Account not found");
                });

        account.setBalance(account.getBalance() + amount);
        Account savedAccount = accountRepository.save(account);

        return accountServiceHelper.toAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdrawAmount(Long id, double amount) {
        if (amount <= 0) {
            log.error("Amount must be greater than zero");
            throw new RuntimeException("Amount must be greater than zero");
        }

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Account not found with id={}", id);
                    return new RuntimeException("Account not found");
                });

        if (account.getBalance() < amount) {
            log.error("Insufficient amount");
            throw new RuntimeException("Insufficient amount");
        }

        account.setBalance(account.getBalance() - amount);
        Account savedAccount = accountRepository.save(account);

        return accountServiceHelper.toAccountDto(savedAccount);
    }

}
