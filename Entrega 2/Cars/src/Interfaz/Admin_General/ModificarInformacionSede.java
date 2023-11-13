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
import Interfaz.Principal.Verify;
import RentadoraModelo.AdministradorGeneral;


public class ModificarInformacionSede extends JPanel implements MetodosAuxiliares, ActionListener {

	Principal principal;
	
	private GridBagConstraints gbc;
	
	static final int textFieldSize = 20;
	
	static final int YSpace = 20;
	
	private JTextField nuevoAspecto;
	private JComboBox<String> aspectos;
	private JComboBox<String> sedes;
	private JButton confirmar;
	private JButton logOut;
	private ErrorDisplay error;
	private Verify verificador;
	private Notificacion notify;
	
	public ModificarInformacionSede (Principal principal) {
		
		setLayout(new GridBagLayout());
		this.principal = principal;
		this.verificador = new Verify();
		this.gbc = new GridBagConstraints();
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        
        JLabel instruccion = new JLabel("Que sede desea modificar: ");
		subTitleText(instruccion);
		add(instruccion, gbc);
		gbc.gridy++;
		
		String[] listaSedes = {"RadiatorSpring", "CarburetorCanyon", "Emeryville", "MotorSpeedway"};
		sedes = new JComboBox<>(listaSedes);
		add(sedes, gbc);
		addSpace(YSpace);
		addSpace(YSpace);
		gbc.gridy++;
		
		JLabel aspect = new JLabel("Que aspecto desea modificar: ");
		subTitleText(aspect);
		add(aspect, gbc);
		gbc.gridy++;
		
		String[] listaAspectos = {"Direccion", "Hora Apertura", "Hora Cierre"};
		aspectos = new JComboBox<>(listaAspectos);
		add(aspectos, gbc);
		addSpace(YSpace);
		addSpace(YSpace);
		gbc.gridy++;
		
		nuevoAspecto = new JTextField(textFieldSize);
		ponerTextitoGris(nuevoAspecto, "Información para el aspecto a cambiar:");
		add(nuevoAspecto, gbc);
		addSpace(YSpace);
		addSpace(YSpace);
		gbc.gridy++;
		
		confirmar = new JButton("Confirmar");
        formatButton(confirmar);
        confirmar.setActionCommand("Confirmation");
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
			if (nuevoAspecto.getText().equals("Información para el aspecto a cambiar:")) {
				error = new ErrorDisplay("PORFAVOR LLENE EL CAMPO CORRECTAMENTE");
			}
			else {
				AdministradorGeneral admin = (AdministradorGeneral) principal.usuarioActual;
				admin.modificarInformacionSedes((String) sedes.getSelectedItem(), 
						(String) aspectos.getSelectedItem(),nuevoAspecto.getText());
				notify = new Notificacion("SE CAMBIO LA INFORMACION DE LA SEDE CORRECTAMENTE!!!!!!!");
				principal.cambiarPanel("consolaAdminGeneral");
			}
		}
		
		
	}
	
	

}