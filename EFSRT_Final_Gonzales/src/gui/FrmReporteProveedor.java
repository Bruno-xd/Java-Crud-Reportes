package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import entidad.Proveedor;
import model.ProveedorModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import util.GeneradorReporte;

public class FrmReporteProveedor extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel_1;
	private JTextField txtNombre;
	private JLabel lblNewLabel_2;
	private JTextField txtDireccion;
	private JLabel lblNewLabel_3;
	private JTextField txtFecDesde;
	private JLabel lblNewLabel_4;
	private JTextField txtApellido;
	private JLabel lblNewLabel_5;
	private JTextField txtTelefono;
	private JLabel lblNewLabel_6;
	private JTextField txtFecHasta;
	private JLabel lblNewLabel_7;
	private JTextField txtDni;
	private JComboBox<String> cboPais;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_8;
	private JTextField txtCorreo;
	private JPanel panelReporte;
	private JButton btnExportar;
	private JLabel lblReporteDeProveedor;

	public FrmReporteProveedor() {
		getContentPane().setBackground(SystemColor.menu);
		getContentPane().setForeground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte de Proveedor");
		setBounds(100, 100, 956, 633);
		getContentPane().setLayout(null);
		
		lblNewLabel_1 = new JLabel("Nombres");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(84, 107, 69, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(163, 104, 124, 20);
		getContentPane().add(txtNombre);
		
		lblNewLabel_2 = new JLabel("Direcci\u00F3n");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(313, 107, 69, 14);
		getContentPane().add(lblNewLabel_2);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(379, 104, 132, 20);
		getContentPane().add(txtDireccion);
		
		lblNewLabel_3 = new JLabel("Fecha de Inicio");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(521, 107, 110, 14);
		getContentPane().add(lblNewLabel_3);
		
		txtFecDesde = new JTextField();
		txtFecDesde.setColumns(10);
		txtFecDesde.setBounds(629, 104, 124, 20);
		getContentPane().add(txtFecDesde);
		
		lblNewLabel_4 = new JLabel("Apellidos");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(84, 129, 69, 14);
		getContentPane().add(lblNewLabel_4);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(163, 129, 124, 20);
		getContentPane().add(txtApellido);
		
		lblNewLabel_5 = new JLabel("Tel\u00E9fono");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(313, 132, 59, 14);
		getContentPane().add(lblNewLabel_5);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(379, 129, 132, 20);
		getContentPane().add(txtTelefono);
		
		lblNewLabel_6 = new JLabel("Fecha de Fin");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(521, 135, 110, 14);
		getContentPane().add(lblNewLabel_6);
		
		txtFecHasta = new JTextField();
		txtFecHasta.setColumns(10);
		txtFecHasta.setBounds(629, 132, 124, 20);
		getContentPane().add(txtFecHasta);
		
		lblNewLabel_7 = new JLabel("DNI");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(84, 157, 46, 14);
		getContentPane().add(lblNewLabel_7);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(163, 157, 86, 20);
		getContentPane().add(txtDni);
		
		cboPais = new JComboBox<String>();
		cboPais.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Per\u00FA", "Chile", "Colombia", "Brasil"}));
		cboPais.setBounds(379, 153, 132, 22);
		getContentPane().add(cboPais);
		
		lblNewLabel = new JLabel("Pa\u00EDs");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(313, 157, 46, 14);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_8 = new JLabel("Correo");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(521, 160, 110, 14);
		getContentPane().add(lblNewLabel_8);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(629, 157, 86, 20);
		getContentPane().add(txtCorreo);
		
		panelReporte = new JPanel();
		panelReporte.setBorder(new TitledBorder(null, "Reporte", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelReporte.setBackground(SystemColor.menu);
		panelReporte.setBounds(10, 219, 932, 375);
		getContentPane().add(panelReporte);
		panelReporte.setLayout(new BorderLayout(0, 0));
		
		btnExportar = new JButton("Exportar");
		btnExportar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExportar.addActionListener(this);
		btnExportar.setBounds(791, 122, 145, 31);
		getContentPane().add(btnExportar);
		
		lblReporteDeProveedor = new JLabel("Reporte de Proveedor");
		lblReporteDeProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteDeProveedor.setFont(new Font("Sylfaen", Font.BOLD, 24));
		lblReporteDeProveedor.setBounds(10, 10, 886, 37);
		getContentPane().add(lblReporteDeProveedor);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnExportar) {
			actionPerformedBtnNewButton(e);
		}
	}
	protected void actionPerformedBtnNewButton(ActionEvent e) {
		String nom=txtNombre.getText();
		String ape=txtApellido.getText();
		String dni=txtDni.getText();
		String dir=txtDireccion.getText();
		String tel=txtTelefono.getText();
		String cor=txtCorreo.getText();
		String pais=cboPais.getSelectedItem().toString();
		String fecIni=txtFecDesde.getText();
		String fecFin=txtFecHasta.getText();
		
		ProveedorModel model = new ProveedorModel();
		List<Proveedor> lstProveedor = model.consultaValores(nom, ape, dni, dir, tel, cor, pais, fecIni, fecFin);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstProveedor);
		
		String reporte ="Proveedor.jasper";
		
		JasperPrint print = GeneradorReporte.genera(reporte, dataSource,null);
		
		JRViewer jasperViewer = new JRViewer(print);
		panelReporte.removeAll();
		panelReporte.add(jasperViewer);
		panelReporte.repaint();
		panelReporte.revalidate();
	}
}
