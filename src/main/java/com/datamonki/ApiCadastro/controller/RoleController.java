package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.datamonki.ApiCadastro.dto.RoleDto;
import com.datamonki.ApiCadastro.dto.UsuarioDto;
import com.datamonki.ApiCadastro.exceptions.ValidationException;
import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.service.RoleService;

@Controller
@RequestMapping("/public/acessos")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	
	
	@PostMapping
	public ResponseEntity<ApiResponse> save(@RequestBody RoleDto roleDto) {
			try {
				return roleService.save(roleDto);
			} catch (ValidationException e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body(new ApiResponse("Não foi possivel cadastrar o usuário, tente novamente, tente novamente", null));
			}
		}
	
	//Get de todas os acesssos cadastrados 	
	@GetMapping 
	public ResponseEntity<ApiResponse> getAll(){
			try {
				return roleService.getAll();
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.ok(new ApiResponse("Não foi possivel localizar Disponibilidades, tente novamente", null));
			}
		}
}
