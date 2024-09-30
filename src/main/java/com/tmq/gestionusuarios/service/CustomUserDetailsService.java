package com.tmq.gestionusuarios.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tmq.gestionusuarios.DAO.DAOGestionUsuario;
import com.tmq.gestionusuarios.POJO.GestionUsuario;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DAOGestionUsuario userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<GestionUsuario> user = Optional.ofNullable(userRepository.buscarPorEmail(email));
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }        
        return new CustomUserDetails(user.get());
    }
}