package view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import controller.OperacionController;
import model.Perfumeria;
import model.Producto;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import javax.swing.JOptionPane;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.beans.PojoProperties;

public class OperacionView extends Composite {
	private DataBindingContext m_bindingContext;
	
	
	private OperacionController operacionController = new OperacionController();
	private Perfumeria perfumeria = operacionController.getPerfumeria();
	private String tipoProducto="";
	private Producto producto;
	
	private Text txtNombre;
	private Text txtDescrip;
	private Text txtPrecio;
	private Table tblProductos;
	private Combo ComboTipoProduct;
	private Text txtCantidad;
	private TableViewer tableViewer;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public OperacionView(Composite parent, int style) {
		super(parent, style);
		
		Group grpDatosDelProducto = new Group(this, SWT.NONE);
		grpDatosDelProducto.setText("Datos del producto");
		grpDatosDelProducto.setBounds(10, 10, 613, 449);
		
		Label lblTipoDeProducto = new Label(grpDatosDelProducto, SWT.NONE);
		lblTipoDeProducto.setBounds(10, 41, 99, 15);
		lblTipoDeProducto.setText("Tipo de producto");
		
		Label lblNombre = new Label(grpDatosDelProducto, SWT.NONE);
		lblNombre.setBounds(301, 44, 55, 15);
		lblNombre.setText("Nombre");
		
		txtNombre = new Text(grpDatosDelProducto, SWT.BORDER);
		txtNombre.setBounds(421, 41, 139, 21);
		
		Label lblDescripcion = new Label(grpDatosDelProducto, SWT.NONE);
		lblDescripcion.setBounds(10, 93, 75, 15);
		lblDescripcion.setText("Descripcion");
		
		txtDescrip = new Text(grpDatosDelProducto, SWT.BORDER);
		txtDescrip.setBounds(130, 87, 139, 21);
		
		Label lblPrecio = new Label(grpDatosDelProducto, SWT.NONE);
		lblPrecio.setBounds(301, 93, 55, 15);
		lblPrecio.setText("Precio");
		
		txtPrecio = new Text(grpDatosDelProducto, SWT.BORDER);
		txtPrecio.setBounds(421, 87, 139, 21);
		
		tableViewer = new TableViewer(grpDatosDelProducto, SWT.BORDER | SWT.FULL_SELECTION);
		tblProductos = tableViewer.getTable();
		tblProductos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				producto = (Producto)tblProductos.getItem(tblProductos.getSelectionIndex()).getData();
				txtNombre.setText(producto.getNombre());
				txtDescrip.setText(producto.getDescripcion());
				txtPrecio.setText(String.valueOf(producto.getPrecio()));
				tipoProducto=producto.getTipoProducto();
				txtCantidad.setText(String.valueOf(producto.getCantidad()));
				
				if(tipoProducto.equalsIgnoreCase("perfume")){
					ComboTipoProduct.setText("PERFUME");
				}else{
					if(tipoProducto.equalsIgnoreCase("colonia")){
						ComboTipoProduct.setText("COLONIA");
					}else{
						ComboTipoProduct.setText("LOCION");
					}
				}
				
				
			}
		});
		tblProductos.setHeaderVisible(true);
		tblProductos.setLinesVisible(true);
		tblProductos.setBounds(10, 195, 581, 181);
		
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
		tblclmnDescripcion.setWidth(237);
		tblclmnDescripcion.setText("Descripcion");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnPrecio = tableViewerColumn_3.getColumn();
		tblclmnPrecio.setWidth(73);
		tblclmnPrecio.setText("Precio");
		
		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnCantidad = tableViewerColumn_4.getColumn();
		tblclmnCantidad.setWidth(66);
		tblclmnCantidad.setText("Cantidad");
		
		Button btnCrear = new Button(grpDatosDelProducto, SWT.NONE);
		btnCrear.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				if(!camposVacios()){
					
					if(operacionController.agregarProducto(txtNombre.getText(),txtDescrip.getText(),
							Double.parseDouble(txtPrecio.getText().trim()), tipoProducto,Integer.parseInt(txtCantidad.getText().trim()))){
						
						JOptionPane.showMessageDialog(null, "producto agregado");
						initDataBindings();
						vaciarCampos();
					}else{
						JOptionPane.showMessageDialog(null, "no se puede agregar el mismo producto");
					}
				}else{
					JOptionPane.showMessageDialog(null, "Debe llenar todos los campos ");
				}
				
				
			}

		});
		btnCrear.setBounds(73, 398, 75, 25);
		btnCrear.setText("Crear");
		
		Button btnActualizar = new Button(grpDatosDelProducto, SWT.NONE);
		btnActualizar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
	          if(producto!=null){
			if(!camposVacios()){
					
					if(operacionController.actualizarProducto(producto.getNombre(),txtNombre.getText(),txtDescrip.getText(),
							Double.parseDouble(txtPrecio.getText().trim()), tipoProducto,Integer.parseInt(txtCantidad.getText().trim()))){
					
						JOptionPane.showMessageDialog(null, "Actualizacion con exito");
						initDataBindings();
						vaciarCampos();
					}else{
						JOptionPane.showMessageDialog(null, "El producto no existe");
					}
				}else{
					JOptionPane.showMessageDialog(null, "Debe llenar todos los campos ");
				}
	          }else{
	        	  JOptionPane.showMessageDialog(null, "Debe seleccionar un producto");
	          }
			}
		});
		btnActualizar.setBounds(254, 398, 75, 25);
		btnActualizar.setText("Actualizar");
		
		Button btnEliminar = new Button(grpDatosDelProducto, SWT.NONE);
		btnEliminar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(producto!=null){
					operacionController.elimiarProducto(producto);
					JOptionPane.showMessageDialog(null, "Eliminacion con exito");
					initDataBindings();
					vaciarCampos();
				}else{
					JOptionPane.showMessageDialog(null, "Debe seleccionar un producto");
				}
			}
		});
		btnEliminar.setBounds(423, 398, 75, 25);
		btnEliminar.setText("Eliminar");
		
		ComboViewer comboViewer = new ComboViewer(grpDatosDelProducto, SWT.NONE);
		ComboTipoProduct = comboViewer.getCombo();
		ComboTipoProduct.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(ComboTipoProduct.getText().equalsIgnoreCase("perfume")){
					tipoProducto = "perfume";
				}else{
					if(ComboTipoProduct.getText().equalsIgnoreCase("colonia")){
						tipoProducto="colonia";
					}else{
						tipoProducto="locion";
					}
				}
		         
				
			}
		});
		ComboTipoProduct.setItems(new String[] {"PERFUME", "COLONIA", "LOCION"});
		ComboTipoProduct.setBounds(130, 41, 139, 23);
		
		Label lblCantidad = new Label(grpDatosDelProducto, SWT.NONE);
		lblCantidad.setBounds(10, 139, 55, 15);
		lblCantidad.setText("Cantidad");
		
		txtCantidad = new Text(grpDatosDelProducto, SWT.BORDER);
		txtCantidad.setBounds(130, 133, 139, 21);
		m_bindingContext = initDataBindings();

	}
	
	private boolean camposVacios(){
		 
		if(txtDescrip.getText().equalsIgnoreCase("") || txtNombre.getText().equalsIgnoreCase("") ||
				txtPrecio.getText().equalsIgnoreCase("") || tipoProducto.equalsIgnoreCase("") ||
				txtCantidad.getText().equalsIgnoreCase("")){
			return true;
		}else{
			return false;
			
		}
	}
	
	private void vaciarCampos(){
		txtNombre.setText("");
		txtDescrip.setText("");
		txtPrecio.setText("");
		txtCantidad.setText("");
		tipoProducto="";
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
