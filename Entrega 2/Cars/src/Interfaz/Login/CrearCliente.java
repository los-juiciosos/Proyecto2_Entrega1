package Interfaz.Login;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;

public class CrearCliente extends JDialog implements MetodosAuxiliares {
	
	Principal principal;
	
	private GridBagConstraints gbc;
	
	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	
	public CrearCliente(Principal principal) {
		super(principal, "Crear Usuario", true);
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
		
        
		JLabel instruccion = new JLabel("Rellene los siguientes campos");
		titleText(instruccion);
		
		add(instruccion,gbc);
		gbc.gridy++;
		
		addCamposIzquierda();
		
		addCamposDerecha();
		
		setResizable(false);
		
		pack();
		
		setVisible(true);
	}
	
	private void addCamposIzquierda() {
        
        JLabel instruccion = new JLabel("Datos Personales");
		subTitleText(instruccion);
		add(instruccion,gbc);
		gbc.gridy++;
        
        String[] campos = {"Nombre completo", "Fecha de nacimiento (dd/mm/YYYY)", "Nacionalidad", 
                "No. Documento de identidad", "Foto documento", "Celular", "Email"};
        
        for (String mensaje : campos) {
        	JTextField campo = new JTextField(textFieldSize);
            ponerTextitoGris(campo, mensaje);
            addSpace(YSpace);
            add(campo,gbc);
            gbc.gridy++;
		}
        
        JButton fotoDocumento = new JButton("Foto Documento");
        formatButton(fotoDocumento);
        add(fotoDocumento,gbc);
        
	}
	
	private void addCamposDerecha() {
		
		gbc.gridx = 1;
        gbc.gridy = 1;
		
        JLabel instruccion = new JLabel("Tarjeta de Credito");
		subTitleText(instruccion);
		add(instruccion,gbc);
		gbc.gridy++;
		
        String[] campos = {"Tipo de Tarjeta", "No. Tarjeta", 
        		"Fecha Vencimiento (mm/YYYY)", "Codigo de Seguridad"};
        
        for (String mensaje : campos) {
        	JTextField campo = new JTextField(textFieldSize);
            ponerTextitoGris(campo, mensaje);
            addSpace(YSpace);
            add(campo,gbc);
            gbc.gridy++;
		}
        
        
        instruccion = new JLabel("Licencia de Conduccion");
		subTitleText(instruccion);
		add(instruccion,gbc);
		gbc.gridy++;
		
        campos = new String[]{"No. Licencia de Conducción", "País de expedición", "Fecha de Vencimiento"};
        
        for (String mensaje : campos) {
        	JTextField campo = new JTextField(textFieldSize);
            ponerTextitoGris(campo, mensaje);
            addSpace(YSpace);
            add(campo,gbc);
            gbc.gridy++;
		}
        
        JButton fotoDocumento = new JButton("Foto Licencia");
        formatButton(fotoDocumento);
        add(fotoDocumento,gbc);

	}
	
	private void addSpace(int Yspace) {
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
		gbc.gridy++;
	}
}
