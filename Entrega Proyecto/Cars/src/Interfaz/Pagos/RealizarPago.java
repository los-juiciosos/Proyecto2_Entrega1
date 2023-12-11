package Interfaz.Pagos;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Interfaz.Principal.ErrorDisplay;
import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Notificacion;
import Interfaz.Principal.Principal;
import RentadoraModelo.MetodoTransaccion;
import RentadoraModelo.Pagos;

public class RealizarPago extends JDialog implements MetodosAuxiliares, ActionListener{
	
	private GridBagConstraints gbc;
	private ImageIcon sonrisa1;
	static final int YSpace = 5;
	private JButton pagar;
	private JTextField numeroCuenta;
	private ErrorDisplay error;
	private Notificacion notify;

	
	
	public RealizarPago() {
		
		setLocation(100,100);
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		
		gbc = new GridBagConstraints();
		setTitle(Principal.pasarelaActual);
		
		numeroCuenta = new JTextField("Ingrese número de cuenta");
		add(numeroCuenta, gbc);
		addSpace(YSpace);
		
		JLabel nomPasarela = new JLabel("PASARELA: " + Principal.pasarelaActual + " \n\n");
		add(nomPasarela,gbc);
		addSpace(YSpace);
		
		JLabel textoGeneral = new JLabel("Monto: "+ Principal.precioActual + " es el " + Principal.porcentaje + "% del total "+ " \n\n");
		add(textoGeneral,gbc);
		addSpace(YSpace);
		
		addSpace(YSpace*2);
		addSpace(YSpace*2);
		pagar = new JButton("PAGAR");
		pagar.setActionCommand("PAGAR");
		pagar.addActionListener(this);
		formatButton(pagar);
		add(pagar, gbc);
		
		
		setModal(true);
		setSize(1000,800);
		setVisible(true);
		

	}
	
	private void addSpace(int Yspace) {
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String grito = e.getActionCommand();
		
		if (grito.equals("PAGAR")) {
			Pagos pago = new Pagos(Principal.pasarelaActual,Principal.precioActual,numeroCuenta.getText(),Principal.estadoTarjeta);
			MetodoTransaccion metodoTransaccion = pago.getMetodo();
			boolean estadoCobro = metodoTransaccion.cobrar(numeroCuenta.getText());
			if (estadoCobro == true) {
				
			pago.generarFactura(Principal.pasarelaActual);
			
			boolean verifyEstado = pago.isEstado();
			
			if (verifyEstado == true) {
				notify = new Notificacion("SE PAGO CON EXITO");
				Principal.estadoTransaccion = true;
			}
			else {
				error = new ErrorDisplay("NO SE PAGO CON EXITO !!!!!!!!!!!!!!!!");
			}
			
			}
			else {
				error = new ErrorDisplay("NO ES UNA CUENTA DE "+ Principal.pasarelaActual+ " !!!!!!!!!!!!!!!!");
			}
			dispose();
			
		}
		
	}
	
}