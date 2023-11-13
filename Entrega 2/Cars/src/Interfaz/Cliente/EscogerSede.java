package Interfaz.Cliente;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;

public class EscogerSede extends JPanel implements MetodosAuxiliares, ActionListener {
	
	Principal principal;
	private GridBagConstraints gbc;
	private Catalogo catalogo;
	private MenuCliente menuCliente;
	private FormalizarAlquiler formalizarAlquiler;
	private HacerReserva hacerReserva;
	private EntregarVehiculo entregarVehiculo;
	private CambiarReserva cambiarReserva;
	private NuevosConductores nuevosConductores;
	private JButton logOut;
	
	static final int textFieldSize = 20;
	
	static final int YSpace = 20;
	
	public EscogerSede(Principal principal) {
		
		this.principal = principal;
		this.gbc = new GridBagConstraints();
		
		this.menuCliente = new MenuCliente(principal);
		this.formalizarAlquiler = new FormalizarAlquiler(principal);
		this.hacerReserva = new HacerReserva(principal);
		this.entregarVehiculo = new EntregarVehiculo(principal);
		this.cambiarReserva = new CambiarReserva(principal);
		this.nuevosConductores = new NuevosConductores(principal);
		
		principal.addPanel(menuCliente, "menuCliente" );
		principal.addPanel(formalizarAlquiler, "formalizarAlquiler" );
		principal.addPanel(hacerReserva, "hacerReserva" );
		principal.addPanel(entregarVehiculo, "entregarVehiculo" );
		principal.addPanel(cambiarReserva, "cambiarReserva" );
		principal.addPanel(nuevosConductores, "nuevosConductores" );
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
		
		//setBackground(Principal.globalTheme);
		
		setSize(500, 500);
		
		setBorder(new EmptyBorder(40, 40, 40, 40)); //PARA PONER MARGENES
		
		addBotones();
		
		addCatalogos();
		
	}
	
	private void addBotones() {
		
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        logOut = new JButton("LOG OUT");
		formatButton(logOut);
		logOut.setActionCommand("login");
		logOut.addActionListener(this);
		add(logOut, gbc);
		gbc.gridy++;
		addSpace(YSpace);
		
		
		for (String sede : principal.cargaArchivos.cargarSedes()) {
			
			JButton button = new JButton(sede);
			
			formatButton(button);
			
			button.setActionCommand(sede);
			
			button.addActionListener(this);
			
			add(button, gbc);
			gbc.gridy++;
		}

	}
	
	private void addCatalogos() {

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 2;
        
		for (String sede : principal.cargaArchivos.cargarSedes()) {
					
			JButton button = new JButton("Catalogo");
			
			formatButton(button);
			button.setActionCommand(sede+"!");
			button.addActionListener(this);
			
			add(button, gbc);
			gbc.gridy++; 
		
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String grito = e.getActionCommand();
		
		if (grito.charAt(grito.length() - 1) == '!') {
			catalogo = new Catalogo(principal, grito.replace("!", ""));
			
		} else if (grito.equals("login")) {
			principal.cambiarPanel(grito); 
			
		} else {
			principal.setSedeActual(grito.replace("!", ""));
			principal.cambiarPanel("menuCliente");
			
		} 
	}
	private void addSpace(int Yspace) {
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
		gbc.gridy++;
	}
	
}
