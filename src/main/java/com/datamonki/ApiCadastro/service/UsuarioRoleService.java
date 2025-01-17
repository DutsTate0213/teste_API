package com.datamonki.ApiCadastro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.ApiCadastro.dto.UsuarioRoleDto;
import com.datamonki.ApiCadastro.exceptions.IdNotFoundException;
import com.datamonki.ApiCadastro.exceptions.ValidationException;
import com.datamonki.ApiCadastro.model.Role;
import com.datamonki.ApiCadastro.model.Usuario;
import com.datamonki.ApiCadastro.repository.RoleRepository;
import com.datamonki.ApiCadastro.repository.UsuarioRepository;
import com.datamonki.ApiCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class UsuarioRoleService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RoleRepository roleRepository;

	private void verificar(UsuarioRoleDto dto) {
		List<String> messages = new ArrayList<>();

		if (dto.usuarioId() == null) {
			throw new IdNotFoundException(
					"Não há usuário registrado com o id: " + dto.usuarioId() + ", verifique e tente novamente");
		}

		if (dto.roleId() == null) {
			throw new IdNotFoundException(
					"Não há role registrada com o id: " + dto.roleId() + ", verifique e tente novamente");
		}

		if (!usuarioRepository.findById(dto.usuarioId()).isPresent()) {
			throw new IdNotFoundException(
					"Não há usuário registrado com o id: " + dto.usuarioId() + ", verifique e tente novamente");
		}

		if (!roleRepository.findById(dto.roleId()).isPresent()) {
			throw new IdNotFoundException(
					"Não há role registrada com o id: " + dto.roleId() + ", verifique e tente novamente");
		}

		if (!messages.isEmpty()) {
			throw new ValidationException(messages);
		}
	}

	// Uma maneira diferente de adcionar mais um elemento em uma tabela
	// intermediária com spring
	@Transactional
	public ResponseEntity<ApiResponse> adicionarRoleAoUsuario(UsuarioRoleDto dto) {
		verificar(dto);
		Usuario usuario = usuarioRepository.findById(dto.usuarioId()).get();
		Role role = roleRepository.findById(dto.roleId()).get();
		usuario.getRole().add(role);
		return ResponseEntity.ok(new ApiResponse("Acesso concedido com sucesso", usuario.getUsername()));
	}

	// Uma maneira diferente de remover um elemento da tabela intermediária com
	// spring
	@Transactional
	public ResponseEntity<ApiResponse> removerRoleDoUsuario(UsuarioRoleDto dto) {
		verificar(dto);
		Usuario usuario = usuarioRepository.findById(dto.usuarioId()).get();
		Role role = roleRepository.findById(dto.roleId()).get();
		usuario.getRole().remove(role);
		return ResponseEntity.ok(new ApiResponse("Acesso removido com sucesso", usuario.getUsername()));
	}
}