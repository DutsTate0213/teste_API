package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import com.datamonki.ApiCadastro.service.TurmaService;
import com.datamonki.ApiCadastro.dto.TurmaDto;
import com.datamonki.ApiCadastro.response.ApiResponse;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN')")
    public ResponseEntity<ApiResponse> create(@RequestBody TurmaDto turmaDto) {
        return turmaService.create(turmaDto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Integer id) {
        return turmaService.getById(id);
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        return turmaService.getAll();
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        return turmaService.deleteById(id);
    }
    
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
    public ResponseEntity<ApiResponse> delete() {
        return turmaService.deleteAll();
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN', 'ACESSO_COORDENADOR')")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody TurmaDto turmaDto) {
        return turmaService.update(id, turmaDto);
    }
    
    @GetMapping("/curso/{id}")
    public ResponseEntity<ApiResponse> findByIdCurso(@PathVariable Integer id) {
        return turmaService.findByIdCurso(id);
    }
    
    @DeleteMapping("/curso/{id}")
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
    public ResponseEntity<ApiResponse> deleteByIdCurso(@PathVariable Integer id) {
        return turmaService.deleteByIdCurso(id);
    }
}