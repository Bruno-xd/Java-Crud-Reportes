package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.JButton;
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

import entidad.Empleado;
import model.EmpleadoModel;

public class FrmConsultaEmpleado extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblBsquedaDeEmpleado;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JButton btnFiltrar;
	private JScrollPane scrollPane;
	private JTable table;
	int hoveredRow = -1, hoveredColumn = -1;

	public FrmConsultaEmpleado() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Empleado");
		setBounds(100, 100, 917, 450);
		getContentPane().setLayout(null);

		lblBsquedaDeEmpleado = new JLabel("B\u00FAsqueda de Empleado");
		lblBsquedaDeEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		lblBsquedaDeEmpleado.setFont(new Font("Sylfaen", Font.BOLD, 24));
		lblBsquedaDeEmpleado.setBounds(10, 10, 886, 37);
		getContentPane().add(lblBsquedaDeEmpleado);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(45, 101, 69, 14);
		getContentPane().add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(124, 99, 149, 20);
		getContentPane().add(txtNombre);

		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFiltrar.setBounds(616, 92, 112, 37);
		getContentPane().add(btnFiltrar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 146, 885, 256);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nombres", "Apellidos", "DNI", "Pa\u00ECs", "Correo", "Fecha nacimiento", "Fecha registro" }));
		scrollPane.setViewportView(table);

		// alineación
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


		// tamano de la fila
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(110);
		table.getColumnModel().getColumn(6).setPreferredWidth(80);
		table.getColumnModel().getColumn(7).setPreferredWidth(80);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		// selecciona una sola fila
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// desabilita mover las columnas
		table.getTableHeader().setReorderingAllowed(false);

		scrollPane.setViewportView(table);

		// color de la fila seleccionada
		table.setSelectionBackground(Color.yellow);

		// No se pueda editar
		table.setDefaultEditor(Object.class, null);

		// Efecto Rollover
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
	}

	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFiltrar) {
			actionPerformedBtnFiltrar(e);
		}
	}

	protected void actionPerformedBtnFiltrar(ActionEvent e) {
		String nombre = txtNombre.getText();

		EmpleadoModel model = new EmpleadoModel();
		List<Empleado> listaUsuario = model.listaEmpleadoNombreLike(nombre);

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);

		Object[] fila = null;

		for (Empleado x : listaUsuario) {
			fila = new Object[] { x.getIdAlumno(), x.getNombre(), x.getApellido(), x.getDni(), x.getPais(),
					x.getCorreo(), x.getFechanac(), x.getFechareg() };

			dtm.addRow(fila);

		}
	}
}
