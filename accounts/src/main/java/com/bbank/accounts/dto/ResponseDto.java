package com.bbank.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Generic ResponseDto class
 * @Author Thaw Htin Aung
 */

@Data
@AllArgsConstructor
@Builder
public class ResponseDto <T>{
    private String status;
    private T data;
    private String message;

}
