package com.bbank.accounts.service;

import com.bbank.accounts.dto.CustomerDto;
import com.bbank.accounts.entity.Customer;

import java.util.Optional;

public interface AccountService {

    /**
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber - mobileNumber
     * @return Account Detail based on given phone number
     */
    CustomerDto getCustomerByMobileNumber (String mobileNumber);

}
