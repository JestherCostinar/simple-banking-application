package com.costinarj.bankingapplicationsystem.service;

import com.costinarj.bankingapplicationsystem.service.data.AccountDto;

public interface AccountService {

    AccountDto createAccount(AccountDto account);

    AccountDto getAccountById(Long id);

    AccountDto depositAmount(Long id, double amount);

    AccountDto withdrawAmount(Long id, double amount);
}
