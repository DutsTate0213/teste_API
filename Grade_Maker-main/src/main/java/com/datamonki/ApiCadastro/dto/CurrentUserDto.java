package com.datamonki.ApiCadastro.dto;

import java.util.stream.Collectors;

import java.util.Set;
import com.datamonki.ApiCadastro.model.Role;
import com.datamonki.ApiCadastro.model.Usuario;

public record CurrentUserDto(Long id, String username, Set<String> roles) {

    // Construtor personalizado para converter o modelo Usuario em UsuarioDTO
    public CurrentUserDto(Usuario usuario) {
        this(usuario.getId(),
             usuario.getUsername(),
             usuario.getRole().stream()
                    .map(Role::getName)
                    .collect(Collectors.toSet())); // Retorna apenas os nomes dos papéis
    }
}