package com.datamonki.ApiCadastro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.datamonki.ApiCadastro.dto.CurrentUserDto;
import com.datamonki.ApiCadastro.model.Usuario;
import com.datamonki.ApiCadastro.repository.UsuarioRepository;
import com.datamonki.ApiCadastro.response.ApiResponse;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
       try {
    	   Authentication authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
           );

           // Gera o token JWT
           String jwt = jwtUtil.generateToken(authentication.getName());

           // Cria o cookie HTTP-only com o token
           Cookie cookie = new Cookie("token", jwt);
           cookie.setHttpOnly(true); // Torna o cookie inacessível via JavaScript
           cookie.setSecure(true);  // Requer HTTPS (desativar para testes locais, mas usar em produção)
           cookie.setPath("/");     // Disponível em toda a aplicação
           cookie.setMaxAge(10 * 60 * 60); // 10 horas

           // Adiciona o cookie na resposta
           response.addCookie(cookie);
           return ResponseEntity.ok(new ApiResponse("Logado com sucesso", null));
       }catch(Exception e) {
    	   return ResponseEntity.status(401).body(new ApiResponse("Falha ao logar", null));
       }
       
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        // Remove o cookie JWT no logout
        Cookie cookie = new Cookie("token", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(0); // Expira imediatamente

        response.addCookie(cookie);
    }
    
    @GetMapping("/me")
    public ResponseEntity<ApiResponse> getCurrentUser(@CookieValue(value = "token", required = false) String token) {
        if (token == null) {
            return ResponseEntity.status(401).body(new ApiResponse("Usuário não autenticado", null));
        }

        try {
            // Extrai o nome do usuário do token
            String username = jwtUtil.extractUsername(token);

            // Busca o usuário no banco de dados
            Usuario usuario = usuarioRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            
            CurrentUserDto currentUserDto = new CurrentUserDto(usuario);
            // Retorna o usuário
            return ResponseEntity.ok(new ApiResponse("Usuário autenticado", currentUserDto));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new ApiResponse("Token inválido ou expirado", null));
        }
    }

}

@Getter
@Setter
class AuthRequest {
    private String username;
    private String password;
}
