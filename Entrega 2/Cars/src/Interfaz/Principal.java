package Interfaz;

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
import Interfaz.Admin_Local.ConsolaAdminLocal;
import Interfaz.Cliente.EscogerSede;
import Interfaz.Empleados.EmpleadoInventario;
import Interfaz.Empleados.EmpleadoMostrador;
import Modelo.Rentadora;


public class Principal extends JFrame implements EventListener {
	
	public Rentadora rentadora;
	
	private Login login;
	
	private ConsolaAdminGeneral consolaAdminGeneral;
	
	private ConsolaAdminLocal consolaAdminLocal;
	
	private EmpleadoInventario empleadoInventario;
	
	private EmpleadoMostrador empleadoMostrador;
	
	private EscogerSede escogerSede;
	
	public static final Color globalTheme = new Color(227, 36, 43); //El color base de la aplicacion 
	
	public Principal() {
		
		//MODELO
		this.rentadora = new Rentadora();
		
		//PANELES (unicos paneles no cambiar!)
		this.escogerSede = new EscogerSede(this);
		this.login = new Login(this);
		this.consolaAdminLocal = new ConsolaAdminLocal(this);
		this.consolaAdminGeneral = new ConsolaAdminGeneral(this);
		this.empleadoInventario = new EmpleadoInventario(this);
		this.empleadoMostrador = new EmpleadoMostrador(this);
		
		//Diseño	
		setTitle("Alquiler y Reservas del Rayo Mc Queen");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//setResizable(false);
		
		//setSize(400, 300);
		
		setLayout(new BorderLayout());
		
        BufferedImage originalImage; //LOGO DE CARS
		try {
			originalImage = ImageIO.read(new File("./imagenes/carsLogo.png"));
			Image image = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH) ;
			setIconImage(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Paneles
		
//		JPanel mainPanel = new JPanel();
//        
//        CardLayout cardLayout = new CardLayout();
//        mainPanel.setLayout(cardLayout); //para cambiar entre ventanas
//		
//		mainPanel.add(login,"login");
//		
        //cardLayout.show(mainPanel,"login");
        
		//add(mainPanel, BorderLayout.CENTER);
		
//		add(login);
		
		add(escogerSede);
		
		centerWindow();
		
        pack();
        
		setVisible(true);
		
//		login.requestFocus(null);
		
	}
	
	public void centerWindow() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 3; //no es el centro pero se ve mas lindo

        setLocation(x, y);
	}
	
	public static void main(String[] args) {
		
		Principal principal = new Principal();
		
	}
	
}
