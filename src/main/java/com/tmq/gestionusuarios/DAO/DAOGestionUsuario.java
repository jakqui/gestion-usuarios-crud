package com.tmq.gestionusuarios.DAO;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import com.tmq.gestionusuarios.POJO.GestionUsuario;

public interface DAOGestionUsuario {

	public int crear(GestionUsuario usuario);
	public GestionUsuario buscar(int id);	
	public void actualizar(int id, Map<String, Object> datos);
	public void eliminar(int id);
	public GestionUsuario loadUserByUsername(String username);
	public GestionUsuario findByEmail(String email);
}
