package com.tmq.gestionusuarios.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmq.gestionusuarios.DAO.DAOGestionUsuario;
import com.tmq.gestionusuarios.POJO.GestionUsuario;

@Service
public class LogicaGestionUsuario implements ServiceGestionUsuario {
	
	@Autowired
	DAOGestionUsuario repositorio;

	@Override
	public GestionUsuario crear(GestionUsuario usuario) {
		int id_usuario = repositorio.crear(usuario);
		return repositorio.buscar(id_usuario);
	}

	@Override
	public GestionUsuario buscar(int id) {
		return repositorio.buscar(id);
	}

	@Override
	public GestionUsuario actualizar(int id, Map<String, Object> datos) {
		repositorio.actualizar(id, datos);
		return repositorio.buscar(id);
	}

	@Override
	public void eliminar(int id) {
		repositorio.eliminar(id);
	}
}
