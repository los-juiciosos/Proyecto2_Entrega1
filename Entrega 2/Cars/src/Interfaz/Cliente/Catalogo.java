package Interfaz.Cliente;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;

public class Catalogo extends JDialog implements MetodosAuxiliares {
	
	Principal principal;
	
	private JTable catalogo;
	
	private String sede;
	
	private JScrollPane scrollPane;
	
	public Catalogo(Principal principal, String sede) {
		
		super(principal, "CATALAGO", false);
		this.principal = principal;
		this.sede = sede;
		this.catalogo = crearCatalogo();
		
		setLayout(new BorderLayout());
		
		JPanel panel = newGradientPanel(cutePurple, Color.PINK);
		setPanelBackground(panel);
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(40, 40, 40, 40));
		panel.setBackground(cutePurple);
//		panel.setSize(2000,2000);
		
		this.scrollPane = new JScrollPane(catalogo);
		
		panel.add(scrollPane, BorderLayout.CENTER);
		
		add(panel, BorderLayout.CENTER);
		
//		setResizable(false);
		
		setSize(1300,250);
		
		setVisible(true);
				
	}
	
	private void setPanelBackground(JPanel panel) {
		
		
	}

	private JTable crearCatalogo() {
		
		String[] columnNames = {"Placa", "Marca", "Modelo", 
				"Color", "Transmision", "Tipo", 
				"Capacidad", "Categoria"};
		
		Object[][] valores = principal.cargaArchivos.cargarAutos(sede);
		
		JTable tabla = new JTable(valores, columnNames);
		
		formatJTable(tabla);
				
		return tabla;
		
	}
}
