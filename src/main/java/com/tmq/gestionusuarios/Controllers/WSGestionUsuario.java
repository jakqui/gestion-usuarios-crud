package com.tmq.gestionusuarios.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tmq.gestionusuarios.POJO.GestionUsuario;
import com.tmq.gestionusuarios.security.JwtRecepcionToken;
import com.tmq.gestionusuarios.service.ServiceGestionUsuario;

@RestController
@RequestMapping("/general/gestion-usuarios")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
public class WSGestionUsuario {

    @Autowired
    ServiceGestionUsuario service;

	@Autowired
	JwtRecepcionToken recepcionTokenService;
    
    @PostMapping()
	public ResponseEntity<GestionUsuario> crear(@RequestBody GestionUsuario usuario, @RequestHeader("Authorization") String token){
		recepcionTokenService.validarToken(token);	
		GestionUsuario respuesta = null; 
		try{
			 respuesta = service.crear(usuario);	
			return new ResponseEntity<GestionUsuario>(respuesta, HttpStatus.CREATED);
		}catch(Exception e){
			return new ResponseEntity<GestionUsuario>(HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<GestionUsuario> buscar(@PathVariable int id, @RequestHeader("Authorization") String token) {
		recepcionTokenService.validarToken(token);		
		GestionUsuario usuario = null;
		try {
			usuario = service.buscar(id);
		} catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity<GestionUsuario>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<GestionUsuario>(usuario, HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable int id, @RequestBody Map<String, Object> datos, @RequestHeader("Authorization") String token) throws JsonProcessingException {
		recepcionTokenService.validarToken(token);	
		GestionUsuario usuario = null;
		try {
			usuario = service.actualizar(id, datos);
			return new ResponseEntity<GestionUsuario>(usuario, HttpStatus.OK);
	    } catch (Exception e) {	 
	    	return new ResponseEntity<>(HttpStatus.CONFLICT);
	    } 
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int id, @RequestHeader("Authorization") String token) {
		recepcionTokenService.validarToken(token);	
		try {
			service.eliminar(id);
			return new ResponseEntity<GestionUsuario>(HttpStatus.OK);
	    } catch (Exception e) {	 
	    	return new ResponseEntity<>(HttpStatus.CONFLICT);
	    } 
	}
}
