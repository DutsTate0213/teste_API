package com.datamonki.ApiCadastro.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.datamonki.ApiCadastro.dto.UsuarioDto;
import com.datamonki.ApiCadastro.exceptions.ValidationException;
import com.datamonki.ApiCadastro.model.Role;
import com.datamonki.ApiCadastro.model.Usuario;
import com.datamonki.ApiCadastro.repository.UsuarioRepository;
import com.datamonki.ApiCadastro.response.ApiResponse;


@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private void verificarUsuario(UsuarioDto usuarioDto) {
		List<String> messages = new ArrayList<>();
		if (usuarioDto.userName().isBlank()) {
			messages.add("O campo  userName está vazio");
		}else if (usuarioDto.password().isBlank()) {
			messages.add("O campo  password está vazio");
		}else if(usuarioRepository.findByUsername(usuarioDto.userName()).isPresent()) {
			messages.add("Usuário  já cadastrado");
		}else if(usuarioDto.password().length() < 5) {
			messages.add("A senha precisa ter mais de 5 caracateres");
		}	
		
		if (!messages.isEmpty()) {
			throw new ValidationException(messages);
		}
	
	}
	

	public ResponseEntity<ApiResponse> save (UsuarioDto usuarioDto){
		verificarUsuario(usuarioDto);
		String senhaCodificada = passwordEncoder.encode(usuarioDto.password());
		Usuario usuario = new Usuario();
		usuario.setUsername(usuarioDto.userName());
		usuario.setPassword(senhaCodificada);
		usuarioRepository.save(usuario);
		return ResponseEntity.ok(new ApiResponse("Usuário cadastrado com sucesso", usuario.getUsername()));
		
	}
}
