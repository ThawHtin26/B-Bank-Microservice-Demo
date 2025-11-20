package com.bbank.accounts.repository;

import com.bbank.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * AccountRepository class
 * @Author Thaw Htin Aung
 */
@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByCustomerId(Long customerId);
}
