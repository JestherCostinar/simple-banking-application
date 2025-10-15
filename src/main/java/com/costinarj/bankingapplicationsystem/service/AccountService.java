package com.costinarj.bankingapplicationsystem.service;

import com.costinarj.bankingapplicationsystem.service.data.AccountDto;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto account);

    AccountDto getAccountById(Long id) throws AccountNotFoundException;

    AccountDto depositAmount(Long id, double amount);

    AccountDto withdrawAmount(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id) throws AccountNotFoundException;
}
