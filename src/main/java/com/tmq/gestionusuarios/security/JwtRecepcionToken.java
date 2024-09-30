package com.tmq.gestionusuarios.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtRecepcionToken {
    
    @Autowired
	UserDetailsService userDetailsService;

	@Autowired
	JwtUtil jwtService;


    public ResponseEntity<?> validarToken(String token){
        // Quitar "Bearer " del token si está presente
		if (token.startsWith("Bearer ")) {
			token = token.substring(7);
		}

		// Obtener el Authentication del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// Obtener el nombre de usuario desde el Authentication
        String username = authentication.getName();
		// Cargar UserDetails usando el UserDetailsService
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		// Validar el token
		if (!jwtService.validateToken(token, userDetails)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401 Unauthorized
		}
		System.out.println("Roles Usuario en Sesión: "+authentication.getAuthorities());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
