package controller;

import model.Perfumeria;
import model.Producto;

public class OperacionController {

	private ModelFactoryController controller;
	private Perfumeria perfumeria;
	
	public OperacionController() {
	controller = ModelFactoryController.getInstance();
	perfumeria=controller.getPerfumeria();
	}
	
	
	public Perfumeria getPerfumeria() {
		return perfumeria;
	}

	public boolean agregarProducto(String nombre, String descripcion, double precio, String tipo,int cantidad){
		return controller.agregarProducto(nombre,descripcion,precio,tipo,cantidad);
	}
	
	public boolean actualizarProducto(String nombreAntiguo,String nombre, String descripcion, double precio, String tipo,int cantidad){
		return controller.actulizarProducto(nombreAntiguo,nombre,descripcion,precio,tipo,cantidad);
	}

	public void elimiarProducto(Producto producto) {
		controller.elimiarProducto(producto);
	}
}
