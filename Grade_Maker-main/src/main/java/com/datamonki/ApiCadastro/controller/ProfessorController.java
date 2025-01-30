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
import org.springframework.security.access.prepost.PreAuthorize;

import com.datamonki.ApiCadastro.dto.ProfessorDto;
import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.service.ProfessorService;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('ACESSO_ADMIN')")
	public ResponseEntity<ApiResponse> create(@RequestBody ProfessorDto professorDto){
		return professorService.create(professorDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getById(@PathVariable Integer id){
		return professorService.getById(id);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse> getAll(){ 
		return professorService.getAll();
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<ApiResponse> getByNome(@PathVariable String nome) {
		return professorService.getByNome(nome);
	}

	@GetMapping("/nome/order")
	public ResponseEntity<ApiResponse> getByOrderNome(){
		return professorService.getByOrderNome();
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
	public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
		return professorService.deleteById(id);
	}
	
	@DeleteMapping
	@PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
	public ResponseEntity<ApiResponse> delete(){
		return professorService.deleteAll();
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ACESSO_ADMIN', 'ACESSO_COORDENADOR')")
	public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody ProfessorDto professorDto){
		return professorService.update(id, professorDto);
	}
}