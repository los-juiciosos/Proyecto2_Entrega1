package Interfaz.Empleados;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        
        add(volver,gbc);
        
	}

	private void crearLabels() {
		
		reservaEspecialLabel = new JLabel("Crear Reserva Especial");
		subTitleText(reservaEspecialLabel);
		
		revisarVehiculoLabel = new JLabel("Mandar a Revisar vehiculo");
		subTitleText(revisarVehiculoLabel);
		
	}

	private void crearBotones() {
		
		revisarVehiculoButton = new JButton("aqui va una foto");
		revisarVehiculoButton.setActionCommand("revisarVehiculo");
		revisarVehiculoButton.addActionListener(this);
		formatButton(revisarVehiculoButton);
		
		reservaEspecialButton = new JButton("aqui va una foto");
		reservaEspecialButton.setActionCommand("reservaEspecial");
		reservaEspecialButton.addActionListener(this);
		formatButton(reservaEspecialButton);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String grito = e.getActionCommand();
		
		principal.cambiarPanel(grito);
		
	}

}
