package com.tmq.gestionusuarios.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tmq.gestionusuarios.POJO.GestionUsuario;

public class RMGestionUsuario implements RowMapper<GestionUsuario>{

	@Override
	public GestionUsuario mapRow(ResultSet rs, int i) throws SQLException {
		GestionUsuario u = new GestionUsuario();
		u.setId(rs.getInt("id"));
		u.setNombre(rs.getString("nombre"));
		u.setEmail(rs.getString("email"));
		u.setContrasena(rs.getString("contrasena"));
		u.setRol(rs.getString("rol"));
		u.setActivo(rs.getInt("activo"));
		return u;
	}

}
