package model;

import java.io.Serializable;

public class Producto implements Serializable{

	private String nombre;
    private String descripcion;
    private double precio;
    private String tipoProducto;
    private int cantidad;
    private static final long serialVersionUID = 1L;
	
    public Producto() {
	
	}

	public Producto(String nombre, String descripcion, double precio, String tipoProducto, int cantidad) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.tipoProducto = tipoProducto;
		this.cantidad = cantidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + ", tipoProducto="
				+ tipoProducto + ", cantidad=" + cantidad + "]";
	}




    
	
}
