package com.datamonki.ApiCadastro.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;

@Component
public class JwtUtil {
	
	@Value("${spring.boot.security.jwt.secret}")
	private String secret;
	
	@Value("${spring.boot.security.jwt.expiration}")
	private long expirationTime;
	

	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String generateToken(String userName) {
		return Jwts.builder()
				.setSubject(userName)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(getSigningKey())
				.compact();
	}
	
	//Captura o nome do usuário do token
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    //Validação se o nome do usuário está correta condizente com o do toke, caso não tenha espirado
    public boolean validateToken(String token, String userName) {
    	return userName.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    //Caso o token tenha espirado retorna true
    private boolean isTokenExpired(String token) {
    	return getClaims(token).getExpiration().before(new Date());
    }

    //Usa chave para verificar a integridade do token e decodifica o toke, para puxar as informações do payload
    private Claims getClaims(String token) {
    	return Jwts.parserBuilder()
    			.setSigningKey(getSigningKey())
    			.build()
    			.parseClaimsJws(token)
    			.getBody();
    }
}