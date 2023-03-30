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

import entidad.Producto;
import model.ProductoModel;

public class FrmConsultaProducto extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblBsquedaDeProducto;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JButton btnFiltrar;
	private JScrollPane scrollPane;
	private JTable table;
	int hoveredRow = -1, hoveredColumn = -1;

	public FrmConsultaProducto() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Producto");
		setBounds(100, 100, 933, 462);
		getContentPane().setLayout(null);

		lblBsquedaDeProducto = new JLabel("B\u00FAsqueda de Producto");
		lblBsquedaDeProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblBsquedaDeProducto.setFont(new Font("Sylfaen", Font.BOLD, 24));
		lblBsquedaDeProducto.setBounds(10, 10, 886, 37);
		getContentPane().add(lblBsquedaDeProducto);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(28, 107, 69, 14);
		getContentPane().add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(107, 105, 149, 20);
		getContentPane().add(txtNombre);

		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFiltrar.setBounds(599, 98, 112, 37);
		getContentPane().add(btnFiltrar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 154, 905, 260);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nombre", "Fecha registro", "Stock", "Tipo" }));
		scrollPane.setViewportView(table);

		// alineación
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

		// tamano de la fila
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
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

		ProductoModel model = new ProductoModel();
		List<Producto> listaUsuario = model.listaProductoNombreLike(nombre);

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);

		Object[] fila = null;

		for (Producto x : listaUsuario) {
			fila = new Object[] { x.getIdProducto(), x.getNombre(), x.getFechaRegistro(), x.getStock(), x.getTipo() };

			dtm.addRow(fila);

		}
	}
}
