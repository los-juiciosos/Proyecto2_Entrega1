package Interfaz.Admin_General;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;

public class RevisarGrafico extends JPanel implements MetodosAuxiliares, ActionListener {
	
	Principal principal;
	private PopUpGrafico popUpGrafico;
	
	private JButton confirmar;
	
	private GridBagConstraints gbc;
	
	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	
	public RevisarGrafico(Principal principal) {
		
		this.principal = principal;
		
		crearBotones();
		
		this.gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
		
		confirmar = new JButton("VOLVER");
		formatButton(confirmar);
		confirmar.setActionCommand("consolaAdminGeneral");
		confirmar.addActionListener(this);
		add(confirmar, gbc);
	}
	
	private void crearBotones() {
		
		for (String sede : principal.cargaArchivos.cargarSedes()) {
			
			JButton button = new JButton(sede);
			
			formatButton(button);
			
			button.setActionCommand(sede);
			
			button.addActionListener(this);
			
			add(button, gbc);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String grito = e.getActionCommand();
		
		if (grito.equals("consolaAdminGeneral")) {
			principal.cambiarPanel(grito);
		} else {
			
			MatrizFrecuencia.sedeActual = grito;
			
			popUpGrafico = new PopUpGrafico(principal);
		}
		
	}
	
	private void addSpace(int Yspace) {
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
		gbc.gridy++;
	}

}
