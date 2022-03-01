package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class PerfumeriaView {

	protected Shell shlPerfumeria;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PerfumeriaView window = new PerfumeriaView();
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
		shlPerfumeria.open();
		shlPerfumeria.layout();
		while (!shlPerfumeria.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlPerfumeria = new Shell();
		shlPerfumeria.setSize(712, 546);
		shlPerfumeria.setText("Perfumeria");
		
		TabFolder tabFolder = new TabFolder(shlPerfumeria, SWT.NONE);
		tabFolder.setBounds(10, 10, 729, 533);
		
		//operacion
		TabItem tabItemOperacion = new TabItem(tabFolder, SWT.NONE);
		tabItemOperacion.setText("Operaciones");
		
		OperacionView CompositeOperacion = new OperacionView(tabFolder, SWT.NONE);
		tabItemOperacion.setControl(CompositeOperacion);
		
	   TabItem TabItemteResultado = new TabItem(tabFolder, SWT.NONE);
	   TabItemteResultado.setText("Resultado");
	   
	   ResultadoView CompositeResultado = new ResultadoView(tabFolder, SWT.NONE);
	   TabItemteResultado.setControl(CompositeResultado);
	 
	

	}

}
