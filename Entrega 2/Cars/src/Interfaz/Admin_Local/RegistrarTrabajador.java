package Interfaz.Admin_Local;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Interfaz.Principal.ErrorDisplay;
import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Notificacion;
import Interfaz.Principal.Principal;
import Interfaz.Principal.Verify;

public class RegistrarTrabajador extends JPanel implements MetodosAuxiliares, ActionListener  {

	Principal principal;
	
	private GridBagConstraints gbc;
	
	private JButton confirmar;
	private JTextField campoFoto;
	private JComboBox tipoEmpleado;
	private JComboBox sedes;
	ArrayList<String> campos;
	ArrayList<JTextField> listaCampos;
	private Verify verificador;
	private ErrorDisplay error;
	private Notificacion notify;
	private JButton volver;

	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	
	public RegistrarTrabajador(Principal principal) {
		this.verificador = new Verify();
		this.campos = new ArrayList<String>();
		this.listaCampos = new ArrayList<JTextField>();
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
		
		volver = new JButton("VOLVER");
		volver.setActionCommand("VOLVER");
		volver.addActionListener(this);
		add(volver, gbc);
	}
	
	private void addDesplegables() {
		
		ArrayList<String> todasSedes = principal.cargaArchivos.cargarSedes();
		sedes = new JComboBox<>();
		for (String sede: todasSedes) {			
        sedes.addItem(sede);
		}
		add(sedes,gbc);
        
        tipoEmpleado = new JComboBox<>();
        tipoEmpleado.addItem("inventario");
        tipoEmpleado.addItem("mostrador");
        add(tipoEmpleado,gbc);
		
	}

	private void addCampos() {
		
		JLabel instruccion = new JLabel("Datos Personales");
		subTitleText(instruccion);
		add(instruccion,gbc);
        
		campos.add("Usuario");
		campos.add("Contraseña");
		campos.add("Nombre completo");
		campos.add("Fecha de nacimiento (dd/mm/YYYY)");
		campos.add("Nacionalidad");
		campos.add("No. Documento de identidad");
		
        for (String mensaje : campos) {
        	JTextField campo = new JTextField(textFieldSize);
            ponerTextitoGris(campo, mensaje);
            addSpace(YSpace);
            listaCampos.add(campo);
            add(campo,gbc);
		}
        
        
        JButton fotoDocumento = new JButton("Foto Documento");
        fotoDocumento.setActionCommand("FOTO");
        fotoDocumento.addActionListener(this);
        formatButton(fotoDocumento);
        add(fotoDocumento,gbc);
        
        campoFoto = new JTextField(textFieldSize);
        campoFoto.setEnabled(false);
        addSpace(YSpace);
        listaCampos.add(campoFoto);
        add(campoFoto,gbc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String grito = e.getActionCommand();
		
		if (grito.equals("VOLVER")) {
			principal.cambiarPanel("consolaAdminLocal");
		}
		
		else if (grito.equals("FOTO")) {
			String fotoName = verificador.chooseFile();
			if (fotoName == null) {
				fotoName = "ERROR";
			}
			campoFoto.setText(fotoName);
		}
		
		else {
			ArrayList<String> listaInfo = new ArrayList<String>();
			for (JTextField field : listaCampos) {
				if (campos.contains(field.getText())) {
					listaInfo.add("");
				}
				else {
					listaInfo.add(field.getText());
				}
			}
			
			String rol = (String) tipoEmpleado.getSelectedItem();
			String sede = (String) sedes.getSelectedItem();
			
			listaInfo.add(sede);
			listaInfo.add("true");
			listaInfo.add(rol);
			
			boolean verifyCampos = verificador.verifyLleno(listaInfo);
			if (verifyCampos == true) {
				Properties pLogin = principal.cargaArchivos.cargarLogin();
				boolean verifyUser = verificador.verifyExistUser(pLogin, listaInfo.get(0));
				if (verifyUser == true) {
					principal.cargaArchivos.guardarNuevoUsuario(listaInfo);
					notify = new Notificacion("GUARDADO EXITOSAMENTE");
				}
				else {
					error = new ErrorDisplay("USUARIO YA EXISTENTE, INGRESE OTRO USUARIO");
				}
			}
			else {
				error = new ErrorDisplay("LLENE TODO LOS CAMPOS CORRECTAMENTE");
			}
			principal.cambiarPanel(grito);
		}
		
		
	}
	
	private void addSpace(int Yspace) {
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
	}

}
