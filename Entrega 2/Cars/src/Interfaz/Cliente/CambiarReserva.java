package Interfaz.Cliente;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;

public class CambiarReserva extends JPanel implements MetodosAuxiliares, ActionListener {

	Principal principal;
	
	private GridBagConstraints gbc;
	
	private JLabel instruccion;
	
	private JComboBox<String> categorias;
	
	private JTextField nuevoValor;
	
	private JButton confirmar;
	
	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	
	public CambiarReserva(Principal principal) {
		
		this.principal = principal;
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		
		crearAtributos();
		
		add(instruccion, gbc);
		
		add(categorias, gbc);
		
		add(nuevoValor, gbc);
		
		add(confirmar,gbc);
		
	}

	private void crearAtributos() {
		
		instruccion = new JLabel("¿Que categoria deseas editar?");
		titleText(instruccion);
		
		categorias = new JComboBox<>();
		categorias.addItem("Fecha Devolucion");
		categorias.addItem("Hora Devolucion");
		categorias.addItem("Sede Entrega");	
		
		nuevoValor = new JTextField(textFieldSize);
		ponerTextitoGris(nuevoValor, "Nuevo Valor");
		
		confirmar = new JButton("Confirmar");
		formatButton(confirmar);
		confirmar.setActionCommand("formalizarAlquiler");
		confirmar.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String grito = e.getActionCommand();
		
		principal.cambiarPanel(grito);
		
	}
	
}
