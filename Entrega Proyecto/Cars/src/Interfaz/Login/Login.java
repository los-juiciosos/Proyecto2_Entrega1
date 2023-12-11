package Interfaz.Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import AplicacionClientes.AplicacionClientes;
import Interfaz.Principal.ErrorDisplay;
import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;
import Interfaz.Principal.Verify;
import RentadoraModelo.Cliente;

public class Login extends JPanel implements MetodosAuxiliares, ActionListener {
	
	private Principal principal;
	private JLabel nombreEmpresa;
	private JLabel bienvenida;
	private JTextField usuario;
	private JTextField password;
	private JButton ingresar;
	private JButton nuevoUsuario;
	private Verify verifyLogin;
	
	public Login (Principal principal) {
		
		this.principal = principal;
		this.verifyLogin = new Verify();
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
		
		setBackground(Principal.globalTheme);
		
		setBorder(new EmptyBorder(40, 40, 40, 40)); //PARA PONER MARGENES
		
		nombreEmpresa = new JLabel("Cuchau Motors");
		titleText(nombreEmpresa);
		
		bienvenida = new JLabel("Ingresa tu usuario y contraseña");
		subTitleText(bienvenida);
		
		usuario = new JTextField(44);
		ponerTextitoGris(usuario,"Usuario");
		
		password = new JTextField(44);
		ponerTextitoGris(password,"Contraseña");
		
		ingresar = new JButton("Ingresar");
		formatButton(ingresar);
		ingresar.setActionCommand("INGRESAR");
		ingresar.addActionListener(this);
		
		nuevoUsuario = new JButton("Crear Usuario");
		formatButton(nuevoUsuario);
		nuevoUsuario.setActionCommand("NUEVO");
		nuevoUsuario.addActionListener(this);
		
		
		gbc.anchor = GridBagConstraints.CENTER;
		add(nombreEmpresa, gbc);
		
		addSpace(10);
		
		gbc.anchor = GridBagConstraints.WEST;
		add(bienvenida, gbc);
		
		addSpace(5);
		
		add(usuario, gbc);
		
		addSpace(5);
		
		add(password, gbc);
		
		addSpace(5);
		
		gbc.gridheight = GridBagConstraints.REMAINDER;
		gbc.gridwidth = gbc.gridy;
		
		add(ingresar, gbc);
		
		add(nuevoUsuario, gbc);
				
		requestFocus(null);	
	}
	
	private void addSpace(int Yspace) {
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String grito = e.getActionCommand();
		
		if (grito.equals("NUEVO")) {
			principal.cambiarPanel("crearCliente");
		} else if (grito.equals("INGRESAR")) {
			String user = usuario.getText();
			String contrasenia = password.getText();
			Properties pLogin = principal.cargaArchivos.cargarLogin();
			
			boolean verify = verifyLogin.checkLogin(pLogin, user, contrasenia);
			
			if (verify == true) {		
				String rol = verifyLogin.getRol(pLogin, user);
				principal.usernameActual = user;
				if (rol.equals("cliente")) {
					principal.usuarioActual = principal.cargaArchivos.cargarCliente(pLogin,user);
					principal.cambiarPanel("escogerSede");
				}
				else if (rol.equals("general")) {
					principal.usuarioActual = principal.cargaArchivos.cargarGeneral(pLogin, user);
					principal.cambiarPanel("consolaAdminGeneral");
				}
				else if (rol.equals("local")) {
					principal.usuarioActual = principal.cargaArchivos.cargarLocal(pLogin, user);
					principal.cambiarPanel("consolaAdminLocal");
				}
				else if (rol.equals("mostrador")) {
					principal.usuarioActual = principal.cargaArchivos.cargarMostrador(pLogin, user);
					principal.cambiarPanel("empleadoMostrador");
				}
				else if (rol.equals("inventario")) {
					principal.usuarioActual = principal.cargaArchivos.cargarEmpInventario(pLogin, user);
					principal.cambiarPanel("empleadoInventario");
				}
			}
			else {
			ponerTextitoGris(usuario,"Usuario");
			ponerTextitoGris(password,"Contraseña");
			ErrorDisplay error = new ErrorDisplay("USUARIO O CONSTRASEÑA INCORRECTA");
			}
		}
		
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        // Define the start and end points for the gradient
        Point2D start = new Point2D.Float(0, 0);
        Point2D end = new Point2D.Float(0, height);

        // Define the colors for the gradient
        Color color1 = redTheme;
//        Color color2 = Color.YELLOW;
//        Color color2 = cuteYellow;
        Color color2 = cutePurple;

        // Create a gradient paint
        GradientPaint gradientPaint = new GradientPaint(start, color1, end, color2);

        // Set the paint for the graphics context
        g2d.setPaint(gradientPaint);

        // Fill the background with the gradient paint
        g2d.fillRect(0, 0, width, height);
    }

	
}
