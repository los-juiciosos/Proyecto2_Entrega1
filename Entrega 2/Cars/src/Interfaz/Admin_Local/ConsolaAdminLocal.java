package Interfaz.Admin_Local;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Interfaz.Login.CrearCliente;
import Interfaz.Login.PopUpCrearCliente;
import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;

public class ConsolaAdminLocal extends JPanel implements MetodosAuxiliares, ActionListener {
	
	Principal principal;
	
	private GridBagConstraints gbc;
	
	private PopUpCrearCliente popUpCrearCliente;
	
	private RegistrarTrabajador registrarTrabajador;
	
	private  ModificarTrabajador modificarTrabajador;
	
	private JButton crearClienteButton;
	
	private JButton modificarTrabajadorButton;
	
	private JButton registrarTrabajadorButton;
	
	private JLabel crearClienteLabel;
	
	private JLabel registrarTrabajadorLabel;
	
	private JLabel modificarTrabajadorLabel;
	
	private JButton volver;
	
	static final int YSpace = 5;
	
	public ConsolaAdminLocal(Principal principal) {
		
		this.principal = principal;
		
		this.registrarTrabajador = new RegistrarTrabajador(principal);
		this.modificarTrabajador = new ModificarTrabajador(principal);
		
		principal.addPanel(registrarTrabajador, "registrarTrabajador" );
		principal.addPanel(modificarTrabajador, "modificarTrabajador" );
		
		crearLabels();
		
		crearBotones();
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        
        volver = new JButton("LOGOUT");
		volver.setActionCommand("login");
		volver.addActionListener(this);
		formatButton(volver);
		
         add(crearClienteButton,gbc);
         gbc.gridx = 1;
         
         add(crearClienteLabel, gbc);
         gbc.gridy++;
         gbc.gridx = 0;
         
         add(registrarTrabajadorButton,gbc);
         gbc.gridx = 1;
         
         add(registrarTrabajadorLabel, gbc);
         gbc.gridy++;
         gbc.gridx = 0;
         
         add(modificarTrabajadorButton,gbc);
         gbc.gridx = 1;
         
         add(modificarTrabajadorLabel, gbc);
         gbc.gridy++;
         gbc.gridx = 0;
         
         addSpaceY(YSpace*3);
         
         gbc.gridwidth = 2;
         add(volver, gbc);
		
	}

	private void crearLabels() {
		
		crearClienteLabel = new JLabel("  Crear Cliente");
		subTitleText(crearClienteLabel);
		
		modificarTrabajadorLabel = new JLabel("  Modificar Trabajador");
		subTitleText(modificarTrabajadorLabel);
		
		registrarTrabajadorLabel = new JLabel("  Registrar Trabajador");
		subTitleText(registrarTrabajadorLabel);
		
	}

	private void crearBotones() {
		
		crearClienteButton = new JButton("→");
		formatLeftArrowButton(crearClienteButton);
		crearClienteButton.setActionCommand("crearCliente");
		crearClienteButton.addActionListener(this);
		
		modificarTrabajadorButton = new JButton("→");
		formatLeftArrowButton(modificarTrabajadorButton);
		modificarTrabajadorButton.setActionCommand("modificarTrabajador");
		modificarTrabajadorButton.addActionListener(this);
		
		registrarTrabajadorButton = new JButton("→");
		formatLeftArrowButton(registrarTrabajadorButton);
		registrarTrabajadorButton.setActionCommand("registrarTrabajador");
		registrarTrabajadorButton.addActionListener(this);
		
	}
	
	private void addSpaceY(int Yspace) {
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
		gbc.gridy++;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String grito = e.getActionCommand();
		
		if (grito.equals("crearCliente")) {
			this.popUpCrearCliente = new PopUpCrearCliente(principal);
		}else {
			principal.cambiarPanel(grito);
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
