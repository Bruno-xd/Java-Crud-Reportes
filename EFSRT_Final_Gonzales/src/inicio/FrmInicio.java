package inicio;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.FrmConsultaEmpleado;
import gui.FrmConsultaProducto;
import gui.FrmConsultaProveedor;
import gui.FrmCrudEmpleado;
import gui.FrmCrudProducto;
import gui.FrmCrudProveedor;
import gui.FrmRegistroEmpleado;
import gui.FrmRegistroProducto;
import gui.FrmRegistroProveedor;
import gui.FrmReporteEmpleado;
import gui.FrmReporteProducto;
import gui.FrmReporteProveedor;

public class FrmInicio extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuItem mntmEmplado;
	private JMenuItem mntmProducto;
	private JMenuItem mntmManEmpleado;
	private JMenuItem mntmManProducto;
	// -----------------------------------------------------------------------------------------------------------
	private FrmRegistroEmpleado frmEmpleado = new FrmRegistroEmpleado();
	private FrmRegistroProducto frmProducto = new FrmRegistroProducto();
	private FrmRegistroProveedor frmProveedor = new FrmRegistroProveedor();
	private FrmCrudEmpleado frmManEmpleado = new FrmCrudEmpleado();
	private FrmCrudProducto frmManProducto = new FrmCrudProducto();
	private FrmCrudProveedor frmManProveedor = new FrmCrudProveedor();
	private FrmConsultaEmpleado frmBusEmpleado = new FrmConsultaEmpleado();
	private FrmConsultaProducto frmBusProducto = new FrmConsultaProducto();
	private FrmConsultaProveedor frmBusProveedor = new FrmConsultaProveedor();
	private FrmReporteEmpleado frmRepEmpleado = new FrmReporteEmpleado();
	private FrmReporteProducto frmRepProducto = new FrmReporteProducto();
	private FrmReporteProveedor frmRepProveedor = new FrmReporteProveedor();

	
	private JMenuItem mntmProovedor;
	private JMenu mnBuscar;
	private JMenu mnReporte;
	private JMenuItem mntmManProveedor;
	private JMenuItem mntmBusEmpleado;
	private JMenuItem mntmBusProducto;
	private JMenuItem mntmBusProveedor;
	private JMenuItem mntmRepEmpleado;
	private JMenuItem mntmRepProducto;
	private JMenuItem mntmRepProveedor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmInicio frame = new FrmInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.as
	 */
	public FrmInicio() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Gesti\u00F3n de registros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 347);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAdministracion = new JMenu("Registrar");
		menuBar.add(mnAdministracion);

		mntmEmplado = new JMenuItem("Empleado");
		mntmEmplado.addActionListener(this);
		mnAdministracion.add(mntmEmplado);

		mntmProducto = new JMenuItem("Producto");
		mntmProducto.addActionListener(this);
		mnAdministracion.add(mntmProducto);

		mntmProovedor = new JMenuItem("Proovedor");
		mntmProovedor.addActionListener(this);
		mnAdministracion.add(mntmProovedor);

		JMenu mnMantenimiento = new JMenu("Mantenimiento");
		menuBar.add(mnMantenimiento);

		mntmManEmpleado = new JMenuItem("Empleado");
		mntmManEmpleado.addActionListener(this);
		mnMantenimiento.add(mntmManEmpleado);

		mntmManProducto = new JMenuItem("Producto");
		mntmManProducto.addActionListener(this);
		mnMantenimiento.add(mntmManProducto);

		mntmManProveedor = new JMenuItem("Proveedor");
		mntmManProveedor.addActionListener(this);
		mnMantenimiento.add(mntmManProveedor);

		mnBuscar = new JMenu("B\u00FAsqueda");
		menuBar.add(mnBuscar);

		mntmBusEmpleado = new JMenuItem("Empleado");
		mntmBusEmpleado.addActionListener(this);
		mnBuscar.add(mntmBusEmpleado);

		mntmBusProducto = new JMenuItem("Producto");
		mntmBusProducto.addActionListener(this);
		mnBuscar.add(mntmBusProducto);

		mntmBusProveedor = new JMenuItem("Proveedor");
		mntmBusProveedor.addActionListener(this);
		mnBuscar.add(mntmBusProveedor);

		mnReporte = new JMenu("Reporte");
		menuBar.add(mnReporte);

		mntmRepEmpleado = new JMenuItem("Empleado");
		mntmRepEmpleado.addActionListener(this);
		mnReporte.add(mntmRepEmpleado);

		mntmRepProducto = new JMenuItem("Producto");
		mntmRepProducto.addActionListener(this);
		mnReporte.add(mntmRepProducto);

		mntmRepProveedor = new JMenuItem("Proveedor");
		mntmRepProveedor.addActionListener(this);
		mnReporte.add(mntmRepProveedor);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		// ---------------------------------------------------------------------------------------------------------
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.activeCaption);

		desktopPane.add(frmEmpleado);
		desktopPane.add(frmProducto);
		desktopPane.add(frmProveedor);
		desktopPane.add(frmManEmpleado);
		desktopPane.add(frmManProducto);
		desktopPane.add(frmManProveedor);
		desktopPane.add(frmBusEmpleado);
		desktopPane.add(frmBusProducto);
		desktopPane.add(frmBusProveedor);
		desktopPane.add(frmRepEmpleado);
		desktopPane.add(frmRepProducto);
		desktopPane.add(frmRepProveedor);


		contentPane.add(desktopPane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmRepProveedor) {
			actionPerformedMntmRepProveedor(e);
		}
		if (e.getSource() == mntmRepProducto) {
			actionPerformedMntmRepProducto(e);
		}
		if (e.getSource() == mntmRepEmpleado) {
			actionPerformedMntmRepEmpleado(e);
		}
		if (e.getSource() == mntmBusProveedor) {
			actionPerformedMntmBusProveedor(e);
		}
		if (e.getSource() == mntmBusProducto) {
			actionPerformedMntmBusProducto(e);
		}
		if (e.getSource() == mntmBusEmpleado) {
			actionPerformedMntmBusEmpleado(e);
		}
		if (e.getSource() == mntmManProveedor) {
			actionPerformedMntmManProveedor(e);
		}
		if (e.getSource() == mntmProovedor) {
			actionPerformedMntmProovedor(e);
		}
		if (e.getSource() == mntmManProducto) {
			actionPerformedMntmManProducto(e);
		}
		if (e.getSource() == mntmManEmpleado) {
			actionPerformedMntmManEmpleado(e);
		}
		if (e.getSource() == mntmProducto) {
			actionPerformedMntmProducto(e);
		}
		if (e.getSource() == mntmEmplado) {
			actionPerformedMntmEmpleado(e);
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------
	protected void actionPerformedMntmEmpleado(ActionEvent e) {
		frmEmpleado.setVisible(true);
	}

	protected void actionPerformedMntmProducto(ActionEvent e) {
		frmProducto.setVisible(true);
	}
	
	protected void actionPerformedMntmProovedor(ActionEvent e) {
		frmProveedor.setVisible(true);
	}

	protected void actionPerformedMntmManEmpleado(ActionEvent e) {
		frmManEmpleado.setVisible(true);
	}

	protected void actionPerformedMntmManProducto(ActionEvent e) {
		frmManProducto.setVisible(true);
	}
	
	protected void actionPerformedMntmManProveedor(ActionEvent e) {
		frmManProveedor.setVisible(true);
	}
	
	protected void actionPerformedMntmBusEmpleado(ActionEvent e) {
		frmBusEmpleado.setVisible(true);
	}
	
	protected void actionPerformedMntmBusProducto(ActionEvent e) {
		frmBusProducto.setVisible(true);
	}
	
	protected void actionPerformedMntmBusProveedor(ActionEvent e) {
		frmBusProveedor.setVisible(true);
	}
	
	protected void actionPerformedMntmRepEmpleado(ActionEvent e) {
		frmRepEmpleado.setVisible(true);
	}
	
	protected void actionPerformedMntmRepProducto(ActionEvent e) {
		frmRepProducto.setVisible(true);

	}
	
	protected void actionPerformedMntmRepProveedor(ActionEvent e) {
		frmRepProveedor.setVisible(true);

	}
}
