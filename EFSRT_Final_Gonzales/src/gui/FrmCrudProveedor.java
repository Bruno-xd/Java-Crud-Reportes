package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidad.Proveedor;
import model.ProveedorModel;
import util.Validaciones;

public class FrmCrudProveedor extends JInternalFrame implements MouseListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblApellido;
	private JTextField txtApellido;
	private JLabel lblDni;
	private JTextField txtDni;
	private JLabel lblDireccion;
	private JTextField txtDireccion;
	private JLabel lblTelefono;
	private JTextField txtTelefono;
	private JLabel lblCorreo;
	private JTextField txtCorreo;
	private JLabel lblPais;
	private JComboBox<String>cboPais;
	private JButton btnIngresar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private JTable table;
	private int idSeleccionado=-1;
	int hoveredRow=-1,hoveredColumn=-1;
	private JLabel lblMantenimientoProveedor;
	
	public FrmCrudProveedor() {
		getContentPane().setBackground(SystemColor.menu);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento de Proveedor");
		setBounds(100, 100, 898, 627);
		getContentPane().setLayout(null);
		
		lblNombre =  new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(35, 79, 100, 22);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(159, 80, 213, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblApellido.setBounds(448, 79, 100, 22);
		getContentPane().add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(572, 80, 213, 20);
		getContentPane().add(txtApellido);
		
		lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDni.setBounds(35, 130, 100, 22);
		getContentPane().add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(159, 131, 213, 20);
		getContentPane().add(txtDni);
		
		lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDireccion.setBounds(448, 130, 100, 22);
		getContentPane().add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(572, 131, 213, 20);
		getContentPane().add(txtDireccion);
		
		lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTelefono.setBounds(35, 181, 100, 22);
		getContentPane().add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(159, 182, 213, 20);
		getContentPane().add(txtTelefono);
		
		lblCorreo = new JLabel("Correo:");
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCorreo.setBounds(448, 181, 100, 22);
		getContentPane().add(lblCorreo);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(572, 182, 213, 20);
		getContentPane().add(txtCorreo);
		
		lblPais = new JLabel("Pa\u00EDs:");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPais.setBounds(35, 233, 100, 22);
		getContentPane().add(lblPais);
		
		cboPais = new JComboBox<String>();
		cboPais.setModel(new DefaultComboBoxModel<String>(new String[] {"[Seleccione]", "Per\u00FA", "Chile", "Colombia", "Brasil"}));
		
		cboPais.setBounds(159, 233, 213, 22);
		getContentPane().add(cboPais);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnIngresar.addActionListener(this);
		btnIngresar.setBounds(268, 289, 89, 23);
		getContentPane().add(btnIngresar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(367, 289, 89, 23);
		getContentPane().add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(471, 289, 89, 23);
		getContentPane().add(btnEliminar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 334, 866, 246);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Nombres", "Apellidos","DNI", "Dirección", "Teléfono","Correo","País",
				}
				));
		scrollPane.setViewportView(table);
		//alineación
				DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
				rightRenderer.setHorizontalAlignment(JLabel.CENTER);
				table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
				
				//tamano de la fila	
				table.getColumnModel().getColumn(0).setPreferredWidth(15);
				table.getColumnModel().getColumn(1).setPreferredWidth(120);
				table.getColumnModel().getColumn(2).setPreferredWidth(120);
				table.getColumnModel().getColumn(3).setPreferredWidth(50);
				table.getColumnModel().getColumn(4).setPreferredWidth(60);
				table.getColumnModel().getColumn(5).setPreferredWidth(60);
				table.getColumnModel().getColumn(6).setPreferredWidth(90);
				table.getColumnModel().getColumn(7).setPreferredWidth(60);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
				
				//selecciona una sola fila
				table.setRowSelectionAllowed(true);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
				//desabilita mover las columnas
				table.getTableHeader().setReorderingAllowed(false);
				
				scrollPane.setViewportView(table);
				
				//color de la fila seleccionada
				table.setSelectionBackground(Color.yellow);
				
				lblMantenimientoProveedor = new JLabel("Mantenimiento Proveedor");
				lblMantenimientoProveedor.setHorizontalAlignment(SwingConstants.CENTER);
				lblMantenimientoProveedor.setFont(new Font("Sylfaen", Font.BOLD, 24));
				lblMantenimientoProveedor.setBounds(10, 10, 866, 37);
				getContentPane().add(lblMantenimientoProveedor);
				
			    //No se pueda editar
			    table.setDefaultEditor(Object.class, null);
			    
				//Efecto Rollover
			    table.addMouseMotionListener(new MouseMotionListener() {
			        @Override
			        public void mouseMoved(MouseEvent e) {
			            Point p = e.getPoint();
			            hoveredRow = table.rowAtPoint(p);
			            hoveredColumn = table.columnAtPoint(p);
			            table.setRowSelectionInterval(hoveredRow, hoveredRow);
			            table.repaint();    
			        }
			        @Override
			        public void mouseDragged(MouseEvent e) {
			            hoveredRow = hoveredColumn = -1;
			            table.repaint();
			        }
			    });
			    
				lista();	
	}
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
	void limpiarCajasTexto() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtDni.setText("");
		txtDireccion.setText(null);
		txtTelefono.setText(null);
		txtCorreo.setText(null);
		cboPais.setSelectedIndex(0);
		txtNombre.requestFocus();
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			mouseClickedTable(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void mouseClickedTable(MouseEvent e) {
		busca();
	}
	protected void handle_table_mouseClicked(MouseEvent e) {
		//busca();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
		if (e.getSource() == btnIngresar) {
			actionPerformedBtnIngresar(e);
		}
	}
	protected void actionPerformedBtnIngresar(ActionEvent e) {
		inserta();
	}
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		actualiza();
		
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		elimina();
	}
	//------------------------------lista
	private void lista() {
		ProveedorModel model= new ProveedorModel();
		List<Proveedor> lista=model.listarProveedor();
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		Object[] fila = null;
		for (Proveedor x : lista) {
			fila = new Object[] {x.getIdProveedor(), x.getNombre(), x.getApellido(),
					x.getDni(), x.getDireccion(),x.getTelefono(),x.getCorreo(),x.getPais(),x.getFechaRegistro()};
			dtm.addRow(fila);
		}
	}
	private void inserta() {
		String nom = txtNombre.getText();
		String ape = txtApellido.getText();
		String dni = txtDni.getText();
		String direccion=txtDireccion.getText();
		String telefono=txtTelefono.getText();
		String Correo=txtCorreo.getText();
		String Pais=cboPais.getSelectedItem().toString();
		
		if (!nom.matches(Validaciones.TEXTO)) {	mensaje("El nombre es de 2 a 20 caracteres");
		}else if (!ape.matches(Validaciones.TEXTO)) {mensaje("El apellido es de 2 a 20 caracteres");
		}else if (!dni.matches(Validaciones.DNI)) {	mensaje("El nombre es de 8 digitos");	
		}else if(!direccion.matches(Validaciones.DIRECCION)) {mensaje("La dirección es de 2 a 40 caracteres");
		}else if(!telefono.matches(Validaciones.TELEFONO)) {mensaje("El teléfono debe ser de 9 dígitos");
		}else if(!Correo.matches(Validaciones.CORREO)) {mensaje("El correo debe incluir @ y .");}
		
		
		else {
			Proveedor obj = new Proveedor();
			obj.setNombre(nom);
			obj.setApellido(ape);
			obj.setDni(dni);
			obj.setDireccion(direccion);
			obj.setTelefono(telefono);
			obj.setCorreo(Correo);
			obj.setPais(Pais);
			
			ProveedorModel model = new ProveedorModel();
			int salida = model.insertaProveedor(obj);
			if (salida > 0) {
				lista();
				limpiarCajasTexto();
				idSeleccionado = -1;
				mensaje("Se insertó correctamente");
			}else {
				mensaje("Error en el Registro");
			}
			
		}	
	}
	private void elimina() {
		if(idSeleccionado==-1) {mensaje("Seleecione una fila");}
		else {
			ProveedorModel model= new ProveedorModel();
			int salida=model.elimnarProveedor(idSeleccionado);
			if(salida>0) {
				lista();
				idSeleccionado=-1;
				limpiarCajasTexto();
				mensaje("Se eliminó correctamente");
			}
			else {mensaje("Error en la eliminación");}
		}
		
	}
	private void actualiza() {
		String nom = txtNombre.getText();
		String ape = txtApellido.getText();
		String dni = txtDni.getText();
		String direccion=txtDireccion.getText();
		String telefono=txtTelefono.getText();
		String Correo=txtCorreo.getText();
		String Pais=cboPais.getSelectedItem().toString();
		
		if(idSeleccionado==-1) {mensaje("Seleccione una Fila");}
		else if (!nom.matches(Validaciones.TEXTO)) {	mensaje("El nombre es de 2 a 20 caracteres");
		}else if (!ape.matches(Validaciones.TEXTO)) {mensaje("El apellido es de 2 a 20 caracteres");
		}else if (!dni.matches(Validaciones.DNI)) {	mensaje("El nombre es de 8 digitos");	
		}else if(!direccion.matches(Validaciones.DIRECCION)) {mensaje("La dirección es de 2 a 40 caracteres");
		}else if(!telefono.matches(Validaciones.TELEFONO)) {mensaje("El teléfono debe ser de 9 dígitos");
		}else if(!Correo.matches(Validaciones.CORREO)) {mensaje("El correo debe incluir @ y .");}
		else if(cboPais.getSelectedIndex()==0) {mensaje("Seleccione un País");}
		
		else {
			Proveedor obj = new Proveedor();
			obj.setIdProveedor(idSeleccionado);
			obj.setNombre(nom);
			obj.setApellido(ape);
			obj.setDni(dni);
			obj.setDireccion(direccion);
			obj.setTelefono(telefono);
			obj.setCorreo(Correo);
			obj.setPais(Pais);
			
			
			ProveedorModel model = new ProveedorModel();
			int salida = model.actualizarProveedor(obj);
			if (salida > 0) {
				lista();
				idSeleccionado = -1;
				limpiarCajasTexto();
				mensaje("Se actualizó correctamente");
			}else {
				mensaje("Error en la actualización");
			}
		}	
	}
	private void busca() {
int fila = table.getSelectedRow();
		
		idSeleccionado = (Integer)table.getValueAt(fila, 0);
		String nombre =  (String)table.getValueAt(fila, 1);
		String apellido =  (String)table.getValueAt(fila, 2);
		String Dni=(String)table.getValueAt(fila, 3);
		String Direccion=(String)table.getValueAt(fila, 4);
		String Telefono=(String)table.getValueAt(fila, 5);
		String Correo=(String)table.getValueAt(fila, 6);
		String Pais=(String)table.getValueAt(fila, 7);
		
				
		txtNombre.setText(nombre);
		txtApellido.setText(apellido);
		txtDni.setText(Dni);
		txtDireccion.setText(Direccion);
		txtTelefono.setText(Telefono);
		txtCorreo.setText(Correo);
		cboPais.setSelectedItem(Pais);
		
		
	}
	
}
