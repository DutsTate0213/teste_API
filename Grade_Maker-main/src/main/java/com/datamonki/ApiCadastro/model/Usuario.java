package com.datamonki.ApiCadastro.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario implements UserDetails{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_roles") // Apenas define o nome da tabela intermediária
    private Set<Role> role = new HashSet<>();

    
    public Usuario(String username, String password, Set<Role> roles, String email) {
        this.username = username;
        this.password = password;
        this.role = roles;
        this.email = email;
    }

    // Getters e Setters omitidos para brevidade
    
    // Converte os papéis (roles) em GrantedAuthority
    @Override
    @Transactional
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.stream()
                .map(role -> (GrantedAuthority) () -> role.getName())
                .collect(Collectors.toSet());
    }
    
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

 