package AplicacionClientes;

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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;
import RentadoraModelo.RentadoraCarros;

public class RevisarGrafico2 extends JPanel implements MetodosAuxiliares, ActionListener {
	
	AplicacionClientes AplicacionClientes;
	private PopUpGrafico2 popUpGrafico;
	
	private JButton confirmar;
	
	private GridBagConstraints gbc;
	
	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	
	public RevisarGrafico2(AplicacionClientes aplicacionClientes) {
		
		this.AplicacionClientes = aplicacionClientes;
		
		this.gbc = new GridBagConstraints();
		setLayout(new GridBagLayout());
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JLabel instruccion = new JLabel("Selecciona una sede:");
		subTitleText(instruccion);
		add(instruccion, gbc);
		addSpace(YSpace*2);
		
        crearBotones();
		
		confirmar = new JButton("Volver");
		formatButton(confirmar);
		confirmar.setActionCommand("consolaAdminGeneral");
		confirmar.addActionListener(this);
		addSpace(YSpace*3);
		add(confirmar, gbc);
		
	}
	
	private void crearBotones() {
		
		for (String sede : AplicacionClientes.cargaArchivos.cargarSedes()) {
			
			JButton button = new JButton(sede);
			
			formatButton(button);
			
			button.setActionCommand(sede);
			
			button.addActionListener(this);
			
			add(button, gbc);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String grito = e.getActionCommand();
		
		if (grito.equals("consolaAdminGeneral")) {
			AplicacionClientes.cambiarPanel(grito);
		} else {
			
			MatrizFrecuencia2.sedeActual = grito;
			
			popUpGrafico = new PopUpGrafico2(AplicacionClientes);
		}
		
	}
	
	private void addSpace(int Yspace) {
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
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
        Color color1 = lightOrange;
        Color color2 = Color.PINK;

        // Create a gradient paint
        GradientPaint gradientPaint = new GradientPaint(start, color1, end, color2);

        // Set the paint for the graphics context
        g2d.setPaint(gradientPaint);

        // Fill the background with the gradient paint
        g2d.fillRect(0, 0, width, height);
    }

}
