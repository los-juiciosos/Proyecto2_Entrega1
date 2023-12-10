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

import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;

public class RealizarPago extends JDialog implements MetodosAuxiliares, ActionListener{
	
	private GridBagConstraints gbc;
	private ImageIcon sonrisa1;
	
	public RealizarPago() {
		
		setLocation(100,100);
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		
		gbc = new GridBagConstraints();
		
		gbc.gridx=0;
		gbc.gridy = 0;
		
		JLabel nomPasarela = new JLabel("PASARELA: PAYPAL");
		JLabel textoGeneral = new JLabel("Monto: "+ Principal.precioActual + " es el " + Principal.porcentaje + "% del total");
        		
		setTitle("PAYPAL");
		add(nomPasarela);
		add(textoGeneral);
		
		setModal(true);
		setResizable(true);
		setSize(1000,800);
		setVisible(true);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}