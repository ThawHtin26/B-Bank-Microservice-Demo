package com.bbank.accounts.repository;

import com.bbank.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CustomerRepository class
 * @Author Thaw Htin Aung
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
