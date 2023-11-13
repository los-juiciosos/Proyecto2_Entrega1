package Interfaz.Empleados;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import Interfaz.Principal.Principal;

public class EmpleadoInventario extends JPanel {
	
	Principal principal;
	
	public EmpleadoInventario(Principal principal) {
		
		this.principal = principal;
		
		setLayout(new GridBagLayout());
		
		
	}
}
