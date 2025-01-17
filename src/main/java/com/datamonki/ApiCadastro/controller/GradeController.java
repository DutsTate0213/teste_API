package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datamonki.ApiCadastro.service.GradeService;
import com.datamonki.ApiCadastro.dto.GradeDto;
import com.datamonki.ApiCadastro.response.ApiResponse;

@RestController
@RequestMapping("/api/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;
    
    @PostMapping
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
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        return gradeService.deleteById(id);
    }
    
    @DeleteMapping
    public ResponseEntity<ApiResponse> delete() {
        return gradeService.deleteAll();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody GradeDto gradeDto) {
        return gradeService.update(id, gradeDto);
    }
}