package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entidad.Producto;
import model.ProductoModel;
import util.Validaciones;

public class FrmRegistroProducto extends JInternalFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_5;
	private JComboBox<String> cboTipo;
	private JTextField txtNombre;
	private JTextField txtStock;
	private JButton btnRegistrar;
	private JLabel lblRegistroDeProducto;

	public FrmRegistroProducto() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Registro de Producto");
		setBounds(100, 100, 917, 465);
		getContentPane().setLayout(null);

		lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(271, 122, 154, 21);
		getContentPane().add(lblNewLabel);

		lblNewLabel_2 = new JLabel("N\u00FAmero de unidades:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(271, 208, 184, 18);
		getContentPane().add(lblNewLabel_2);

		lblNewLabel_5 = new JLabel("Tipo:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(271, 290, 154, 19);
		getContentPane().add(lblNewLabel_5);

		cboTipo = new JComboBox<String>();
		cboTipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboTipo.setModel(new DefaultComboBoxModel<String>(new String[] {"[Seleccione]", "Limpieza", "Mueble", "Tecnolog\u00EDa", "Comida"}));
		cboTipo.setBounds(465, 292, 96, 21);
		getContentPane().add(cboTipo);

		txtNombre = new JTextField();
		txtNombre.setBounds(465, 126, 192, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		txtStock = new JTextField();
		txtStock.addKeyListener(this);
		txtStock.setBounds(465, 207, 96, 19);
		getContentPane().add(txtStock);
		txtStock.setColumns(10);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRegistrar.setBounds(392, 346, 118, 29);
		getContentPane().add(btnRegistrar);
		
		lblRegistroDeProducto = new JLabel("Registro de Producto");
		lblRegistroDeProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDeProducto.setFont(new Font("Sylfaen", Font.BOLD, 24));
		lblRegistroDeProducto.setBounds(0, 38, 886, 37);
		getContentPane().add(lblRegistroDeProducto);
	}

	public void mensaje(String msje) {
		JOptionPane.showMessageDialog(this, msje);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrar(e);
		}
	}

	protected void actionPerformedBtnRegistrar(ActionEvent e) {
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
				mensaje("Se insertó correctamente");
			} else {
				mensaje("Error en el registro");
			}
		}
	}
	private void limpiar() {
		txtNombre.setText("");
		txtStock.setText("");
		cboTipo.setSelectedIndex(0);
		txtNombre.requestFocus();
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