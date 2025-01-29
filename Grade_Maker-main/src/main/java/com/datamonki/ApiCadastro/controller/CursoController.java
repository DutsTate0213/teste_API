package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datamonki.ApiCadastro.dto.CursoDto;
import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.service.CursoService;

//Classe que representa o controller, responsavel pelas requisicoes de curso para a api
@RestController
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('ACESSO_ADMIN')")
	public ResponseEntity<ApiResponse> create(@RequestBody CursoDto cursoDto){

		return cursoService.create(cursoDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getById(@PathVariable Integer id){

		return cursoService.getById(id);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse> getAll(){ 

		return cursoService.getAll();
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<ApiResponse> getByNome(@PathVariable String nome) {

		return cursoService.getByNome(nome);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
	public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){

		return cursoService.deleteById(id);
	}
	
	@DeleteMapping
	@PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
	public ResponseEntity<ApiResponse> delete(){

		return cursoService.deleteAll();
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ACESSO_ADMIN', 'ACESSO_COORDENADOR')")
	public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody CursoDto cursoDto){

		return cursoService.update(id, cursoDto);
	}
}