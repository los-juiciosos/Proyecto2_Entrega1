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
	
	static final int YSpace = 20;
	
	private JTextField nuevaTarifa;
	private JComboBox<String> seguros;
	private JButton consultar;
	private JButton confirmar;
	private JButton logOut;
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
        add(placa);
        gbc.gridy++;
        
        consultar = new JButton("Consultar");
        formatButton(consultar);
        consultar.setActionCommand("Consultar");
        consultar.addActionListener(this);
        add(consultar,gbc);
        gbc.gridy++;
        
        
		nuevaTarifa = new JTextField(textFieldSize);
		nuevaTarifa.setEditable(false);
        add(nuevaTarifa,gbc);
        addSpace(YSpace);
        addSpace(YSpace);
        gbc.gridy++;
        
        JLabel instruccion = new JLabel("¿Dar de baja?");
		subTitleText(instruccion);
		
		add(instruccion, gbc);
		gbc.gridy++;

		String[] listaSeguros = {"Si", "No"};
		seguros = new JComboBox<>(listaSeguros);
		add(seguros, gbc);
		addSpace(YSpace);
		gbc.gridy++;
		
	
		confirmar = new JButton("Confirmar");
        formatButton(confirmar);
        confirmar.setActionCommand("confirmation");
        confirmar.addActionListener(this);
        add(confirmar,gbc);
        gbc.gridy++;
        
        logOut = new JButton("LOG OUT");
        formatButton(logOut);
        logOut.setActionCommand("login");
        logOut.addActionListener(this);
        add(logOut,gbc);
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
		
		if (grito.equals("login")) {
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
		
	}
	
