package com.tmq.gestionusuarios.service;

import java.util.Map;

import com.tmq.gestionusuarios.POJO.GestionUsuario;

public interface ServiceGestionUsuario {
	
	public GestionUsuario crear(GestionUsuario dp);
	public GestionUsuario buscar(int id);	
	public GestionUsuario actualizar(int id, Map<String, Object> datos);
	public void eliminar(int id);
}
