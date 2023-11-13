package Interfaz.Empleados;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;

public class EmpleadoInventario extends JPanel implements MetodosAuxiliares, ActionListener {
	
	Principal principal;
	
	private GridBagConstraints gbc;
	
	private JButton confirmar;
	
	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	
	public EmpleadoInventario(Principal principal) {
		
		this.principal = principal;
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        
        JLabel instruccion = new JLabel("Ingrese la placa del vehiculo a revisar");
		subTitleText(instruccion);
		
		JTextField campo = new JTextField(textFieldSize);
        ponerTextitoGris(campo, "Placa");
        
        confirmar = new JButton("Confirmar");
		confirmar.setActionCommand("login");
		confirmar.addActionListener(this);
		
		add(instruccion, gbc);
		
		add(campo, gbc);
		
		add(confirmar, gbc);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String grito = e.getActionCommand();
		
		principal.cambiarPanel(grito);
		
	}
}
