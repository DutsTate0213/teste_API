package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.service.TurnoService;

@RestController
@RequestMapping("/api/turno")
public class TurnoController {
    
    @Autowired
    private TurnoService turnoService;
    
    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        return turnoService.getAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Integer id) {
        return turnoService.getById(id);
    }
}