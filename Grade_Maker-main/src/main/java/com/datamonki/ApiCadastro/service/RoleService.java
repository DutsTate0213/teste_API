package com.datamonki.ApiCadastro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.ApiCadastro.dto.RoleDto;
import com.datamonki.ApiCadastro.exceptions.ValidationException;
import com.datamonki.ApiCadastro.model.Role;
import com.datamonki.ApiCadastro.repository.RoleRepository;
import com.datamonki.ApiCadastro.response.ApiResponse;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	private void verificarRole(RoleDto roleDto) {
		List<String> messages = new ArrayList<>();
		if (roleDto.name().isBlank()) {
			messages.add("O campo  name está vazio");
		}else if (roleDto.descricao().isBlank()) {
			messages.add("O campo  descricao está vazio");
		}else if(roleDto.name().length() < 5) {
			messages.add("O nome precisa ter mais de 5 caracateres");
		}else if(roleRepository.findByName(roleDto.name()).isPresent()) {
			messages.add("Usuário  já cadastrado");
		}	
		if (!messages.isEmpty()) {
			throw new ValidationException(messages);
		}
	}
	
	public ResponseEntity<ApiResponse> save(RoleDto roleDto){
		verificarRole(roleDto);
		Role role = new Role();
		role.setName(roleDto.name());
		role.setDescricao(roleDto.descricao());
		roleRepository.save(role);
		return ResponseEntity.ok(new ApiResponse("Acesso criado com sucesso", role));
	}
	 
	public ResponseEntity<ApiResponse> getAll(){
		List<Role> roles = roleRepository.findAll();
		return ResponseEntity.ok(new ApiResponse("Sucesso! Lista de roles cadastradas", roles));
	}
	
}
