package Interfaz.Admin_General;

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
		
		setVisible(true);
		
	}
	
}
