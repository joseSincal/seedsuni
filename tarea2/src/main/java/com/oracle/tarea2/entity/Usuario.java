package com.oracle.tarea2.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name="USUARIO")
public class Usuario implements Serializable{
	private static final long serialVersionUID = 3052416686714504697L;
	
	@Id
	@Column(name="IDUSUARIO")
	private Integer idusuario;

	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="FECHA_CREACION")
	private String fechaCreacion;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name= "EMAIL")
	private String email;

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
