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

import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;


public class ConsolaAdminGeneral extends JPanel implements MetodosAuxiliares, ActionListener {

	private Principal principal;

	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	
	private ArrayList<JButton> listaBotones;
	
	private JTextField bajaVehiculo;
	private JTextField generarLog;
	
	
	private GridBagConstraints gbc;
	
	public ConsolaAdminGeneral(Principal principal) {
		
		this.listaBotones = new ArrayList<JButton>();
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
		
        JLabel info = new JLabel("Eliga la opción con la que desea trabajar");
		titleText(info);
        
		add(info, gbc);
		gbc.gridy++;
		
		addCamposBotones();
		
		bajaVehiculo = new JTextField(textFieldSize);
		ponerTextitoGris(bajaVehiculo, "Dar de baja vehículo Placa: ");
		add(bajaVehiculo, gbc);
		gbc.gridy++;
		
		
		generarLog = new JTextField(textFieldSize);
		ponerTextitoGris(generarLog, "Dar de baja vehículo Placa: ");
		add(generarLog, gbc);
		gbc.gridy++;
		
		
		setVisible(true);
		
        
	}
	
	
	
	private void addCamposBotones() {
		

		String[] campos = {"Registrar nuevo vehículo", "Modificar seguros", "Modificar información sede",
				"Revisar Gráfico por Sede", "Modificar Tarifa por Categoría", "Revisar Mantenimiento"};
		
		for (String mensaje : campos) {
			JButton campo = new JButton(mensaje);
			addSpace(YSpace);
			add(campo, gbc);
			gbc.gridy++;
			listaBotones.add(campo);
		}
				
	}
	

		
	
	
private void addCamposTexto() {

        
        String[] campos = {"Dar de baja vehículo Placa: ", "Generar Log de un Vehículo Placa: "};
        
        
        for (String mensaje : campos) {
			JTextField campo = new JTextField(textFieldSize);
			ponerTextitoGris(campo, mensaje);
			addSpace(YSpace);
			add(campo, gbc);
			gbc.gridy++;
			
		}
		
	}
	
	
	private void addSpace(int Yspace) {
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
		gbc.gridy++;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}