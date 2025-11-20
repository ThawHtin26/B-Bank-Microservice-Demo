package com.bbank.accounts.service.impl;

import com.bbank.accounts.constants.AccountConstants;
import com.bbank.accounts.dto.CustomerDto;
import com.bbank.accounts.entity.Account;
import com.bbank.accounts.entity.Customer;
import com.bbank.accounts.exceptions.CustomerAlreadyExistsException;
import com.bbank.accounts.repository.AccountRepository;
import com.bbank.accounts.repository.CustomerRepository;
import com.bbank.accounts.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException(String.format("Customer already exists : {%s} ", optionalCustomer.get().getMobileNumber()));
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccountFromCustomer(savedCustomer));
    }

    private Account createNewAccountFromCustomer(Customer customer) {
        Account account = new Account();
        account.setCustomerId(customer.getCustomerId());

        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        account.setAccountNumber(randomAccNumber);
        account.setAccountType(AccountConstants.SAVINGS_ACCOUNT);
        account.setBranchAddress(AccountConstants.ADDRESS_ACCOUNT);
        return account;
    }
}
