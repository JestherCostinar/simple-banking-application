package com.costinarj.bankingapplicationsystem.service;

import com.costinarj.bankingapplicationsystem.entity.Account;
import com.costinarj.bankingapplicationsystem.repository.AccountRepository;
import com.costinarj.bankingapplicationsystem.service.data.AccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
}
