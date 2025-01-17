package com.datamonki.ApiCadastro.infra;


import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.datamonki.ApiCadastro.exceptions.IdNotFoundException;
import com.datamonki.ApiCadastro.exceptions.ValidationException;
import com.datamonki.ApiCadastro.response.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponse> ValidationException(ValidationException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
        body(new ApiResponse(e.getMessage(), null));
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ApiResponse> IdNotFoundException(IdNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
        body(new ApiResponse(e.getMessage(), null));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse> BadRequestException(BadRequestException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
        body(new ApiResponse(e.getMessage(), null));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> IllegalArgumentException(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
        body(new ApiResponse(e.getMessage(), null));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> DataIntegrityViolationException(DataIntegrityViolationException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).
        body(new ApiResponse(e.getMessage(), null));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> RuntimeException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
        body(new ApiResponse(e.getMessage(), null));
    }
}