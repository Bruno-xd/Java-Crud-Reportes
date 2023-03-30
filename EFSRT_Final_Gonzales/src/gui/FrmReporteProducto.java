package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import entidad.Producto;
import model.ProductoModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import util.GeneradorReporte;
import java.awt.BorderLayout;

public class FrmReporteProducto extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblReporteDeProducto;
	private JLabel lblNewLabel;
	private JTextField txtNombre;
	private JButton btnExportar;
	private JPanel panel;

	public FrmReporteProducto() {
		getContentPane().setForeground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte de Producto");
		setBounds(100, 100, 926, 583);
		getContentPane().setLayout(null);

		lblReporteDeProducto = new JLabel("Reporte de Producto");
		lblReporteDeProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteDeProducto.setFont(new Font("Sylfaen", Font.BOLD, 24));
		lblReporteDeProducto.setBounds(10, 10, 886, 37);
		getContentPane().add(lblReporteDeProducto);

		lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(29, 95, 69, 14);
		getContentPane().add(lblNewLabel);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(108, 92, 124, 20);
		getContentPane().add(txtNombre);

		btnExportar = new JButton("Exportar");
		btnExportar.addActionListener(this);
		btnExportar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExportar.setBounds(732, 87, 145, 31);
		getContentPane().add(btnExportar);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Reporte",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 145, 894, 399);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnExportar) {
			actionPerformedBtnExportar(e);
		}
	}

	protected void actionPerformedBtnExportar(ActionEvent e) {
		String nom = txtNombre.getText();

		ProductoModel model = new ProductoModel();
		List<Producto> lstProducto = model.listaProductoNombreLike(nom);

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstProducto);

		String reporte = "Repprod.jasper";

		JasperPrint print = GeneradorReporte.genera(reporte, dataSource, null);

		JRViewer jasperViewer = new JRViewer(print);
		panel.removeAll();
		panel.add(jasperViewer);
		panel.repaint();
		panel.revalidate();
	}
}
