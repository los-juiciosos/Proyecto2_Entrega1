package Interfaz.Cliente;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JTable;

import Interfaz.Principal.Principal;

public class Catalogo extends JDialog {
	
	Principal principal;
	
	private JTable catalogo;
	
	private String sede;
	
	public Catalogo(Principal principal, String sede) {
		
		super(principal, "CATALAGO", false);
		this.principal = principal;
		this.sede = sede;
		this.catalogo = crearCatalogo();
		
		setLayout(new BorderLayout());
		
		add(catalogo,BorderLayout.CENTER);
		
		setResizable(false);
		
		pack();
		
		setVisible(true);
				
	}
	
	private JTable crearCatalogo() {
		
		String[] columnNames = {"Placa", "Marca", "Modelo", 
				"Color", "Transmision", "Tipo", 
				"Capacidad", "Categoria"};
		
		Object[][] valores = principal.cargaArchivos.cargarAutos(sede);
		
		return new JTable(valores, columnNames);
		
	}
}
