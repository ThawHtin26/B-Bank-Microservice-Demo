package com.bbank.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Generic ResponseDto class
 * @Author Thaw Htin Aung
 */

@Data
@AllArgsConstructor
public class ResponseDto <T>{
    private String status;
    private T data;
    private String message;

}
