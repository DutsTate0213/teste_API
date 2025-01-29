package com.datamonki.ApiCadastro.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.datamonki.ApiCadastro.repository.UsuarioRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	private UsuarioRepository usuarioRepository;
	
	
	 public CustomUserDetailService(UsuarioRepository usuarioRepository) {
	        this.usuarioRepository = usuarioRepository;
	    }
	
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        return usuarioRepository.findByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
	    }
	
}




//@Override
//public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
//	if("admin".equals(userName)) {
//		return User.builder()
//				.username("admin")
//				.password(new BCryptPasswordEncoder().encode("admin123"))
//				.roles("ADMIN")
//				.build();
//	}
//	throw new UsernameNotFoundException("Usuário não encontrado");
//}