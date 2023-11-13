package Interfaz.Empleados;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;

public class RevisarVehiculo extends JPanel implements MetodosAuxiliares, ActionListener {

	Principal principal;
	
	private GridBagConstraints gbc;
	
	private JButton confirmar;
	
	private JTextField placa;
	
	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	
	public RevisarVehiculo(Principal principal) {
		
		this.principal = principal;
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        
        JLabel instruccion = new JLabel("Mandar a revisar el Auto con placa:");
		subTitleText(instruccion);
		
		placa = new JTextField(textFieldSize);
		ponerTextitoGris(placa,"Placa");
		
		confirmar = new JButton("Confirmar");
		confirmar.setActionCommand("empleadoMostrador");
		confirmar.addActionListener(this);
		
		add(instruccion, gbc);
		
		add(placa, gbc);
		
		add(confirmar, gbc);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String grito = e.getActionCommand();
		
		principal.cambiarPanel(grito);
		
	}
	
	private void addSpace(int Yspace) {
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
	}

}
