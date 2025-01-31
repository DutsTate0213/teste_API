package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.datamonki.ApiCadastro.dto.DisciplinaCursoDto;
import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.service.DisciplinaCursoService;

@RestController
@RequestMapping("/disciplina-curso")
public class DisciplinaCursoController {
    
    @Autowired
    private DisciplinaCursoService disciplinaCursoService;
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN')")
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
    
    @GetMapping("/disciplina/{id}")
    public ResponseEntity<ApiResponse> findByIdDisciplina(@PathVariable Integer id) {
        return disciplinaCursoService.findByIdDisciplina(id);
    }
    
    @GetMapping("/curso/{id}")
    public ResponseEntity<ApiResponse> findByIdCurso(@PathVariable Integer id) {
        return disciplinaCursoService.findByIdCurso(id);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        return disciplinaCursoService.deleteById(id);
    }
    
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
    public ResponseEntity<ApiResponse> delete() {
        return disciplinaCursoService.deleteAll();
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN', 'ACESSO_COORDENADOR')")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody DisciplinaCursoDto disciplinaCursoDto) {
        return disciplinaCursoService.update(id, disciplinaCursoDto);
    }
    
    @DeleteMapping("/curso/{id}")
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
    public ResponseEntity<ApiResponse> deleteByCursoId(@PathVariable Integer id) {
        return disciplinaCursoService.deleteByCursoId(id);
    }
    
    @DeleteMapping("/disciplina/{id}")
    @PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
    public ResponseEntity<ApiResponse> deleteByDisciplinaId(@PathVariable Integer id) {
        return disciplinaCursoService.deleteByDisciplinaId(id);
    }
}