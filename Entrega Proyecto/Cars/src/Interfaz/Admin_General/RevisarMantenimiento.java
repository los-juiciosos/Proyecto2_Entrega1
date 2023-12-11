package Interfaz.Admin_General;


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
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import Interfaz.Principal.ErrorDisplay;
import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Notificacion;
import Interfaz.Principal.Principal;
import RentadoraModelo.AdministradorGeneral;

public class RevisarMantenimiento extends JPanel implements MetodosAuxiliares, ActionListener {
	
	Principal principal;	
	
	private GridBagConstraints gbc;
	
	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	
	private JTextField nuevaTarifa;
	private JComboBox<String> seguros;
	private JButton consultar;
	private JButton confirmar;
	private JButton volver;
	private JLabel titulo;
	private JLabel placa;
	
	private ErrorDisplay error;
	private Notificacion notify;
	
	public RevisarMantenimiento(Principal principal) {
		setLayout(new GridBagLayout());
		this.principal = principal;
		this.gbc = new GridBagConstraints();
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        
        
        placa = new JLabel("Placa del vehículo: ");
        subTitleText(placa);
        add(placa, gbc);
        gbc.gridy++;
        addSpace(YSpace);
        
        consultar = new JButton("Consultar");
        formatButton(consultar);
        consultar.setActionCommand("Consultar");
        consultar.addActionListener(this);
        add(consultar,gbc);
        gbc.gridy++;
        
        
		nuevaTarifa = new JTextField(textFieldSize);
		nuevaTarifa.setEditable(false);
        add(nuevaTarifa,gbc);
        gbc.gridy++;
        addSpace(YSpace*2);
        
        JLabel instruccion = new JLabel("¿Dar de baja?");
		subTitleText(instruccion);
		
		add(instruccion, gbc);
		gbc.gridy++;
		addSpace(YSpace);

		String[] listaSeguros = {"Si", "No"};
		seguros = new JComboBox<>(listaSeguros);
		add(seguros, gbc);
		gbc.gridy++;
		addSpace(YSpace*2);
		
	
		confirmar = new JButton("Confirmar");
        formatButton(confirmar);
        confirmar.setActionCommand("confirmation");
        confirmar.addActionListener(this);
        add(confirmar,gbc);
        gbc.gridy++;
        
        volver = new JButton("Volver");
        formatButton(volver);
        volver.setActionCommand("consolaAdminGeneral");
        volver.addActionListener(this);
        add(volver,gbc);
        addSpace(YSpace);
        gbc.gridy++;
        
		
		setVisible(true);
		
        
	}

	private void addSpace(int Yspace) {
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
		gbc.gridy++;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String grito = e.getActionCommand();
		
		if (grito.equals("consolaAdminGeneral")) {
			principal.cambiarPanel(grito);
		}
		else {
			if (nuevaTarifa.getText().equals("Ingrese la placa del carro a revisar")) {
				error = new ErrorDisplay("PORFAVOR LLENE TODOS LOS CAMPOS");
			}
			else if (grito.equals("Consultar")){
				AdministradorGeneral admin = (AdministradorGeneral) principal.usuarioActual;
				String placaRevisar = admin.obtenerPlacaMantenimiento();
				if (placaRevisar == null) {
					nuevaTarifa.setText("NO HAY POR AHORA");
				}
				else {
					nuevaTarifa.setText("CARRO PLACA: "+ placaRevisar);
				}
			}
			else if (grito.equals("confirmation")) {
				AdministradorGeneral admin = (AdministradorGeneral) principal.usuarioActual;
				String placaRevisar = admin.obtenerPlacaMantenimiento();
				String seleccion = (String) seguros.getSelectedItem();
				if (seleccion.equals("Si")) {
					admin.darBajaVehiculo(placaRevisar);
					admin.eliminarMantenimiento(placaRevisar);
					notify = new Notificacion("SE DIÓ DE BAJA EL VEHICULO SATISFACOTRIAMENTE");
				}
				else {
					admin.actualizarEstadoVehículo(placaRevisar);
					admin.eliminarMantenimiento(placaRevisar);
					notify = new Notificacion("NO SE DIO DE BAJA Y ESTA LISTO PARA ANDAR");
						
					}
				nuevaTarifa.setText("");
				principal.cambiarPanel("consolaAdminGeneral");
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
	
