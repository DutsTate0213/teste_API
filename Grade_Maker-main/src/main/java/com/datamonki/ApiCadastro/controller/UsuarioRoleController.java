package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.datamonki.ApiCadastro.dto.UsuarioRoleDto;
import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.service.UsuarioRoleService;

@Controller
@RequestMapping("/public/dar-acesso")
public class UsuarioRoleController {

	@Autowired
	private UsuarioRoleService usuarioRoleService;

	@PostMapping
	public ResponseEntity<ApiResponse> save(@RequestBody UsuarioRoleDto dto) {
		return usuarioRoleService.adicionarRoleAoUsuario(dto);
	}

	@DeleteMapping
	public ResponseEntity<ApiResponse> delete(
			@RequestParam("idUsuario") Integer idUsuario,
			@RequestParam("idRole") Integer idRole) {
		UsuarioRoleDto dto = new UsuarioRoleDto(idUsuario, idRole);
		return usuarioRoleService.removerRoleDoUsuario(dto);
	}
}