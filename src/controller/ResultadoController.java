package controller;

import java.util.ArrayList;

import model.Cliente;
import model.Perfumeria;
import model.Producto;
import model.Venta;

public class ResultadoController {

	private ModelFactoryController controller= ModelFactoryController.getInstance();
	private Perfumeria perfumeria =controller.getPerfumeria();
	
	public ArrayList<String> obtenerNombresCLientes(){
		return controller.obtenerNombresClientes();
	}

	public Perfumeria getPerfumeria() {
		return perfumeria;
	}

	public boolean agregarCliente(String nombre, String email) {
		return controller.agregarCliente(nombre,email);
	}

	public Cliente obtenerCliente(String nombre) {
		return controller.obtenerCliente(nombre);
	}

	public void agregarVenta(Venta venta) {
		controller.agregarVenta(venta);
	}

	public void cancelaCompra(ArrayList<Producto> listaCompra) {
		controller.cancelarCompra(listaCompra);
		
	}
	
}
