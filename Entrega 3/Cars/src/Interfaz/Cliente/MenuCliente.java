package Interfaz.Cliente;

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
import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;

public class MenuCliente extends JPanel implements MetodosAuxiliares, ActionListener {

	Principal principal;
	private GridBagConstraints gbc;
	private JButton iniciarReserva;
	private JButton formalizarAlquiler;
	private JButton entregarVehiculo;
	private JButton retroceder;
	static final int YSpace = 3;
	
	public MenuCliente(Principal principal) {
		
		this.principal = principal;
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        
        
        JLabel instruccion = new JLabel("Escoge una opcion");
		titleText(instruccion);
        
        iniciarReserva = new JButton("Iniciar Reserva");
        formatButton(iniciarReserva);
        iniciarReserva.setActionCommand("hacerReserva");
		iniciarReserva.addActionListener(this);
        
        formalizarAlquiler = new JButton("Formalizar Alquiler");
        formatButton(formalizarAlquiler);
        formalizarAlquiler.setActionCommand("formalizarAlquiler");
        formalizarAlquiler.addActionListener(this);
        
        entregarVehiculo = new JButton("Entregar un Vehiculo");
        formatButton(entregarVehiculo);
        entregarVehiculo.setActionCommand("entregarVehiculo");
		entregarVehiculo.addActionListener(this);
        
		retroceder = new JButton("Retroceder");
		formatButton(retroceder);
		retroceder.setActionCommand("escogerSede");
		retroceder.addActionListener(this);
        
        add(instruccion,gbc);
		gbc.gridy++;
		addSpace(YSpace);
		
		add(iniciarReserva, gbc);
        gbc.gridy++;
        addSpace(YSpace);
        
        add(formalizarAlquiler, gbc);
        gbc.gridy++;
        addSpace(YSpace);
        
        add(entregarVehiculo, gbc);
        gbc.gridy++;
        addSpace(YSpace);
        
        add(retroceder, gbc);
        gbc.gridy++;
        addSpace(YSpace);
		
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
        Color color1 = new Color(255, 165, 0);
        Color color2 = Color.PINK;

        // Create a gradient paint
        GradientPaint gradientPaint = new GradientPaint(start, color1, end, color2);

        // Set the paint for the graphics context
        g2d.setPaint(gradientPaint);

        // Fill the background with the gradient paint
        g2d.fillRect(0, 0, width, height);
    }
}
