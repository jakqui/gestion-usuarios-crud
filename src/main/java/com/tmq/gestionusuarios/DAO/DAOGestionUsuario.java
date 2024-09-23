package com.tmq.gestionusuarios.DAO;

import java.util.Map;

import com.tmq.gestionusuarios.POJO.GestionUsuario;

public interface DAOGestionUsuario {

	public int crear(GestionUsuario usuario);
	public GestionUsuario buscar(int id);	
	public void actualizar(int id, Map<String, Object> datos);
	public void eliminar(int id);
}
