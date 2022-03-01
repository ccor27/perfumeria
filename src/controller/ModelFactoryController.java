package controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.ietf.jgss.Oid;

import model.Administrador;
import model.Cliente;
import model.Perfumeria;
import model.Producto;
import model.Venta;
import persistencia.persistencia;

public class ModelFactoryController {
	
	
	
	private Perfumeria perfumeria;
	

	private static class SingletonHolder { 
		// El constructor de Singleton puede ser llamado desde aquí al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// Método para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}
	
	public Perfumeria getPerfumeria() {
		return perfumeria;
	}


	public ModelFactoryController(){
		//iniciar();
		//salvarDatos();
		cargarDatos();
	}

	public void iniciar(){
		perfumeria = new Perfumeria();
		Administrador admin = new Administrador("juan","123");
		perfumeria.getListaAdmins().add(admin);
		Producto p1 = new Producto("bulgari", "locion mujer", 10000, "locion", 4);
		Producto p2 = new Producto("mour", "locion mujer", 15000, "colonia", 5);
		perfumeria.getListaProductos().add(p1);
		perfumeria.getListaProductos().add(p2);
		Cliente c1 = new Cliente("Ernesto", "Ernesto@gmail.com");
		Cliente c2 = new Cliente("Albeiro", "Albeiro@gmail.com");
		perfumeria.getListClientes().add(c1);
		perfumeria.getListClientes().add(c2);
	}
	
	public void salvarDatos(){
		persistencia.guardarRecursoPerfumeriaXML(perfumeria);
	}
	public void cargarDatos(){
		perfumeria = persistencia.cargarRecursoPerfumeriaXML();
	}
	public boolean agregarProducto(String nombre, String descripcion, double precio, String tipo,int cantidad) {
		
		return perfumeria.agregarProducto(nombre,descripcion,precio,tipo,cantidad);
	}

	public boolean actulizarProducto(String nombreAntiguo, String nombre, String descripcion, double precio,
			String tipo, int cantidad) {
		
		return perfumeria.actulizarProducto(nombreAntiguo,nombre,descripcion,precio,tipo,cantidad);
	}

	public void elimiarProducto(Producto producto) {
		perfumeria.eliminarProducto(producto);
	}
	
	public ArrayList<String> obtenerNombresClientes(){
		
		ArrayList<String> listaNombresClientes = new ArrayList<>();
		ArrayList<Cliente> listaClientes = perfumeria.getListClientes();
	    for (int i = 0; i < listaClientes.size(); i++) {
			listaNombresClientes.add(listaClientes.get(i).getNombre());
			System.out.println(i);
		}
	    return listaNombresClientes;
	}

	public boolean agregarCliente(String nombre, String email) {
		return perfumeria.agregarCliente(nombre,email);
	}

	public Cliente obtenerCliente(String nombre) {
		return perfumeria.obtenerCliente(nombre);
	}

	public void agregarVenta(Venta venta) {
		perfumeria.agregarVenta(venta);
	}

	public void cancelarCompra(ArrayList<Producto> listaCompra) {
		perfumeria.cancelarCompra(listaCompra);;
	}
}
