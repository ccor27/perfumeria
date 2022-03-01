package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Perfumeria implements Serializable{

	private String name;
	private ArrayList<Cliente> listClientes = new ArrayList<>();
	private ArrayList<Producto> listaProductos = new ArrayList<>();
	private ArrayList<Venta> listaVentas = new ArrayList<>();
	private ArrayList<Administrador> listaAdmins = new ArrayList<>();
	private static final long serialVersionUID = 1L;
	
	public Perfumeria() {
		// TODO Auto-generated constructor stub
	}
	

	public Perfumeria(String name) {
		super();
		this.name = name;
	}


	public ArrayList<Administrador> getListaAdmins() {
		return listaAdmins;
	}

	public void setListaAdmins(ArrayList<Administrador> listaAdmins) {
		this.listaAdmins = listaAdmins;
	}

	public ArrayList<Venta> getListaVentas() {
		return listaVentas;
	}

	public void setListaVentas(ArrayList<Venta> listaVentas) {
		this.listaVentas = listaVentas;
	}

	public ArrayList<Cliente> getListClientes() {
		return listClientes;
	}

	public void setListClientes(ArrayList<Cliente> listClientes) {
		this.listClientes = listClientes;
	}

	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public boolean agregarProducto(String nombre, String descripcion, double precio, String tipo,int cantidad) {
		
		Producto p = new Producto(nombre, descripcion, precio, tipo,cantidad);
		if(listaProductos.contains(p)){
			return false;	
		}else{
			listaProductos.add(p);
			return true;
		}
		
	}

	public boolean actulizarProducto(String nombreAntiguo, String nombre, String descripcion, double precio,
			String tipo, int cantidad) {
		
		Producto p =obtenerProducto(nombreAntiguo);
		if(p!=null){
			p.setNombre(nombre);
			p.setDescripcion(descripcion);
			p.setPrecio(precio);
			p.setTipoProducto(tipo);
			p.setCantidad(cantidad);
			return true;
		}else{
			return false;	
		}
		
		
	}
	
	private Producto obtenerProducto(String nombre){
	
		Producto p = null;
		for (int i = 0; i < listaProductos.size(); i++) {
			if(listaProductos.get(i).getNombre().equalsIgnoreCase(nombre)){
				p=listaProductos.get(i);
				break;
			}
		}
		return p;
	}

	public void eliminarProducto(Producto producto) {
		listaProductos.remove(producto);
	}

	public boolean agregarCliente(String nombre, String email) {
		Cliente c = new Cliente(nombre, email);
		if(listaProductos.contains(c)){
			return false;
		}else{
			listClientes.add(c);
			return true;
		}
	}

	public Cliente obtenerCliente(String nombre) {
		
		Cliente c = null;
		for (int i = 0; i < listClientes.size(); i++) {
			if(listClientes.get(i).getNombre().equalsIgnoreCase(nombre)){
				c=listClientes.get(i);
				break;
			}
		}
		return c;
	}

	public void agregarVenta(Venta venta) {
     listaVentas.add(venta);
     System.out.println(listaVentas);
	}

	public void cancelarCompra(ArrayList<Producto> listaCompra) {
		
		for (int i = 0; i < listaProductos.size(); i++) {
		
			Producto p = listaProductos.get(i);
			
			for (int j = 0; j < listaCompra.size(); j++) {
				
                if(listaCompra.get(j).getNombre().equalsIgnoreCase(p.getNombre())){
                	p.setCantidad(listaCompra.get(j).getCantidad()+p.getCantidad());
                }
				
			}
		}
	} 


}
