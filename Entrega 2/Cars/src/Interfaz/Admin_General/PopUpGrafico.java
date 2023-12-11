package Interfaz.Admin_General;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;

import Interfaz.Principal.Principal;

public class PopUpGrafico extends JDialog{
	
	Principal principal;
	
	private MatrizFrecuencia matrizFrecuencia;
	
	public PopUpGrafico(Principal principal) {
		super(principal, "GRAFICO", false);
		
		this.principal = principal;
		
		this.matrizFrecuencia = new MatrizFrecuencia(principal);
		
		add(matrizFrecuencia);
		
		setSize(MatrizFrecuencia.ancho+50, MatrizFrecuencia.alto+150);
		
		centerWindow();
		
		setVisible(true);
		
	}
	
	public void centerWindow() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;

        setLocation(x, y);
	}
	
	
}
