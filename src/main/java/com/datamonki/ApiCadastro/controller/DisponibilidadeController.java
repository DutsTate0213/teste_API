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

import com.datamonki.ApiCadastro.dto.DisponibilidadeDto;
import com.datamonki.ApiCadastro.exceptions.IdNotFoundException;
import com.datamonki.ApiCadastro.exceptions.ValidationException;
import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.service.DisponibilidadeService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/disponibilidade")
public class DisponibilidadeController {
	
	@Autowired
	private DisponibilidadeService disponibilidadeService;
	
	//Faz a requisicao para criar uma disponibilidade
	@PostMapping
	public ResponseEntity<ApiResponse> save(@RequestBody DisponibilidadeDto disponibilidadeDto) {
			try {
				return disponibilidadeService.save(disponibilidadeDto);
			} catch (ValidationException e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body(new ApiResponse("Não foi possivel criar Disponibilidade, tente novamente", null));
			}
		}
	//Faz a requisicao para criar uma disponibilidade
	@PostMapping("/lista")
	public ResponseEntity<ApiResponse> saveAll(@RequestBody List<DisponibilidadeDto>  disponibilidadesDto) {
			try {
				return disponibilidadeService.saveAll(disponibilidadesDto);
			} catch (ValidationException e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body(new ApiResponse("Não foi possivel criar Disponibilidade, tente novamente", null));
			}
		}
		 
	//Get de todas as disponbilidades cadastradas 	
	@GetMapping 
	public ResponseEntity<ApiResponse> getAll(){
			try {
				return disponibilidadeService.getAll();
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Disponibilidades, tente novamente", null));
			}
		}
	
	@GetMapping("/professor/{idProfessor}")
	public ResponseEntity<ApiResponse> getByidProfessor(@PathVariable Integer idProfessor){
		try {
			return disponibilidadeService.getByIdProfessor(idProfessor);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Disponibilidades pelo id do professor, tente novamente", null));
		}
	}
	
	
	@DeleteMapping("/professor/{idProfessor}")	
	public ResponseEntity<ApiResponse> deleteByIdProfessor(@PathVariable Integer idProfessor){
		try {
			return disponibilidadeService.deleteByIdProfessor(idProfessor);
		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (ValidationException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Não foi possivel deletar Disponibilidade, tente novamente", null));
		}
	}
	
	@DeleteMapping("/professor")	
	public ResponseEntity<ApiResponse> deleteByIdProfessorAnoSemestre(@RequestParam("idProfessor") Integer idProfessor,
			@RequestParam("ano") Integer ano, @RequestParam("semestre") Integer semestre){
		try {
			return disponibilidadeService.deleteByIdProfessorAnoSemestre(idProfessor, semestre, ano);
		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (ValidationException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse("Não foi possivel deletar Disponibilidade, tente novamente", null));
		}
	}
	
}