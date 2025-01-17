package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datamonki.ApiCadastro.dto.MatrizDto;
import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.service.MatrizService;

@RestController
@RequestMapping("/api/matriz")
public class MatrizController {
    
    @Autowired
    private MatrizService matrizService;
    
    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody MatrizDto matrizDto) {
        return matrizService.create(matrizDto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Integer id) {
        return matrizService.getById(id);
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        return matrizService.getAll();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        return matrizService.deleteById(id);
    }
    
    @DeleteMapping
    public ResponseEntity<ApiResponse> delete() {
        return matrizService.deleteAll();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody MatrizDto matrizDto) {
        return matrizService.update(id, matrizDto);
    }
}