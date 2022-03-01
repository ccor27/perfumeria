package controller;

import model.Administrador;

public class LoginController {

	private ModelFactoryController controller;
	
	public LoginController() {
	 controller =  ModelFactoryController.getInstance();
	}
	public Administrador obtenerAdmin(){
		return controller.getPerfumeria().getListaAdmins().get(0);
	}
}
