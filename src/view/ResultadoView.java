package view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import controller.ResultadoController;
import model.Cliente;
import model.Perfumeria;

import org.eclipse.swt.widgets.Combo;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.beans.PojoObservables;
import model.Producto;
import model.Venta;

import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.beans.PojoProperties;

public class ResultadoView extends Composite {
	private DataBindingContext m_bindingContext;
	private Text txtNombreCliente;
	private Text txtEmail;
	private Table tblProductos;
	private ComboViewer comboViewer;

	private ResultadoController resultadoController = new ResultadoController();
	private Perfumeria  perfumeria = resultadoController.getPerfumeria();
	private ArrayList<String> listaNombresClientes = new ArrayList();
	private Combo comboNombreCliente;
	private Button btnAgregar;
	private Button btnAgregarProducto;
	private Button btnVerCompra;
	private Button btnComprar;
	private Button btnCancelarCompra;
	private TableViewer tableViewer;
	
	private Cliente cliente;
	private Venta venta;
	private Producto productoSeleccionado;
	private double precio = 0;
	private ArrayList<Producto> listaCompra = new ArrayList<>();
	private Text txtCantidad;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ResultadoView(Composite parent, int style) {
		super(parent, style);
		

		
		Group grpDatosDelCliente = new Group(this, SWT.NONE);
		grpDatosDelCliente.setText("Datos ");
		grpDatosDelCliente.setBounds(10, 10, 626, 446);
		
		Group grpCliente = new Group(grpDatosDelCliente, SWT.NONE);
		grpCliente.setText("cliente");
		grpCliente.setBounds(10, 22, 606, 105);
		
		Label lblNombreCliente = new Label(grpCliente, SWT.NONE);
		lblNombreCliente.setBounds(10, 82, 88, 15);
		lblNombreCliente.setText("Nombre cliente");
		
		txtNombreCliente = new Text(grpCliente, SWT.BORDER);
		txtNombreCliente.setBounds(123, 76, 91, 21);
		
		Label lblEmail = new Label(grpCliente, SWT.NONE);
		lblEmail.setBounds(248, 82, 55, 15);
		lblEmail.setText("Email");
		
		txtEmail = new Text(grpCliente, SWT.BORDER);
		txtEmail.setBounds(361, 79, 88, 21);
		
		Label lblClienteExistente = new Label(grpCliente, SWT.NONE);
		lblClienteExistente.setBounds(248, 26, 88, 15);
		lblClienteExistente.setText("Nombre");
		
		comboViewer = new ComboViewer(grpCliente, SWT.NONE);
		comboNombreCliente = comboViewer.getCombo();
		comboNombreCliente.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				cliente = resultadoController.obtenerCliente(comboNombreCliente.getText());
				venta = new Venta(cliente);
				txtNombreCliente.setEnabled(false);
				txtEmail.setEnabled(false);
				btnAgregar.setEnabled(false);
				btnAgregarProducto.setEnabled(true);
				btnCancelarCompra.setEnabled(true);
				btnComprar.setEnabled(true);
				btnVerCompra.setEnabled(true);
				txtCantidad.setEnabled(true);
			}
		});
		comboNombreCliente.setBounds(361, 26, 91, 23);
		
		btnAgregar = new Button(grpCliente, SWT.NONE);
		btnAgregar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(txtNombreCliente.getText().equalsIgnoreCase("") || txtEmail.getText().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "debe llenar todos los campos");
				}else{
					
					if(resultadoController.agregarCliente(txtNombreCliente.getText(),txtEmail.getText())){
						cliente = resultadoController.obtenerCliente(txtNombreCliente.getText());
						venta = new Venta(cliente);
						btnAgregarProducto.setEnabled(true);
						btnCancelarCompra.setEnabled(true);
						btnComprar.setEnabled(true);
						btnVerCompra.setEnabled(true);
						txtCantidad.setEnabled(true);
						JOptionPane.showMessageDialog(null, "cliente agregado");
						
					}else{
						JOptionPane.showMessageDialog(null, "el cliente ya existe");
					}
				}
			}
		});
		btnAgregar.setBounds(500, 77, 75, 25);
		btnAgregar.setText("Agregar");
		
		ComboViewer comboViewer_1 = new ComboViewer(grpCliente, SWT.NONE);
		Combo comboExistencia = comboViewer_1.getCombo();
		comboExistencia.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(comboExistencia.getText().equalsIgnoreCase("si")){
					comboNombreCliente.setEnabled(true);
					btnAgregarProducto.setEnabled(false);
					btnCancelarCompra.setEnabled(false);
					btnComprar.setEnabled(false);
					btnVerCompra.setEnabled(false);
					txtNombreCliente.setEnabled(false);
					txtEmail.setEnabled(false);
					btnAgregar.setEnabled(false);
					llenarComboClientesExistentes();
				}else{
					comboNombreCliente.setEnabled(false);
					txtNombreCliente.setEnabled(true);
					txtEmail.setEnabled(true);
					btnAgregar.setEnabled(true);
					btnAgregarProducto.setEnabled(false);
					btnCancelarCompra.setEnabled(false);
					btnComprar.setEnabled(false);
					btnVerCompra.setEnabled(false);
				}
			}
		});
		comboExistencia.setItems(new String[] {"SI", "NO"});
		comboExistencia.setBounds(123, 26, 91, 23);
		
		Label lblExistencia = new Label(grpCliente, SWT.NONE);
		lblExistencia.setBounds(10, 26, 55, 15);
		lblExistencia.setText("Existencia");
		
		Group grpProductos = new Group(grpDatosDelCliente, SWT.NONE);
		grpProductos.setText("Productos");
		grpProductos.setBounds(20, 133, 606, 198);
		
		tableViewer = new TableViewer(grpProductos, SWT.BORDER | SWT.FULL_SELECTION);
		tblProductos = tableViewer.getTable();
		tblProductos.setLinesVisible(true);
		tblProductos.setHeaderVisible(true);
		tblProductos.setBounds(10, 24, 586, 164);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnProducto = tableViewerColumn.getColumn();
		tblclmnProducto.setWidth(100);
		tblclmnProducto.setText("Producto");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNombre = tableViewerColumn_1.getColumn();
		tblclmnNombre.setWidth(100);
		tblclmnNombre.setText("Nombre");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnDescripcion = tableViewerColumn_2.getColumn();
		tblclmnDescripcion.setWidth(236);
		tblclmnDescripcion.setText("Descripcion");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnPrecio = tableViewerColumn_3.getColumn();
		tblclmnPrecio.setWidth(74);
		tblclmnPrecio.setText("Precio");
		
		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnCantidad = tableViewerColumn_4.getColumn();
		tblclmnCantidad.setWidth(72);
		tblclmnCantidad.setText("Cantidad");
		
		Group grpAcciones = new Group(grpDatosDelCliente, SWT.NONE);
		grpAcciones.setText("Acciones");
		grpAcciones.setBounds(10, 337, 606, 98);
		
		btnAgregarProducto = new Button(grpAcciones, SWT.NONE);
		btnAgregarProducto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(txtCantidad.getText().trim().equalsIgnoreCase("")){
					JOptionPane.showMessageDialog(null, "Debe indicar la cantidad");
				}else{

				    productoSeleccionado = (Producto) tblProductos.getItem(tblProductos.getSelectionIndex()).getData();
					int cantidadCompra = Integer.parseInt(txtCantidad.getText().trim());//se obtiene la cantidad
					
					if(cantidadCompra<=productoSeleccionado.getCantidad()){
						

						Producto productVenta = new Producto();//se crea el producto a vender
						//se le da los valores al producto a vender
						productVenta.setNombre(productoSeleccionado.getNombre());
						productVenta.setDescripcion(productoSeleccionado.getDescripcion());
						productVenta.setCantidad(cantidadCompra);
						productVenta.setTipoProducto(productoSeleccionado.getTipoProducto());
						productVenta.setPrecio(productoSeleccionado.getPrecio()*cantidadCompra);
						listaCompra.add(productVenta);
						precio+=productoSeleccionado.getPrecio()*cantidadCompra;
						//se le actualiza la cantidad al producto que se vendio
						productoSeleccionado.setCantidad(productoSeleccionado.getCantidad()-cantidadCompra);
						initDataBindings();
						
					}else{
						JOptionPane.showMessageDialog(null, "NO hay la cantidad pedida");
					}

				}

				
			}
		});
		btnAgregarProducto.setBounds(59, 63, 99, 25);
		btnAgregarProducto.setText("Agregar producto");
		
		btnVerCompra = new Button(grpAcciones, SWT.NONE);
		btnVerCompra.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				JOptionPane.showMessageDialog(null, verCompra());
			}
		});
		btnVerCompra.setBounds(199, 63, 99, 25);
		btnVerCompra.setText("Ver compra");
		
		btnComprar = new Button(grpAcciones, SWT.NONE);
		btnComprar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				venta.setListaCompras(listaCompra);
				venta.setPrecio(precio);
				cliente=null;
				productoSeleccionado=null;
				listaCompra.removeAll(listaCompra);
				initDataBindings();
				bloquearCamposIniciales();
				resultadoController.agregarVenta(venta);
				
			}
		});
		btnComprar.setBounds(336, 63, 99, 25);
		btnComprar.setText("Comprar");
		
		btnCancelarCompra = new Button(grpAcciones, SWT.NONE);
		btnCancelarCompra.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				resultadoController.cancelaCompra(listaCompra);
				listaCompra.removeAll(listaCompra);
				precio=0;
				cliente=null;
				productoSeleccionado=null;
				initDataBindings();
				bloquearCamposIniciales();
			}
		});
		btnCancelarCompra.setBounds(481, 63, 99, 25);
		btnCancelarCompra.setText("Cancelar compra");
		
		Label lblCantidad = new Label(grpAcciones, SWT.NONE);
		lblCantidad.setBounds(10, 30, 55, 15);
		lblCantidad.setText("Cantidad");
		
		txtCantidad = new Text(grpAcciones, SWT.BORDER);
		txtCantidad.setBounds(82, 24, 76, 21);
		

		bloquearCamposIniciales();
		m_bindingContext = initDataBindings();

	}
	
	
	private void llenarComboClientesExistentes(){
		obtenerNombresClientes();
		for (int i = 0; i < listaNombresClientes.size(); i++) {
			comboNombreCliente.add(listaNombresClientes.get(i), i);
		}
	}
	private void obtenerNombresClientes(){
		listaNombresClientes=resultadoController.obtenerNombresCLientes();
	}
	private void bloquearCamposIniciales(){
	txtCantidad.setEnabled(false);
	txtNombreCliente.setEnabled(false);
	txtEmail.setEnabled(false);
	comboNombreCliente.setEnabled(false);
	btnAgregar.setEnabled(false);
	btnAgregarProducto.setEnabled(false);
	btnCancelarCompra.setEnabled(false);
	btnComprar.setEnabled(false);
	btnVerCompra.setEnabled(false);
	
	}
	
	private String verCompra(){
		
		String compraPrevia="";
		for (Producto producto : listaCompra) {
			compraPrevia+="producto: "+producto.getNombre()+"- cantidad: "+producto.getCantidad()+"- precio: "+producto.getPrecio()+"\n";
		}
		return compraPrevia;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(), Producto.class, new String[]{"tipoProducto", "nombre", "descripcion", "precio", "cantidad"});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList listaProductosPerfumeriaObserveList = PojoProperties.list("listaProductos").observe(perfumeria);
		tableViewer.setInput(listaProductosPerfumeriaObserveList);
		//
		return bindingContext;
	}
}
