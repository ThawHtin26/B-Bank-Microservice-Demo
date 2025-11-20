package com.bbank.accounts.service;

import com.bbank.accounts.dto.CustomerDto;
import com.bbank.accounts.entity.Customer;

public interface AccountService {

    /**
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

}
