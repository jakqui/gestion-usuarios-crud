package com.tmq.gestionusuarios.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tmq.gestionusuarios.POJO.GestionUsuario;
import com.tmq.gestionusuarios.service.ServiceGestionUsuario;

@RestController
@RequestMapping("/general/gestion-usuarios")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
public class WSGestionUsuario {

    @Autowired
    ServiceGestionUsuario service;
    
    @PostMapping()
	public ResponseEntity<GestionUsuario> crear(@RequestBody GestionUsuario usuario){
		GestionUsuario respuesta = null; 
		try{
			 respuesta = service.crear(usuario);	
			return new ResponseEntity<GestionUsuario>(respuesta, HttpStatus.CREATED);
		}catch(Exception e){
			return new ResponseEntity<GestionUsuario>(HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<GestionUsuario> buscar(@PathVariable int id) {
		GestionUsuario usuario = null;
		try {
			usuario = service.buscar(id);
		} catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity<GestionUsuario>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<GestionUsuario>(usuario, HttpStatus.OK);
	}
}