package com.bbank.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Generic ErrorResponseDto class
 * @Author Thaw Htin Aung
 */

@Data
@AllArgsConstructor
@Builder
public class ErrorResponseDto{
    private String apiPath;
    private HttpStatus errorCode;
    private String errorMessage;
    private LocalDateTime errorTime;
}
