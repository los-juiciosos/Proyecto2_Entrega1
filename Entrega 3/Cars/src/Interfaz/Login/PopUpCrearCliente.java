package Interfaz.Login;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;

import Interfaz.Principal.Principal;

public class PopUpCrearCliente extends JDialog{

	Principal principal;
	
	CrearCliente crearCliente;
	
	public PopUpCrearCliente(Principal principal) {
		super(principal, "Crear Usuario", true);
		
		this.crearCliente = new CrearCliente(principal, this);
		
		add(crearCliente);
		
		pack();
		
		setResizable(false);
		
		centerWindow();
		
		setVisible(true);
	}
	
	public void close() {
		dispose();
	}
	
	public void centerWindow() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 3; //no es el centro pero se ve mas lindo

        setLocation(x, y);
	}
}
