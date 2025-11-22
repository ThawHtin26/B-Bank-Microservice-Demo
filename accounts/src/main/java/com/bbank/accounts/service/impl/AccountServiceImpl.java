package com.bbank.accounts.service.impl;

import com.bbank.accounts.constants.AccountConstants;
import com.bbank.accounts.dto.AccountDto;
import com.bbank.accounts.dto.CustomerDto;
import com.bbank.accounts.entity.Account;
import com.bbank.accounts.entity.Customer;
import com.bbank.accounts.exceptions.CustomerAlreadyExistsException;
import com.bbank.accounts.exceptions.ResourceNotFoundException;
import com.bbank.accounts.repository.AccountRepository;
import com.bbank.accounts.repository.CustomerRepository;
import com.bbank.accounts.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()){
            log.error("Customer already exists with phone number : {}", customerDto.getMobileNumber());
            throw new CustomerAlreadyExistsException(String.format("Customer already exists with phone number : %s", optionalCustomer.get().getMobileNumber()));
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccountFromCustomer(savedCustomer));
    }

    @Override
    public CustomerDto getCustomerByMobileNumber(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> {
                    log.error("Customer not found with phone number: {}", mobileNumber);
                    return new ResourceNotFoundException("Customer not found with phone number: " + mobileNumber);
                });

        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> {
                    log.error("Account not found with customer Id: {}", customer.getCustomerId());
                    return new ResourceNotFoundException("Account not found with customer Id: " + customer.getCustomerId());
                });

        // Map Account to AccountDto
        AccountDto accountDto = new AccountDto();
        BeanUtils.copyProperties(account, accountDto);

        // Map Customer to CustomerDto
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);

        // Attach
        customerDto.setAccount(accountDto);

        return customerDto;
    }

    @Override
    public boolean updatedAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountDto = customerDto.getAccount();

        if(accountDto != null) {

            Account account = accountRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account not found with account number: " + accountDto.getAccountNumber()));

            BeanUtils.copyProperties(accountDto, account);
            account = accountRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    ()-> new ResourceNotFoundException("Customer not found with customer Id: " + customerId)
            );

            BeanUtils.copyProperties(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
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
