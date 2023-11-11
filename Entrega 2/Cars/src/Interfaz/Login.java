package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.geom.Point2D;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Login extends JPanel implements MetodosAuxiliares {
	
	private Principal principal;
	private JLabel nombreEmpresa;
	private JLabel bienvenida;
	private JTextField usuario;
	private JTextField password;
	private JButton ingresar;
	
	public Login (Principal principal) {
		
		this.principal = principal;
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
		
		//setBackground(Principal.globalTheme);
		
		setSize(500, 500);
		
		setBorder(new EmptyBorder(40, 40, 40, 40)); //PARA PONER MARGENES
		
		nombreEmpresa = new JLabel("Cuchau Motors");
		titleText(nombreEmpresa);
		
		bienvenida = new JLabel("Ingresa tu usuario y contraseña");
		subTitleText(bienvenida);
		
		usuario = new JTextField(40);
		ponerTextitoGris(usuario,"Usuario");
		
		password = new JTextField(40);
		ponerTextitoGris(password,"Contraseña");
		
		gbc.anchor = GridBagConstraints.CENTER;
		add(nombreEmpresa, gbc);
		
		addSpace(10);
		
		gbc.anchor = GridBagConstraints.WEST;
		add(bienvenida, gbc);
		
		addSpace(5);
		
		add(usuario, gbc);
		
		addSpace(5);
		
		add(password, gbc);
				
		requestFocus(null);
		
	}
	
	private void addSpace(int Yspace) {
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        // Define the start and end points for the gradient
        Point2D start = new Point2D.Float(0, 0);
        Point2D end = new Point2D.Float(0, height);

        // Define the colors for the gradient
        Color color1 = Color.RED;
        Color color2 = Color.YELLOW;

        // Create a gradient paint
        GradientPaint gradientPaint = new GradientPaint(start, color1, end, color2);

        // Set the paint for the graphics context
        g2d.setPaint(gradientPaint);

        // Fill the background with the gradient paint
        g2d.fillRect(0, 0, width, height);
    }

	
}
