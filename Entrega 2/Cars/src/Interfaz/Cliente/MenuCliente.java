package Interfaz.Cliente;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
}
