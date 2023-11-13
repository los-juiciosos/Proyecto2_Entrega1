package Interfaz.Admin_Local;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;

public class ModificarTrabajador extends JPanel implements MetodosAuxiliares, ActionListener {
	
	Principal principal;
	
	private GridBagConstraints gbc;
	
	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	
	public ModificarTrabajador(Principal principal) {
		
		this.principal = principal;
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        
        JLabel trabajadorLabel = new JLabel("Trabajador a modificar");
		subTitleText(trabajadorLabel);
		
		JComboBox<String> trabajador = new JComboBox<>();
		
		JTextField nuevoValor = new JTextField(textFieldSize);
		ponerTextitoGris(nuevoValor, "Nuevo Valor");
		
		JButton confirmar = new JButton("Confirmar");
		confirmar.setActionCommand("consolaAdminLocal");
		confirmar.addActionListener(this);
		
		add(trabajadorLabel,gbc);
		
		add(trabajador,gbc);
		
		add(nuevoValor,gbc);
		
		add(confirmar,gbc);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String grito = e.getActionCommand();
		
		principal.cambiarPanel(grito);
		
	}
	
	
}
