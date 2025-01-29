package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.service.DiaSemanaService;
import com.datamonki.ApiCadastro.dto.DiaSemanaDto;

@RestController
@RequestMapping("/dia-semana")
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
    
    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
    public ResponseEntity<ApiResponse> updateStatus(
            @PathVariable Integer id, 
            @RequestBody DiaSemanaDto diaSemanaDto) {
        return diaSemanaService.updateStatus(id, diaSemanaDto);
    }
}