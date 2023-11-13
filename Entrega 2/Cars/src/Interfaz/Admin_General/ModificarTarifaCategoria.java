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


	
public class ModificarTarifaCategoria extends JPanel implements MetodosAuxiliares, ActionListener {
	Principal principal;	
	
	private GridBagConstraints gbc;
	
	static final int textFieldSize = 20;
	
	static final int YSpace = 20;
	
	private JTextField nuevaTarifa;
	
	private JButton confirmar;
	private JButton logOut;
	private ErrorDisplay error;
	private Notificacion notify;
	private JComboBox<String> seguros;
	
	
	public ModificarTarifaCategoria(Principal principal) {
		setLayout(new GridBagLayout());
		this.principal = principal;
		this.gbc = new GridBagConstraints();
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
		
        JLabel instruccion = new JLabel("Que categoria desea editar: ");
		subTitleText(instruccion);
		
		add(instruccion, gbc);
		
		gbc.gridy++;

		String[] listaSeguros = {"lujo", "clasico", "todoterreno", "van", "tractor", "suv"};
		seguros = new JComboBox<>(listaSeguros);
		add(seguros, gbc);
		addSpace(YSpace);
		gbc.gridy++;
		
		nuevaTarifa = new JTextField(textFieldSize);
		ponerTextitoGris(nuevaTarifa, "Ingrese la nueva tarifa");
		addSpace(YSpace);
        add(nuevaTarifa,gbc);
        addSpace(YSpace);
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
			if (nuevaTarifa.getText().equals("Ingrese la nueva tarifa")) {
				error = new ErrorDisplay("PORFAVOR LLENE EL CAMPO CORRECTAMENTE");
			}
			else {
				String seguro = (String) seguros.getSelectedItem();
				String nuevoPrecio = nuevaTarifa.getText();
				AdministradorGeneral admin = (AdministradorGeneral) principal.usuarioActual;
				admin.modificarTarifaCategoria(seguro, nuevoPrecio);
				notify = new Notificacion("CATEGORIA CAMBIADA SATISFACTORIAMENTE!!!!!!");
				principal.cambiarPanel("consolaAdminGeneral");
			}
		}
	}
}
