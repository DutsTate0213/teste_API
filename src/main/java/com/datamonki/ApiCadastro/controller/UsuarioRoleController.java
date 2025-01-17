package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.datamonki.ApiCadastro.dto.UsuarioDto;
import com.datamonki.ApiCadastro.dto.UsuarioRoleDto;
import com.datamonki.ApiCadastro.exceptions.ValidationException;
import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.service.UsuarioRoleService;
import com.datamonki.ApiCadastro.service.UsuarioService;

@Controller
@RequestMapping("/public/dar-acesso")
public class UsuarioRoleController {
	@Autowired
	private UsuarioRoleService usuarioRoleService;
	
	
	@PostMapping
	public ResponseEntity<ApiResponse> save(@RequestBody UsuarioRoleDto dto) {
			try {
				return usuarioRoleService.adicionarRoleAoUsuario(dto);
			} catch (ValidationException e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body(new ApiResponse("Não foi possivel cadastrar o usuário, tente novamente, tente novamente", null));
			}
		}
	
	@DeleteMapping
	public ResponseEntity<ApiResponse> delete(@RequestParam("idUsuario") Integer idUsuario, @RequestParam("idRole") Integer idRole) {
			try {
				UsuarioRoleDto dto = new UsuarioRoleDto(idUsuario, idRole);
				return usuarioRoleService.adicionarRoleAoUsuario(dto);
			} catch (ValidationException e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body(new ApiResponse("Não foi possivel cadastrar o usuário, tente novamente, tente novamente", null));
			}
		}
}
