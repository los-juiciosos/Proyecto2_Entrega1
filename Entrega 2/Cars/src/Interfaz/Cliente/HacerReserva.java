package Interfaz.Cliente;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;

public class HacerReserva extends JPanel implements MetodosAuxiliares, ActionListener  {

	Principal principal;
	
	private JComboBox<String> sedes;
	
	private JComboBox<String> categorias;
	
	private JButton confirmar;
	
	private GridBagConstraints gbc;
	
	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	
	public HacerReserva(Principal principal) {
		
		this.principal = principal;
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		
		addCampos();
		
		addBoxes();
		
		confirmar = new JButton("Confirmar");
		formatButton(confirmar);
		confirmar.setActionCommand("menuCliente");
		confirmar.addActionListener(this);
		add(confirmar,gbc);

	}
	
	private void addBoxes() {
		sedes = new JComboBox<>();
        sedes.addItem("SEDE 1");
        sedes.addItem("SEDE 2");
        sedes.addItem("SEDE 3");
        
        add(sedes,gbc);
        
        categorias = new JComboBox<>();
        categorias.addItem("CATEGORIA 1");
        categorias.addItem("CATEGORIA 2");
        categorias.addItem("CATEGORIA 3");
        
        add(categorias,gbc);
		
	}

	private void addCampos() {
		
        String[] campos = {"Día recogida", "Hora Recogida", 
        		"Día entrega (dd/mm/YYYY)", "Hora Entrega"};
        
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
