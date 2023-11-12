package Interfaz.Cliente;

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

public class FormalizarAlquiler extends JPanel implements MetodosAuxiliares, ActionListener{

	Principal principal;
	
	private JTextField idReserva;
	
	private JLabel deseaAñadirConductores;
	
	private JButton nuevosConductores;
	
	private JLabel deseaCambiarReserva;
	
	private JButton cambiarReserva;
	
	private JComboBox<String> seguros;
	
	private JButton confirmar;
	
	private GridBagConstraints gbc;
	
	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	
	public FormalizarAlquiler(Principal principal) {
		
		this.principal = principal;
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridx = 0;
        gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		
		JLabel rellenar = new JLabel("Completa los siguientes campos");
		subTitleText(rellenar);
		
		JLabel escogeSeguro = new JLabel("Selecciona un seguro");
		subTitleText(escogeSeguro);
		
		crearAtributos();
		
		add(rellenar, gbc);
		gbc.gridy++;
		
		add(idReserva, gbc);
		gbc.gridy++;

		add(deseaAñadirConductores, gbc);
		gbc.gridx = 1;
		
		add(nuevosConductores, gbc);
		gbc.gridy++;
		gbc.gridx = 0;
		
		add(deseaCambiarReserva,gbc);
		gbc.gridx = 1;
		
		add(cambiarReserva, gbc);
		gbc.gridy++;
		gbc.gridx = 0;
		
		add(escogeSeguro,gbc);
		gbc.gridy++;
		
        add(seguros,gbc);
        gbc.gridy++;
        
        add(confirmar, gbc);
        gbc.gridy++;
        
        addSpace(YSpace);

	}
	
	private void crearAtributos() {
		
		idReserva = new JTextField(textFieldSize);
        ponerTextitoGris(idReserva, "ID de reserva");
        
        deseaAñadirConductores = new JLabel("Desea añadir nuevos conductores?");
        subTitleText(deseaAñadirConductores);
        
        deseaCambiarReserva = new JLabel("Desea cambiar datos de su reserva?");
        subTitleText(deseaCambiarReserva);
        
        nuevosConductores = new JButton("aqui va una foto");
		formatButton(nuevosConductores);
		nuevosConductores.setActionCommand("nuevosConductores");
		nuevosConductores.addActionListener(this);
		
		cambiarReserva = new JButton("aqui va una foto");
		formatButton(cambiarReserva);
		cambiarReserva.setActionCommand("cambiarReserva");
		cambiarReserva.addActionListener(this);
		
		seguros = new JComboBox<>();
		seguros.addItem("uwu");
		seguros.addItem("uwu");
		seguros.addItem("uwu");
		
		confirmar = new JButton("confirmar");
		formatButton(confirmar);
		confirmar.setActionCommand("menuCliente");
		confirmar.addActionListener(this);
		
	}

	private void addSpace(int Yspace) {
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String grito = e.getActionCommand();
		
		principal.cambiarPanel(grito);
		
	}
}
