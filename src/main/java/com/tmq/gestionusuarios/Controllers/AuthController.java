package com.tmq.gestionusuarios.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmq.gestionusuarios.security.JwtUtil;
import com.tmq.gestionusuarios.service.CustomUserDetailsService;
import com.tmq.gestionusuarios.service.ServiceGestionUsuario;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ServiceGestionUsuario userService;

    /*@Autowired
    private TokenBlacklistService tokenBlacklistService;*/

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getContrasena())
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error en la autenticación: " + e.getMessage());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new LoginResponse(jwt));
    }
    
}

class LoginResponse {
    private final String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

@Data
class LoginRequest {
    private String email;
    private String contrasena;
}