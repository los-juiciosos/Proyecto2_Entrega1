package Interfaz.Empleados;

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

import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;

public class EmpleadoMostradorIn extends JPanel implements MetodosAuxiliares, ActionListener {
	
	Principal principal;
	
	private GridBagConstraints gbc;
	private RevisarVehiculo revisarVehiculo;
	private ReservaEspecial reservaEspecial; 
	private JButton reservaEspecialButton;
	private JLabel reservaEspecialLabel;
	private JButton revisarVehiculoButton;
	private JLabel revisarVehiculoLabel;
	private JButton confirmar;
	private JButton volver;
	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	
	public EmpleadoMostradorIn(Principal principal) {
		
		this.principal = principal;
		
		this.reservaEspecial = new ReservaEspecial(principal);
		this.revisarVehiculo = new RevisarVehiculo(principal);
		
		principal.addPanel(revisarVehiculo, "revisarVehiculo" );
		principal.addPanel(reservaEspecial, "reservaEspecial" );
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        volver = new JButton("LOGOUT");
		volver.setActionCommand("login");
		volver.addActionListener(this);
		formatGradientButton(volver, pastelPurple, cuteYellow);
        
        crearBotones();
        
        crearLabels();
        
        add(reservaEspecialButton, gbc);
        gbc.gridx = 1;
        
        add(reservaEspecialLabel,gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        
        add(revisarVehiculoButton, gbc);
        gbc.gridx = 1;
        
        add(revisarVehiculoLabel,gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        
        addSpace(YSpace*4);
        
        gbc.gridwidth = 2;
        add(volver,gbc);
        
	}

	private void crearLabels() {
		
		reservaEspecialLabel = new JLabel("  Crear Reserva Especial");
		subTitleText(reservaEspecialLabel);
		
		revisarVehiculoLabel = new JLabel("  Mandar a Revisar vehiculo");
		subTitleText(revisarVehiculoLabel);
		
	}

	private void crearBotones() {
		
		revisarVehiculoButton = new JButton("→");
		revisarVehiculoButton.setActionCommand("revisarVehiculo");
		revisarVehiculoButton.addActionListener(this);
		formatLeftArrowButton(revisarVehiculoButton, pastelPurple);
		
		reservaEspecialButton = new JButton("→");
		reservaEspecialButton.setActionCommand("reservaEspecial");
		reservaEspecialButton.addActionListener(this);
		formatLeftArrowButton(reservaEspecialButton, pastelPurple);
		
	}
	
	private void addSpace(int Yspace) {
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
		gbc.gridy++;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String grito = e.getActionCommand();
		
		principal.cambiarPanel(grito);
		
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
//        Color color1 = new Color(255, 165, 0);
        Color color1 = cuteYellow;
        Color color2 = Color.PINK;

        // Create a gradient paint
        GradientPaint gradientPaint = new GradientPaint(start, color1, end, color2);

        // Set the paint for the graphics context
        g2d.setPaint(gradientPaint);

        // Fill the background with the gradient paint
        g2d.fillRect(0, 0, width, height);
    }

}
