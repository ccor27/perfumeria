package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Venta  implements Serializable{
	
	private Cliente cliente;
	private ArrayList<Producto> listaCompras;
	private double precio;
	private static final long serialVersionUID = 1L;
	
	
	public Venta() {

	}
  
	public Venta(Cliente cliente){
		this.cliente=cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Producto> getListaCompras() {
		return listaCompras;
	}

	public void setListaCompras(ArrayList<Producto> listaCompras) {
		this.listaCompras = listaCompras;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Venta [cliente=" + cliente + ", listaCompras=" + listaCompras + ", precio=" + precio + "]";
	}


	
	

}
