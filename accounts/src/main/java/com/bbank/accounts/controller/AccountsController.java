package com.bbank.accounts.controller;


import com.bbank.accounts.constants.AccountConstants;
import com.bbank.accounts.dto.CustomerDto;
import com.bbank.accounts.dto.ResponseDto;
import com.bbank.accounts.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountsController {

    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto<Void>> createAccount(@RequestBody CustomerDto customerDto) {

        accountService.createAccount(customerDto);

        ResponseDto<Void> responseBody = ResponseDto.<Void>builder()
                    .status(AccountConstants.STATUS_201)
                    .message(AccountConstants.MESSAGE_201)
                    .build();

        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(responseBody);
    }

    @GetMapping("/fetch")
    public ResponseEntity<ResponseDto<CustomerDto>> getAccountDetail(@RequestParam String mobileNumber) {
         CustomerDto customerDto = accountService.getCustomerByMobileNumber(mobileNumber);

        ResponseDto<CustomerDto> responseBody = ResponseDto.<CustomerDto>builder()
                .status(AccountConstants.STATUS_200)
                .data(customerDto)
                .message(AccountConstants.MESSAGE_200)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

}
