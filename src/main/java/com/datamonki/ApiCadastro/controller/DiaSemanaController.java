package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.service.DiaSemanaService;

@RestController
@RequestMapping("/api/dia_semana")
public class DiaSemanaController {
    
    @Autowired
    private DiaSemanaService diaSemanaService;
    
    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        return diaSemanaService.getAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Integer id) {
        return diaSemanaService.getById(id);
    }
}