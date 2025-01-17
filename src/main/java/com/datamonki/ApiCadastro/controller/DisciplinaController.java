package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datamonki.ApiCadastro.dto.DisciplinaDto;
import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.service.DisciplinaService;

@RestController
@RequestMapping("/api/disciplina")
public class DisciplinaController {
    
    @Autowired
    private DisciplinaService disciplinaService;
    
    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody DisciplinaDto disciplinaDto){
        return disciplinaService.create(disciplinaDto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Integer id){
        return disciplinaService.getById(id);
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse> getAll(){ 
        return disciplinaService.getAll();
    }
    
    @GetMapping("/nome/{nome}")
    public ResponseEntity<ApiResponse> getByNome(@PathVariable String nome) {
        return disciplinaService.getByNome(nome);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        return disciplinaService.deleteById(id);
    }
    
    @DeleteMapping
    public ResponseEntity<ApiResponse> delete(){
        return disciplinaService.deleteAll();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody DisciplinaDto disciplinaDto){
        return disciplinaService.update(id, disciplinaDto);
    }
}