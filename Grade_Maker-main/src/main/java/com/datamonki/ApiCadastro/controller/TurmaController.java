package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datamonki.ApiCadastro.service.TurmaService;
import com.datamonki.ApiCadastro.dto.TurmaDto;
import com.datamonki.ApiCadastro.response.ApiResponse;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;
    
    @PostMapping
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
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        return turmaService.deleteById(id);
    }
    
    @DeleteMapping
    public ResponseEntity<ApiResponse> delete() {
        return turmaService.deleteAll();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody TurmaDto turmaDto) {
        return turmaService.update(id, turmaDto);
    }
}