package Interfaz.Cliente;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;
import Interfaz.Principal.Verify;

public class HacerReserva extends JPanel implements MetodosAuxiliares, ActionListener  {

	Principal principal;
	
	private JComboBox<String> sedes;
	private JComboBox<String> categorias;
	private JButton confirmar;
	private GridBagConstraints gbc;
	static final int textFieldSize = 20;
	static final int YSpace = 5;
	private ArrayList<JComboBox> listaSede;
	private ArrayList<JComboBox> listaCategoria;
	private Verify verificador;
	
	public HacerReserva(Principal principal) {
		
		this.principal = principal;
		this.listaSede = new ArrayList<JComboBox>();
		this.listaCategoria = new ArrayList<JComboBox>();
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		
		addCampos();
		
		addBoxes();
		
		confirmar = new JButton("Confirmar");
		formatButton(confirmar);
		confirmar.setActionCommand("CONFIRMAR");
		confirmar.addActionListener(this);
		add(confirmar,gbc);

	}
	
	private void addBoxes() {
		
		ArrayList<String> todasSedes = principal.cargaArchivos.cargarSedes();
		sedes = new JComboBox<>();
		for (String sede: todasSedes) {			
        sedes.addItem(sede);
		}
		listaSede.add(sedes);
		add(sedes,gbc);
		
		ArrayList<String> todasCategorias = principal.cargaArchivos.cargarCategoria();
        
		categorias = new JComboBox<>();
		for (String categoria: todasCategorias) {
        categorias.addItem(categoria);
		}
		listaCategoria.add(categorias);
		add(categorias,gbc);
		
	}

	private void addCampos() {
		
        String[] campos = {"Día recogida (dd/mm/YYYY)", "Hora Recogida (HH:MM)", 
        		"Día entrega (dd/mm/YYYY)", "Hora Entrega (HH:MM)"};
        
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
		if (grito.equals("CONFIRMAR")) {
			String sede = (String) sedes.getSelectedItem();
			String categoria = (String) categorias.getSelectedItem();
			
//			verificador.verifyLleno(null);
			principal.cambiarPanel("menuCliente");
		}
		
	}
	
	private void addSpace(int Yspace) {
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
	}

}
