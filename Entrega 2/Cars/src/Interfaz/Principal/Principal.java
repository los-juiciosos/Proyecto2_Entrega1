package Interfaz.Principal;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.EventListener;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import Interfaz.Admin_General.ConsolaAdminGeneral;
import Interfaz.Admin_General.MatrizFrecuencia;
import Interfaz.Admin_Local.ConsolaAdminLocal;
import Interfaz.Cliente.EscogerSede;
import Interfaz.Empleados.EmpleadoInventario;
import Interfaz.Empleados.EmpleadoMostrador;
import Interfaz.Login.Login;
import RentadoraModelo.CargaArchivos;
import RentadoraModelo.Sede;

public class Principal extends JFrame implements EventListener {
	
	public static String usernameActual;
	public static String idReservaActual;
	public static Object usuarioActual;
	public static Sede sedePresente;
	public CargaArchivos cargaArchivos;
	public String sedeActual;
	private Login login;
	private ConsolaAdminGeneral consolaAdminGeneral;
	private ConsolaAdminLocal consolaAdminLocal;
	private EmpleadoInventario empleadoInventario;
	private EmpleadoMostrador empleadoMostrador;
	private EscogerSede escogerSede;
	private MatrizFrecuencia matrizFrecuencia;
	
	public static final Color globalTheme = new Color(227, 36, 43); //El color base de la aplicacion 
	private CardLayout cardLayout;
	private JPanel mainPanel;
	
	public Principal() {
		
		//Diseño	
		setTitle("CUCHAU MOTORS");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(700, 700);
		
		setLayout(new BorderLayout());
		
		this.cargaArchivos = new CargaArchivos();
		
		//Paneles
		mainPanel = new JPanel();
		add(mainPanel, BorderLayout.CENTER);
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout); //para cambiar entre ventanas
		
		//PANELES (unicos paneles no cambiar!)
		this.escogerSede = new EscogerSede(this);
		this.login = new Login(this);
		this.consolaAdminLocal = new ConsolaAdminLocal(this);
		this.consolaAdminGeneral = new ConsolaAdminGeneral(this);
		this.empleadoInventario = new EmpleadoInventario(this);
		this.empleadoMostrador = new EmpleadoMostrador(this);
		this.matrizFrecuencia = new MatrizFrecuencia(this, sedeActual);
		
        BufferedImage originalImage; //LOGO DE CARS
		try {
			originalImage = ImageIO.read(new File("./imagenes/carsLogo.png"));
			Image image = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH) ;
			setIconImage(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		mainPanel.add(login,"login");
		mainPanel.add(escogerSede,"escogerSede");
		mainPanel.add(consolaAdminLocal,"consolaAdminLocal");
		mainPanel.add(consolaAdminGeneral,"consolaAdminGeneral");
		mainPanel.add(empleadoInventario,"empleadoInventario");
		mainPanel.add(empleadoMostrador,"empleadoMostrador");
		mainPanel.add(matrizFrecuencia,"matrizFrecuencia");
		
		centerWindow();
        pack();
        cardLayout.show(mainPanel,"login");
		setVisible(true);
				
	}
	
	public void cambiarPanel(String panel){
		cardLayout.show(mainPanel,panel);
	}
	
	public void addPanel(JPanel panel, String name) {
		mainPanel.add(panel,name);
	}
	
	public void centerWindow() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 3; //no es el centro pero se ve mas lindo

        setLocation(x, y);
	}
	public void setSedeActual(String nombre) {
		sedeActual = nombre;
	}
	public String getSedeActual() {
		return sedeActual;
	}
	public static void main(String[] args) {
		
		Principal principal = new Principal();
		
	}
	
}
