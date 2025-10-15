package com.costinarj.bankingapplicationsystem.controller;

import com.costinarj.bankingapplicationsystem.service.AccountService;
import com.costinarj.bankingapplicationsystem.service.data.AccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> saveAccount(@RequestBody AccountDto accountDto) {
        log.info("Saving account information..");
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        log.info("Getting account information..");
        AccountDto accountDto = accountService.getAccountById(id);

        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @PutMapping("{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
                                              @RequestBody Map<String, Double> request) {
        log.info("Deposting amount..");
        AccountDto account = accountService.depositAmount(id, request.get("amount"));

        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping("{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,
                                               @RequestBody Map<String, Double> request) {
        log.info("Withdrawing amount..");
        AccountDto account = accountService.withdrawAmount(id, request.get("amount"));
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        log.info("Getting all account information..");

        List<AccountDto> accountDtoList = accountService.getAllAccounts();
        return new ResponseEntity<>(accountDtoList, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        log.info("Deleting account information..");
        accountService.deleteAccount(id);

        return new ResponseEntity<>("Account has been deleted successfully", HttpStatus.OK);
    }
}
