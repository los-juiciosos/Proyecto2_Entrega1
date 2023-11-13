package Interfaz.Admin_Local;

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

import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;

public class RegistrarTrabajador extends JPanel implements MetodosAuxiliares, ActionListener  {

	Principal principal;
	
	private GridBagConstraints gbc;
	
	private JButton confirmar;
	
	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	
	public RegistrarTrabajador(Principal principal) {
		
		this.principal = principal;
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        
        addCampos();
		
		addDesplegables();
        
		confirmar = new JButton("Confirmar");
		confirmar.setActionCommand("consolaAdminLocal");
		confirmar.addActionListener(this);
		add(confirmar,gbc);
	}
	
	private void addDesplegables() {
		JComboBox<String> sedes = new JComboBox<>();
        sedes.addItem("SEDE 1");
        sedes.addItem("SEDE 2");
        sedes.addItem("SEDE 3");
        add(sedes, gbc);
        
        JComboBox<String> tipo = new JComboBox<>();
        tipo.addItem("TIPO 1");
        tipo.addItem("TIPO 2");
        tipo.addItem("TIPO 3");
        add(tipo,gbc);
		
	}

	private void addCampos() {
		
		JLabel instruccion = new JLabel("Datos Personales");
		subTitleText(instruccion);
		add(instruccion,gbc);
        
        String[] campos = {"Nombre completo", "Fecha de nacimiento (dd/mm/YYYY)", "Nacionalidad", 
                "No. Documento de identidad"};
        
        for (String mensaje : campos) {
        	JTextField campo = new JTextField(textFieldSize);
            ponerTextitoGris(campo, mensaje);
            addSpace(YSpace);
            add(campo,gbc);
		}
        
        JButton fotoDocumento = new JButton("Foto Documento");
        formatButton(fotoDocumento);
        add(fotoDocumento,gbc);
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
