package Interfaz.Admin_Local;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;

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
import RentadoraModelo.AdministradorLocal;

public class ModificarTrabajador extends JPanel implements MetodosAuxiliares, ActionListener {
	
	Principal principal;
	
	private GridBagConstraints gbc;
	
	static final int textFieldSize = 20;
	private JComboBox<String> aspectoTrabajador;
	private JTextField  nuevoValor;
	private JTextField  nombreTrabajador;
	static final int YSpace = 5;
	private ErrorDisplay error;
	private Notificacion notify;
	private Verify verificador;

	public ModificarTrabajador(Principal principal) {
		
		this.verificador = new Verify();
		this.principal = principal;
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        
        JLabel trabajadorLabel = new JLabel("Trabajador a modificar");
		subTitleText(trabajadorLabel);
		
		nombreTrabajador = new JTextField(textFieldSize);
		ponerTextitoGris(nombreTrabajador, "Usuario del Trabajador");
		
		JLabel aspectoLabel = new JLabel("Aspecto a Modificar");
		subTitleText(trabajadorLabel);
		
		String[] campos = {"Nombre","FechaNacimiento dd/mm/YYYY","Nacionalidad","Documento de Identidad",
				"Imagen", "Sede en la que trabaja","Rol del empleado"};
	
		aspectoTrabajador = new JComboBox<String>();
		for (String valor: campos) {
			aspectoTrabajador.addItem(valor);
		}
		
		nuevoValor = new JTextField(textFieldSize);
		ponerTextitoGris(nuevoValor, "Nuevo Valor");
		
		JButton confirmar = new JButton("Confirmar");
		confirmar.setActionCommand("consolaAdminLocal");
		confirmar.addActionListener(this);
		
		add(trabajadorLabel,gbc);
		
		add(nombreTrabajador,gbc);
		
		add(aspectoLabel,gbc);
		
		add(aspectoTrabajador,gbc);
		
		add(nuevoValor,gbc);
		
		add(confirmar,gbc);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String grito = e.getActionCommand();
		
		if (nuevoValor.getText().equals("Nuevo Valor") || nombreTrabajador.getText().equals("Usuario del Trabajador")) {
			error = new ErrorDisplay("PORFAVOR LLENE TODOS LOS CAMPOS");
		}
		else {
			AdministradorLocal admin = (AdministradorLocal) principal.usuarioActual;
			Properties pLogin = principal.cargaArchivos.cargarLogin();
			boolean verifyMismaSede = verificador.verifyEmpleadoSede(pLogin, nombreTrabajador.getText(), admin.getSede());
			if (verifyMismaSede == true) {
				admin.modificarInformacionTrabajador(nombreTrabajador.getText(), (String) aspectoTrabajador.getSelectedItem()
						,nuevoValor.getText(), admin.getSede());
				notify = new Notificacion("CAMBIOS REALIZADOS CORRECTAMENTE");
				principal.cambiarPanel("consolaAdminLocal");
			}
			else {
				error = new ErrorDisplay("NO ENCONTRE A ESE EMPLEADO DENTRO DE MI JURISDICCIÓN");
			}
		}
		
		principal.cambiarPanel(grito);
		
	}
	
	
}
