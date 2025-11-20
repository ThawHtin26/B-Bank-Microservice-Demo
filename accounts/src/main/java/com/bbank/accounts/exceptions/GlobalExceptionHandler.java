package com.bbank.accounts.exceptions;

import com.bbank.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex,
                                                                              WebRequest webRequest){
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .apiPath(webRequest.getDescription(false))
                .errorCode(HttpStatus.BAD_REQUEST)
                .errorMessage(ex.getMessage())
                .errorTime(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                                 WebRequest webRequest){
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .apiPath(webRequest.getDescription(false))
                .errorCode(HttpStatus.NOT_FOUND)
                .errorMessage(ex.getMessage())
                .errorTime(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception ex,
                                                                            WebRequest webRequest){
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .apiPath(webRequest.getDescription(false))
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorMessage(ex.getMessage())
                .errorTime(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
