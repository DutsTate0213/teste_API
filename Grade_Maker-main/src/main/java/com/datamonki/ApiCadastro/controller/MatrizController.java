package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import com.datamonki.ApiCadastro.dto.MatrizDto;
import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.service.MatrizService;

@RestController
@RequestMapping("/matriz")
public class MatrizController {
    
    @Autowired
    private MatrizService matrizService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN')")
    public ResponseEntity<ApiResponse> create(@RequestBody MatrizDto matrizDto) {
        return matrizService.create(matrizDto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Integer id) {
        return matrizService.getById(id);
    }

    @GetMapping("/turma/{id}")
    public ResponseEntity<ApiResponse> getByIdTurma(@PathVariable Integer id) {
        return matrizService.getByIdTurma(id);
    }

    @GetMapping("/disciplina/{id}")
    public ResponseEntity<ApiResponse> getByIdDisciplina(@PathVariable Integer id) {
        return matrizService.getByIdDisciplina(id);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        return matrizService.getAll();
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        return matrizService.deleteById(id);
    }
    
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
    public ResponseEntity<ApiResponse> delete() {
        return matrizService.deleteAll();
    }

    @DeleteMapping("/turma/{id}")
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
    public ResponseEntity<ApiResponse> deleteByIdTurma(@PathVariable Integer id) {
        return matrizService.deleteByIdTurma(id);
    }

    @DeleteMapping("/disciplina/{id}")
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
    public ResponseEntity<ApiResponse> deleteByIdDisciplina(@PathVariable Integer id) {
        return matrizService.deleteByIdDisciplina(id);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN', 'ACESSO_COORDENADOR')")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody MatrizDto matrizDto) {
        return matrizService.update(id, matrizDto);
    }
}