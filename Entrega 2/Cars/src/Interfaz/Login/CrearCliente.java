package Interfaz.Login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Interfaz.Principal.ErrorDisplay;
import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;
import Interfaz.Principal.Verify;

public class CrearCliente extends JDialog implements MetodosAuxiliares,ActionListener{
	
	Principal principal;
	private GridBagConstraints gbc;
	static final int textFieldSize = 20;
	static final int YSpace = 2;
	private ArrayList<JTextField> listaCampos;
	private Verify verificador;
	private String fotoName;
	private String documentoName;
	private JTextField campoDocumento;
	private JTextField campoFoto;
	private ErrorDisplay error;
	
	public CrearCliente(Principal principal) {		
		super(principal, "Crear Usuario", true);
		this.principal = principal;
		this.listaCampos = new ArrayList<JTextField>();
		this.verificador = new Verify();
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
		
		setBackground(Color.ORANGE);
		
		setVisible(true);
	}
	
	private void addCamposIzquierda() {
        
        JLabel instruccion = new JLabel("Datos Personales");
		subTitleText(instruccion);
		add(instruccion,gbc);
		gbc.gridy++;
        
        String[] campos = {"Usuario","Contraseña","Nombre completo", "Fecha de nacimiento (dd/mm/YYYY)", "Nacionalidad", 
                "No. Documento de identidad", "Celular", "Email"};
        
        for (String mensaje : campos) {
        	JTextField campo = new JTextField(textFieldSize);
        	listaCampos.add(campo);
            ponerTextitoGris(campo, mensaje);
            addSpace(YSpace);
            add(campo,gbc);
            gbc.gridy++;
		}
        
        JButton fotoDocumento = new JButton("Foto Documento");
        fotoDocumento.setActionCommand("DOCUMENTO");
        fotoDocumento.addActionListener(this);
        formatButton(fotoDocumento);
        add(fotoDocumento,gbc);
        gbc.gridy++;

        
    	campoDocumento = new JTextField(textFieldSize);
    	campoDocumento.setEnabled(false);
        addSpace(YSpace);
        add(campoDocumento,gbc);
        
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
        	listaCampos.add(campo);
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
        	listaCampos.add(campo);
            add(campo,gbc);
            gbc.gridy++;
		}
        
        JButton fotoDocumento = new JButton("Foto Licencia");
        fotoDocumento.setActionCommand("FOTO");
        fotoDocumento.addActionListener(this);
        formatButton(fotoDocumento);
        add(fotoDocumento,gbc);
        gbc.gridy++;

        
        campoFoto = new JTextField(textFieldSize);
    	campoFoto.setEnabled(false);
        addSpace(YSpace);
        add(campoFoto,gbc);
        gbc.gridy++;

        
        JButton guardar = new JButton("crearUsuario");
        guardar.setActionCommand("GUARDAR");
        guardar.addActionListener(this);
        formatButton(guardar);
        add(guardar,gbc);

	}
	
	private void addSpace(int Yspace) {
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
		gbc.gridy++;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String grito = e.getActionCommand();
		
		if (grito.equals("GUARDAR")){
			
			ArrayList<String> listaInfo = new ArrayList<String>();
			for (JTextField field : listaCampos) {
				listaInfo.add(field.getText());
			}
			listaInfo.add(7,documentoName);
			listaInfo.add(fotoName);
			listaInfo.add("true");
			listaInfo.add("cliente");
			
			boolean verifyCampos = verificador.verifyLleno(listaInfo);
			if (verifyCampos == true) {
				Properties pLogin = principal.cargaArchivos.cargarLogin();
				boolean verifyUser = verificador.verifyExistUser(pLogin, listaInfo.get(0));
				if (verifyUser == true) {
					principal.cargaArchivos.guardarNuevoUsuario(listaInfo);
					dispose();
				}
				else {
					error = new ErrorDisplay("USUARIO YA EXISTENTE, INGRESE OTRO USUARIO");
				}
			}
			else {
				error = new ErrorDisplay("LLENE TODO LOS CAMPOS CORRECTAMENTE");
			}
			
		}
		else if (grito.equals("FOTO")) {
			fotoName = verificador.chooseFile();
			if (fotoName == null) {
				fotoName = "ERROR";
			}
			campoFoto.setText(fotoName);
		}
		else if (grito.equals("DOCUMENTO")) {
			documentoName = verificador.chooseFile();
			if (documentoName == null) {
				documentoName = "ERROR";
			}
			campoDocumento.setText(documentoName);
		}
		
	}
}
