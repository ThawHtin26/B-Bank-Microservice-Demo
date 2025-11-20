package com.bbank.accounts.dto;

import lombok.Data;

/**
 * AccountDto class
 * @Author Thaw Htin Aung
 */

@Data
public class AccountDto {
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}
