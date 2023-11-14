package Interfaz.Cliente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Interfaz.Principal.ErrorDisplay;
import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Notificacion;
import Interfaz.Principal.Principal;
import RentadoraModelo.Cliente;

public class EntregarVehiculo extends JPanel implements MetodosAuxiliares, ActionListener{

	Principal principal;
	
	private GridBagConstraints gbc;
	private JButton confirmar;
	static final int textFieldSize = 20;
	static final int YSpace = 5;
	private ErrorDisplay error;
	private ArrayList<JTextField> listaCampos;
	private Notificacion notify;
	private JButton volver;
	
	public EntregarVehiculo(Principal principal) {
		
		this.principal = principal;
		this.listaCampos = new ArrayList<JTextField>();
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		
		addCampos();
		
		confirmar = new JButton("Confirmar");
		formatButton(confirmar);
		confirmar.setActionCommand("menuCliente");
		confirmar.addActionListener(this);
		add(confirmar,gbc);
		
		volver = new JButton("VOLVER");
		volver.setActionCommand("VOLVER");
		volver.addActionListener(this);
		formatButton(volver);
		add(volver, gbc);
		
	}
	
	private void addCampos() {
		
	    String[] campos = {"Ingrese la placa", "ID de reserva"};
	    
	    for (String mensaje : campos) {
	    	JTextField campo = new JTextField(textFieldSize);
	        ponerTextitoGris(campo, mensaje);
	        addSpace(YSpace);
	        add(campo,gbc);
	        listaCampos.add(campo);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String grito = e.getActionCommand();
		
		if (grito.equals("VOLVER")) {
			principal.cambiarPanel("menuCliente");
		}
		
		else if (grito.equals("Ingrese la placa") || grito.equals( "ID de reserva")) {
			error = new ErrorDisplay("LLENE TODOS LOS CAMPOS PORFAVOR");
		}
		else {
			String id = listaCampos.get(1).getText();
			String placaDevolver = listaCampos.get(0).getText();
			Cliente clienteActual = (Cliente) principal.usuarioActual;
			String entregaSuccess = clienteActual.entregarVehiculo(id,placaDevolver,principal.sedeActual);
			
			if (entregaSuccess == null) {
				error = new ErrorDisplay("NO SE ENCONTRO UNA DEVOLUCIÓN PARA ESTA SEDE CON ESA PLACA");
			}
			else {
				notify = new Notificacion("CARRO ENTREGADO CON EXITO!!!!!!!!! \n VUELVA PRONTO!!!!");
			}
			principal.cambiarPanel(grito);
			
		}
		
	}
	
	private void addSpace(int Yspace) {
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
        Color color1 = new Color(255, 165, 0);
        Color color2 = Color.PINK;

        // Create a gradient paint
        GradientPaint gradientPaint = new GradientPaint(start, color1, end, color2);

        // Set the paint for the graphics context
        g2d.setPaint(gradientPaint);

        // Fill the background with the gradient paint
        g2d.fillRect(0, 0, width, height);
    }

}
