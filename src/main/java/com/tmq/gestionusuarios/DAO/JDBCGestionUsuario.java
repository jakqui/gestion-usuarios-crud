package com.tmq.gestionusuarios.DAO;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tmq.gestionusuarios.POJO.GestionUsuario;

@Repository
public class JDBCGestionUsuario implements DAOGestionUsuario{
	
	@Autowired
	JdbcTemplate jdbcTemplate;	
	
	@Override
	public int crear(GestionUsuario usuario) {
		String sql = "INSERT INTO usuarios "
				+"(nombre, email, contraseña, rol) "
				+ "VALUES "
				+ "(?, ?, ?, ?)";
		jdbcTemplate.update(sql, usuario.getNombre(), usuario.getEmail(), usuario.getContrasena(), usuario.getRol());
		return jdbcTemplate.queryForObject("SELECT last_insert_id()", Integer.class);
	}

	@Override
	public GestionUsuario buscar(int id) {
		String sql = "SELECT * FROM usuarios WHERE activo = 1 AND id = ?";
		GestionUsuario usuario = jdbcTemplate.queryForObject(sql,new RMGestionUsuario(), id);
		return usuario;
	}
	
	@Override
	public void actualizar(int id, Map<String, Object> datos) {
		// Construcción dinámica de la parte SET de la consulta
		StringBuilder sql = new StringBuilder("UPDATE usuarios SET ");
		Object[] params = new Object[datos.size() + 1];
		int index = 0;
		 
		for (Map.Entry<String, Object> entry : datos.entrySet()) {
			sql.append(entry.getKey()).append(" = ?,");
			params[index++] = entry.getValue();  // Agregar valor al arreglo de parámetros
		}
		 
		// Elimina la última coma y agrega la condición WHERE
		sql.setLength(sql.length() - 1);  // Eliminar la última coma
		sql.append(" WHERE id = ?");
		params[index] = id;  // Agregar el ID al final de los parámetros
		 
		// Ejecutar la actualización
		jdbcTemplate.update(sql.toString(), params);
	}

	@Override
	public void eliminar(int id) {
		String sql = "UPDATE usuarios SET activo = 0 WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	
	
}