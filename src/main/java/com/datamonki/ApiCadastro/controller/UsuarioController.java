package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.datamonki.ApiCadastro.dto.DisponibilidadeDto;
import com.datamonki.ApiCadastro.dto.UsuarioDto;
import com.datamonki.ApiCadastro.exceptions.ValidationException;
import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.service.UsuarioService;

@Controller
@RequestMapping("/public/usuarios")
public class UsuarioController {
	@Autowired
    private UsuarioService usuarioService;
    //rivate final Role roleRepository;
	
	@PostMapping
	public ResponseEntity<ApiResponse> save(@RequestBody UsuarioDto usuarioDto) {
			try {
				return usuarioService.save(usuarioDto);
			} catch (ValidationException e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(), null));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body(new ApiResponse("Não foi possivel cadastrar o usuário, tente novamente, tente novamente", null));
			}
		}
	
	
}
