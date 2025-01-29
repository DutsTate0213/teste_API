package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import com.datamonki.ApiCadastro.service.GradeService;
import com.datamonki.ApiCadastro.dto.GradeDto;
import com.datamonki.ApiCadastro.response.ApiResponse;

@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN')")
    public ResponseEntity<ApiResponse> create(@RequestBody GradeDto gradeDto) {
        return gradeService.create(gradeDto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Integer id) {
        return gradeService.getById(id);
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        return gradeService.getAll();
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        return gradeService.deleteById(id);
    }
    
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
    public ResponseEntity<ApiResponse> delete() {
        return gradeService.deleteAll();
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN', 'ACESSO_COORDENADOR')")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody GradeDto gradeDto) {
        return gradeService.update(id, gradeDto);
    }
}