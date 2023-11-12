package Interfaz.Cliente;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;

public class NuevosConductores extends JPanel implements MetodosAuxiliares, ActionListener {
	
	Principal principal;
	
	private JButton fotoDocumento;
	
	private GridBagConstraints gbc;
	
	private JButton confirmar;
	
	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	
	public NuevosConductores(Principal principal) {
		
		this.principal = principal;
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		
		addCampos();
		
		fotoDocumento = new JButton("Foto Documento");
		formatButton(fotoDocumento);
		add(fotoDocumento,gbc);
		
		confirmar = new JButton("Confirmar");
		formatButton(confirmar);
		confirmar.setActionCommand("formalizarAlquiler");
		confirmar.addActionListener(this);
		add(confirmar,gbc);
	}
	
	private void addCampos() {
		
	    String[] campos = {"Nombre", "Número de licencia", "País de licencia",
	    		"Fecha de Vencimiento"};
	    
	    for (String mensaje : campos) {
	    	JTextField campo = new JTextField(textFieldSize);
	        ponerTextitoGris(campo, mensaje);
	        addSpace(YSpace);
	        add(campo,gbc);
		}	
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
