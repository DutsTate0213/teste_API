package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datamonki.ApiCadastro.dto.DisciplinaCursoDto;
import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.service.DisciplinaCursoService;

@RestController
@RequestMapping("/api/disciplina-curso")
public class DisciplinaCursoController {
    
    @Autowired
    private DisciplinaCursoService disciplinaCursoService;
    
    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody DisciplinaCursoDto disciplinaCursoDto) {
        return disciplinaCursoService.create(disciplinaCursoDto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Integer id) {
        return disciplinaCursoService.getById(id);
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        return disciplinaCursoService.getAll();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        return disciplinaCursoService.deleteById(id);
    }
    
    @DeleteMapping
    public ResponseEntity<ApiResponse> delete() {
        return disciplinaCursoService.deleteAll();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody DisciplinaCursoDto disciplinaCursoDto) {
        return disciplinaCursoService.update(id, disciplinaCursoDto);
    }
}