package AplicacionClientes;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;

import Interfaz.Principal.Principal;

public class PopUpGrafico2 extends JDialog{
	
	AplicacionClientes AplicacionClientes;
	
	private MatrizFrecuencia2 matrizFrecuencia;
	
	public PopUpGrafico2(AplicacionClientes AplicacionClientes) {
		super(AplicacionClientes, "GRAFICO", false);
		
		this.AplicacionClientes = AplicacionClientes;
		
		this.matrizFrecuencia = new MatrizFrecuencia2(AplicacionClientes);
		
		add(matrizFrecuencia);
		
		setSize(MatrizFrecuencia2.ancho+50, MatrizFrecuencia2.alto+150);
		
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
