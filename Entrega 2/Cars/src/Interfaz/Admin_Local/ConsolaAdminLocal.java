package Interfaz.Admin_Local;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Interfaz.Login.CrearCliente;
import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;

public class ConsolaAdminLocal extends JPanel implements MetodosAuxiliares, ActionListener {
	
	Principal principal;
	
	private GridBagConstraints gbc;
	
	private CrearCliente crearCliente;
	
	private RegistrarTrabajador registrarTrabajador;
	
	private  ModificarTrabajador modificarTrabajador;
	
	private JButton crearClienteButton;
	
	private JButton modificarTrabajadorButton;
	
	private JButton registrarTrabajadorButton;
	
	private JLabel crearClienteLabel;
	
	private JLabel registrarTrabajadorLabel;
	
	private JLabel modificarTrabajadorLabel;
	
	private JButton volver;
	
	public ConsolaAdminLocal(Principal principal) {
		
		this.principal = principal;
		
		this.registrarTrabajador = new RegistrarTrabajador(principal);
		this.modificarTrabajador = new ModificarTrabajador(principal);
		
		principal.addPanel(registrarTrabajador, "registrarTrabajador" );
		principal.addPanel(modificarTrabajador, "modificarTrabajador" );
		
		crearLabels();
		
		crearBotones();
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        
        volver = new JButton("LOGOUT");
		volver.setActionCommand("login");
		volver.addActionListener(this);
		
         add(crearClienteButton,gbc);
         gbc.gridx = 1;
         
         add(crearClienteLabel, gbc);
         gbc.gridy++;
         gbc.gridx = 0;
         
         add(registrarTrabajadorButton,gbc);
         gbc.gridx = 1;
         
         add(registrarTrabajadorLabel, gbc);
         gbc.gridy++;
         gbc.gridx = 0;
         
         add(modificarTrabajadorButton,gbc);
         gbc.gridx = 1;
         
         add(modificarTrabajadorLabel, gbc);
         gbc.gridy++;
         gbc.gridx = 0;
         
         add(volver, gbc);
		
	}

	private void crearLabels() {
		
		crearClienteLabel = new JLabel("Crear Cliente");
		subTitleText(crearClienteLabel);
		
		modificarTrabajadorLabel = new JLabel("Modificar Trabajador");
		subTitleText(modificarTrabajadorLabel);
		
		registrarTrabajadorLabel = new JLabel("Registrar Trabajador");
		subTitleText(registrarTrabajadorLabel);
		
	}

	private void crearBotones() {
		
		crearClienteButton = new JButton("uwu");
		formatButton(crearClienteButton);
		crearClienteButton.setActionCommand("crearCliente");
		crearClienteButton.addActionListener(this);
		
		modificarTrabajadorButton = new JButton("uwu");
		formatButton(modificarTrabajadorButton);
		modificarTrabajadorButton.setActionCommand("modificarTrabajador");
		modificarTrabajadorButton.addActionListener(this);
		
		registrarTrabajadorButton = new JButton("uwu");
		formatButton(registrarTrabajadorButton);
		registrarTrabajadorButton.setActionCommand("registrarTrabajador");
		registrarTrabajadorButton.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String grito = e.getActionCommand();
		
		if (grito.equals("crearCliente")) {
			this.crearCliente = new CrearCliente(principal);
		}else {
			principal.cambiarPanel(grito);
		}
		
	}

}
