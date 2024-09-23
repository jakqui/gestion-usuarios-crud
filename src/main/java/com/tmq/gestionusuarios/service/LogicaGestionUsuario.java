package com.tmq.gestionusuarios.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
