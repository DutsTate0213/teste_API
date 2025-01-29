package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.service.TurnoService;
import com.datamonki.ApiCadastro.dto.TurnoDto;

@RestController
@RequestMapping("/turno")
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
    
    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
    public ResponseEntity<ApiResponse> updateStatus(
            @PathVariable Integer id, 
            @RequestBody TurnoDto turnoDto) {
        return turnoService.updateStatus(id, turnoDto);
    }
}