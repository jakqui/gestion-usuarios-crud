package com.tmq.gestionusuarios.service;

import java.util.ArrayList;

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
        GestionUsuario user = userRepository.findByEmail(email);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getContrasena(), new ArrayList<>());
    }
}