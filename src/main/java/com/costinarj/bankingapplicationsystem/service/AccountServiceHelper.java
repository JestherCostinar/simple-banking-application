package com.costinarj.bankingapplicationsystem.service;

import com.costinarj.bankingapplicationsystem.entity.Account;
import com.costinarj.bankingapplicationsystem.service.data.AccountDto;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceHelper {

    /**
     * Convert AccountDto to Account
     *
     * @param accountDto
     * @return Account
     */
    public Account toAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setAccountHolderName(accountDto.getAccountHolderName());
        account.setBalance(accountDto.getBalance());

        return account;
    }

    /**
     * Convert Account to AccountDto
     *
     * @param account
     * @return accountDto
     */
    public AccountDto toAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setAccountHolderName(account.getAccountHolderName());
        accountDto.setBalance(account.getBalance());

        return accountDto;
    }
}
