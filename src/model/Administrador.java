package model;

import java.io.Serializable;

public class Administrador implements Serializable{

	private String nombre;
	private String codigo;
	private static final long serialVersionUID = 1L;

	public Administrador() {
		// TODO Auto-generated constructor stub
	}

	public Administrador(String nombre, String codigo) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Administrador [nombre=" + nombre + ", codigo=" + codigo + "]";
	}
	
}
