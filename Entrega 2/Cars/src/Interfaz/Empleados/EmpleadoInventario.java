package Interfaz.Empleados;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Interfaz.Principal.ErrorDisplay;
import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Notificacion;
import Interfaz.Principal.Principal;
import RentadoraModelo.AdministradorGeneral;

public class EmpleadoInventario extends JPanel implements MetodosAuxiliares, ActionListener {
	
	Principal principal;
	
	private GridBagConstraints gbc;
	
	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	
	private JTextField nuevaTarifa;
	private JComboBox<String> seguros;
	private JButton consultar;
	private JButton confirmar;
	private JButton logOut;
	private JLabel titulo;
	private JLabel placa;
	
	private ErrorDisplay error;
	private Notificacion notify;
	
	public EmpleadoInventario(Principal principal) {
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
        
        JLabel instruccion = new JLabel("¿Poner en mantenimiento?");
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
			// #TODO: dale erick :v
			}
			
		}
		
	}
	
