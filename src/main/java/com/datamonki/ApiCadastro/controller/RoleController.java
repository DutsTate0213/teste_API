package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.datamonki.ApiCadastro.dto.RoleDto;
import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.service.RoleService;

@Controller
@RequestMapping("/public/acessos")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping
	public ResponseEntity<ApiResponse> save(@RequestBody RoleDto roleDto) {
		return roleService.save(roleDto);
	}

	@GetMapping
	public ResponseEntity<ApiResponse> getAll() {
		return roleService.getAll();
	}
}