package com.datamonki.ApiCadastro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

import com.datamonki.ApiCadastro.dto.DisponibilidadeDto;
import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.service.DisponibilidadeService;

@RestController
@RequestMapping("/disponibilidade")
public class DisponibilidadeController {
	
	@Autowired
	private DisponibilidadeService disponibilidadeService;
	
	//Faz a requisicao para criar uma disponibilidade
	@PostMapping
	@PreAuthorize("hasAnyAuthority('ACESSO_ADMIN')")
	public ResponseEntity<ApiResponse> save(@RequestBody DisponibilidadeDto disponibilidadeDto) {
		return disponibilidadeService.save(disponibilidadeDto);
	}
	
	//Faz a requisicao para criar uma disponibilidade
	@PostMapping("/lista")
	@PreAuthorize("hasAnyAuthority('ACESSO_ADMIN')")
	public ResponseEntity<ApiResponse> saveAll(@RequestBody List<DisponibilidadeDto> disponibilidadesDto) {
		return disponibilidadeService.saveAll(disponibilidadesDto);
	}
	
	//Get de todas as disponbilidades cadastradas 	
	@GetMapping 
	public ResponseEntity<ApiResponse> getAll() {
		return disponibilidadeService.getAll();
	}
	
	@GetMapping("/professor/{idProfessor}")
	public ResponseEntity<ApiResponse> getByidProfessor(@PathVariable Integer idProfessor) {
		return disponibilidadeService.getByIdProfessor(idProfessor);
	}
	
	@DeleteMapping("/professor/{idProfessor}")	
	@PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
	public ResponseEntity<ApiResponse> deleteByIdProfessor(@PathVariable Integer idProfessor) {
		return disponibilidadeService.deleteByIdProfessor(idProfessor);
	}
	
	@DeleteMapping("/professor")	
	@PreAuthorize("hasAnyAuthority('ACESSO_ADMIN','ACESSO_COORDENADOR')")
	public ResponseEntity<ApiResponse> deleteByIdProfessorAnoSemestre(
			@RequestParam("idProfessor") Integer idProfessor,
			@RequestParam("ano") Integer ano, 
			@RequestParam("semestre") Integer semestre) {
		return disponibilidadeService.deleteByIdProfessorAnoSemestre(idProfessor, semestre, ano);
	}
}