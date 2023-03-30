package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

import entidad.Producto;
import model.ProductoModel;
import util.Validaciones;

public class FrmCrudProducto extends JInternalFrame implements ActionListener, MouseListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JTable tblSala;
	private JLabel lblNewLabel_1;
	private JTextField txtNombre;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_5;
	private JTextField txtStock;
	private JComboBox<String> cboTipo;
	private JButton btnRegistrar;
	private JButton btnEliminar;
	private JButton btnActualizar;

	int idSeleccionado = 1;
	int hoveredRow = -1, hoveredColumn = -1;
	private JLabel lblMantenimientoDeProducto;

	public FrmCrudProducto() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento de Producto");
		setBounds(100, 100, 900, 550);
		getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 292, 868, 219);
		getContentPane().add(scrollPane);

		tblSala = new JTable();
		tblSala.addMouseListener(this);
		tblSala.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Código","Nombre de producto","Stock", "Tipo" }));
		scrollPane.setViewportView(tblSala);

		lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(73, 75, 154, 13);
		getContentPane().add(lblNewLabel_1);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(237, 74, 96, 19);
		getContentPane().add(txtNombre);

		lblNewLabel_3 = new JLabel("N\u00FAmero de Unidades:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(73, 126, 154, 13);
		getContentPane().add(lblNewLabel_3);

		lblNewLabel_5 = new JLabel("Tipo:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(73, 181, 154, 13);
		getContentPane().add(lblNewLabel_5);

		txtStock = new JTextField();
		txtStock.addKeyListener(this);
		txtStock.setColumns(10);
		txtStock.setBounds(237, 125, 96, 19);
		getContentPane().add(txtStock);

		cboTipo = new JComboBox<String>();
		cboTipo.setModel(new DefaultComboBoxModel<String>(new String[] {"[Seleccione]", "Limpieza", "Mueble", "Tecnolog\u00EDa", "Comida"}));
		cboTipo.setBounds(237, 179, 96, 21);
		getContentPane().add(cboTipo);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(228, 245, 103, 28);
		getContentPane().add(btnRegistrar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(386, 245, 103, 28);
		getContentPane().add(btnEliminar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(535, 244, 128, 30);
		getContentPane().add(btnActualizar);

		// alineación
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		tblSala.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		tblSala.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		tblSala.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		tblSala.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

		// tamano de la fila
		tblSala.getColumnModel().getColumn(0).setPreferredWidth(15);
		tblSala.getColumnModel().getColumn(1).setPreferredWidth(120);
		tblSala.getColumnModel().getColumn(2).setPreferredWidth(120);
		tblSala.getColumnModel().getColumn(3).setPreferredWidth(80);
		tblSala.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		// selecciona una sola fila
		tblSala.setRowSelectionAllowed(true);
		tblSala.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// desabilita mover las columnas
		tblSala.getTableHeader().setReorderingAllowed(false);

		scrollPane.setViewportView(tblSala);

		// color de la fila seleccionada
		tblSala.setSelectionBackground(Color.yellow);
		
		lblMantenimientoDeProducto = new JLabel("Mantenimiento de Producto");
		lblMantenimientoDeProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoDeProducto.setFont(new Font("Sylfaen", Font.BOLD, 24));
		lblMantenimientoDeProducto.setBounds(10, 10, 868, 37);
		getContentPane().add(lblMantenimientoDeProducto);

		// No se pueda editar
		tblSala.setDefaultEditor(Object.class, null);

		// Efecto Rollover
		tblSala.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				Point p = e.getPoint();
				hoveredRow = tblSala.rowAtPoint(p);
				hoveredColumn = tblSala.columnAtPoint(p);
				tblSala.setRowSelectionInterval(hoveredRow, hoveredRow);
				tblSala.repaint();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				hoveredRow = hoveredColumn = -1;
				tblSala.repaint();
			}
		});

		listar();

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrar(e);
		}
	}

	protected void actionPerformedBtnRegistrar(ActionEvent e) {
		insertarDatos();
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		eliminarSala();
	}

	protected void actionPerformedBtnActualizar(ActionEvent e) {
		actualizarSala();
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblSala) {
			mouseClickedTblSala(e);
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

	protected void mouseClickedTblSala(MouseEvent e) {
		buscarFila();
	}

	private void listar() {
		ProductoModel model = new ProductoModel();
		List<Producto> listado = model.listaSala();

		DefaultTableModel dtm = (DefaultTableModel) tblSala.getModel();
		dtm.setRowCount(0);

		Object[] fila = null;
		for (Producto a : listado) {
			fila = new Object[] { a.getIdProducto(), a.getNombre(), a.getStock(), a.getTipo()}; 
			dtm.addRow(fila);

		}
	}

	private void insertarDatos() {
		String nom = txtNombre.getText();
		String stock = txtStock.getText();
		String tipo = cboTipo.getSelectedItem().toString();

		if (!nom.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("El numero de sala es de 3 a 30 caracteres");
		} else if (!stock.matches(Validaciones.NUMERO)) {
			mensaje("El número de alumnos debe ser mayor a 0");
		}  else if (cboTipo.getSelectedIndex() == 0) {
			mensaje("Seleccione una sede");
		} else {
			Producto obj = new Producto();
			obj.setNombre(nom);
			obj.setNumStock(Integer.parseInt(stock));
			obj.setTipo(tipo);

			ProductoModel model = new ProductoModel();
			int salida = model.insertaSala(obj);
			if (salida > 0) {
				limpiar();
				listar();
				idSeleccionado = -1;
				mensaje("Se insertó correctamente");
			} else {
				mensaje("Error en el registro");
			}
		}
	}

	private void actualizarSala() {
		String nom = txtNombre.getText();
		String stok = txtStock.getText();
		String tipo = cboTipo.getSelectedItem().toString();
		
		if (!nom.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("El numero de sala es de 3 a 30 caracteres");
		} else if (cboTipo.getSelectedIndex() == 0) {
			mensaje("Seleccionar un piso");
		} else if (!stok.matches(Validaciones.NUMERO)) {
			mensaje("El número de alumnos debe ser mayor a 0");
		}  else if (cboTipo.getSelectedIndex() == 0) {
			mensaje("Seleccione una sede");
		}else {
			Producto obj = new Producto();
			obj.setIdProducto(idSeleccionado);
			obj.setNombre(nom);
			obj.setNumStock(Integer.parseInt(stok));
			obj.setTipo(tipo);
			
			ProductoModel model = new ProductoModel();
			int salida = model.actualizaSala(obj);
			
			if (salida > 0) {
				listar();
				limpiar();
				idSeleccionado = -1;
				mensaje("Se actualizó correctamente");
			}else {
				mensaje("Error en la actualización");
			}
			
		}	
	}

	private void eliminarSala() {
		if(idSeleccionado == -1) {
			mensaje("Seleccione una Fila");
		}else {
			ProductoModel model = new ProductoModel();
			int salida = model.eliminaSala(idSeleccionado);
			
			if(salida>0) {
				listar();
				idSeleccionado = -1;
				limpiar();
				mensaje("Se eliminó correctamente");
			}else {
				mensaje("Error en la eliminación");
			}
		}
	}

	private void buscarFila() {
		int fila = tblSala.getSelectedRow();

		idSeleccionado = (Integer) tblSala.getValueAt(fila, 0);
		String nombre = (String) tblSala.getValueAt(fila, 1);
		int stock = (Integer) tblSala.getValueAt(fila, 2);
		String tipo = (String) tblSala.getValueAt(fila, 3);


		txtNombre.setText(nombre);
		txtStock.setText(String.valueOf(stock));
		cboTipo.setSelectedItem(tipo);
	}

	public void limpiar() {
		txtNombre.setText("");
		txtStock.setText("");
		cboTipo.setSelectedIndex(0);
		txtNombre.requestFocus();
	}

	public void mensaje(String msje) {
		JOptionPane.showMessageDialog(this, msje);
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtStock) {
			keyTypedTxtNumAlumn(e);
		}
	}
	protected void keyTypedTxtNumAlumn(KeyEvent e) {
		if (!Character.isDigit(e.getKeyChar())) {
			e.consume();
		}
	}
}
