package Interfaz.Principal;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
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

public class Notificacion extends JDialog implements MetodosAuxiliares{
	
	private GridBagConstraints gbc;
	private ImageIcon sonrisa1;
	
	public Notificacion(String mensaje) {
		
		setLocation(100,100);
		
		setLayout(new GridBagLayout());
		
		gbc = new GridBagConstraints();
		
		gbc.gridx=0;
		gbc.gridy = 0;
		

//		JLabel advertencia = new JLabel (mensaje);
		
		JTextArea advertencia = new JTextArea(mensaje);
        advertencia.setEditable(false);
        advertencia.setLineWrap(true);
        advertencia.setWrapStyleWord(true);
        advertencia.setPreferredSize(new Dimension(700, 600));
        
		sonrisa1 = new ImageIcon("./imagenes/sonrisa.png");
		
		ImageIcon sonrisa = resizeImage("./imagenes/sonrisa.png", 200, 200);
		JLabel imagen = new JLabel(sonrisa);
		
		setTitle("NOTIFICACION");
		add(advertencia);
		add(imagen);
		
		setModal(true);
		setResizable(true);
		setSize(1000,800);
		setVisible(true);
		

	}
	
	private static ImageIcon resizeImage(String imagePath, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
}
}
