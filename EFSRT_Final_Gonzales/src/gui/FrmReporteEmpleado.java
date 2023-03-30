package gui;

import java.awt.BorderLayout;
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
import javax.swing.border.TitledBorder;

import entidad.Empleado;
import model.EmpleadoModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import util.GeneradorReporte;

public class FrmReporteEmpleado extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblReporteDeEmpleado;
	private JLabel lblNewLabel;
	private JTextField txtNombre;
	private JButton btnExportar;
	private JPanel panel;

	public FrmReporteEmpleado() {
		getContentPane().setForeground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte de empleado");
		setBounds(100, 100, 949, 572);
		getContentPane().setLayout(null);
		
		lblReporteDeEmpleado = new JLabel("Reporte de Empleado");
		lblReporteDeEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteDeEmpleado.setFont(new Font("Sylfaen", Font.BOLD, 24));
		lblReporteDeEmpleado.setBounds(21, 10, 886, 37);
		getContentPane().add(lblReporteDeEmpleado);
		
		lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(41, 92, 69, 14);
		getContentPane().add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(120, 89, 124, 20);
		getContentPane().add(txtNombre);
		
		btnExportar = new JButton("Exportar");
		btnExportar.addActionListener(this);
		btnExportar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExportar.setBounds(744, 84, 145, 31);
		getContentPane().add(btnExportar);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Reporte", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 133, 917, 400);
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

		EmpleadoModel model = new EmpleadoModel();
		List<Empleado> lstProducto = model.listaEmpleadoNombreLike(nom);

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstProducto);

		String reporte = "RepEmpleado.jasper";

		JasperPrint print = GeneradorReporte.genera(reporte, dataSource, null);

		JRViewer jasperViewer = new JRViewer(print);
		panel.removeAll();
		panel.add(jasperViewer);
		panel.repaint();
		panel.revalidate();
	}
}
