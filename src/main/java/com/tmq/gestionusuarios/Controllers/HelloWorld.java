package com.tmq.gestionusuarios.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH})
public class HelloWorld {
    @GetMapping("/")
	public ResponseEntity<String> index(){
		return new ResponseEntity<String>("Index 2", HttpStatus.OK);
	}
    
    @GetMapping("/hola-mundo") 
	public ResponseEntity<String> holaMundo(){
		return new ResponseEntity<String>("Hola Mundo... :)", HttpStatus.OK);
	}
}
