package model;

import java.io.Serializable;

public class Cliente implements Serializable{

	private String nombre;
	private String email;
	private static final long serialVersionUID = 1L;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente(String nombre, String email) {
		super();
		this.nombre = nombre;
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", email=" + email + "]";
	}
	
}
