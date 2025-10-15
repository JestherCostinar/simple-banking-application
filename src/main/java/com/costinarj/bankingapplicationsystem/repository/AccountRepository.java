package com.costinarj.bankingapplicationsystem.repository;

import com.costinarj.bankingapplicationsystem.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
