package com.tmq.gestionusuarios.POJO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor
public class GestionUsuario {
    private int id;
    private String nombre;
    private String email;
    private String contrasena;
    private String rol;
    private int activo;
}
