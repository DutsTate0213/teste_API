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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.access.AccessDeniedException;

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

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse> UsernameNotFoundException(UsernameNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiResponse(e.getMessage(), null));
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiResponse> JwtException(JwtException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(new ApiResponse("Token inválido ou expirado", null));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse> AuthenticationException(AuthenticationException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(new ApiResponse("Falha na autenticação: " + e.getMessage(), null));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> AccessDeniedException(AccessDeniedException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(new ApiResponse("Acesso negado: " + e.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> Exception(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponse("Erro interno do servidor: " + e.getMessage(), null));
    }
}