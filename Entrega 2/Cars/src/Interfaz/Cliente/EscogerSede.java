package Interfaz.Cliente;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Interfaz.MetodosAuxiliares;
import Interfaz.Principal;

public class EscogerSede extends JPanel implements MetodosAuxiliares, ActionListener {
	
	Principal principal;
	
	public EscogerSede(Principal principal) {
		
		this.principal = principal;
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
		
		//setBackground(Principal.globalTheme);
		
		setSize(500, 500);
		
		setBorder(new EmptyBorder(40, 40, 40, 40)); //PARA PONER MARGENES
		
		addBotones();
		
	}
	
	public void addBotones() {
		
		for (String sede : principal.cargaArchivos.cargarSedes()) {
			
			GridBagConstraints gbc = new GridBagConstraints();
	        gbc.gridwidth = GridBagConstraints.REMAINDER;
	        gbc.anchor = GridBagConstraints.CENTER;
			JButton button = new JButton(sede);
			
			formatButton(button);
			
//			button.setActionCommand("jajaSalu2");
//			
//			button.addActionListener(this);
			
			add(button, gbc);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
