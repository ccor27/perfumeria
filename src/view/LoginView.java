package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import controller.LoginController;
import model.Administrador;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class LoginView {

	protected Shell shlLoginPerfumeria;
	private Text txtNombre;
	private Text txtCodigo;
	private Administrador admin;
    private LoginController loginController;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LoginView window = new LoginView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlLoginPerfumeria.open();
		shlLoginPerfumeria.layout();
		while (!shlLoginPerfumeria.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlLoginPerfumeria = new Shell();
		shlLoginPerfumeria.setSize(386, 238);
		shlLoginPerfumeria.setText("Login perfumeria");
		
		Label lblNombreDelAdministrador = new Label(shlLoginPerfumeria, SWT.NONE);
		lblNombreDelAdministrador.setBounds(10, 43, 140, 15);
		lblNombreDelAdministrador.setText("Nombre del administrador");
		
		Label lblCodigoDelAdministrador = new Label(shlLoginPerfumeria, SWT.NONE);
		lblCodigoDelAdministrador.setBounds(10, 87, 140, 15);
		lblCodigoDelAdministrador.setText("Codigo del administrador");
		
		txtNombre = new Text(shlLoginPerfumeria, SWT.BORDER);
		txtNombre.setBounds(175, 43, 171, 21);
		
		txtCodigo = new Text(shlLoginPerfumeria, SWT.BORDER);
		txtCodigo.setBounds(175, 81, 171, 21);
		
		Button btnIngresar = new Button(shlLoginPerfumeria, SWT.NONE);
		btnIngresar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(txtNombre.getText().equalsIgnoreCase("") || txtCodigo.getText().equalsIgnoreCase("")){
			          JOptionPane.showMessageDialog(null, "Debe llenar los campos");
		          }else{
			           
		        	  if(txtNombre.getText().equalsIgnoreCase(admin.getNombre()) && txtCodigo.getText().equalsIgnoreCase(admin.getCodigo())){
		        		  //shlLoginPerfumeria.close();
		        		  PerfumeriaView perfumeriaView = new PerfumeriaView();
		        		  perfumeriaView.open();
		        		  
		        	  }else{
		        		  JOptionPane.showMessageDialog(null, "Acceso no permitido");  
		        	  }
		           }
			}
		});
		btnIngresar.setBounds(136, 148, 75, 25);
		btnIngresar.setText("Ingresar");
		loginController = new LoginController();
		obtenerAdministrador();

	}
	
	private void obtenerAdministrador(){
		admin = loginController.obtenerAdmin(); 
	}
	
	
}
