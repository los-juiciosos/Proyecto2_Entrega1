package Interfaz.Empleados;

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
import java.util.Properties;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Interfaz.Principal.ErrorDisplay;
import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Notificacion;
import Interfaz.Principal.Principal;
import Interfaz.Principal.Verify;
import RentadoraModelo.EmpleadoMostrador;

public class RevisarVehiculo extends JPanel implements MetodosAuxiliares, ActionListener {

	Principal principal;
	
	private GridBagConstraints gbc;
	
	private JButton confirmar;
	
	private JButton volver;
	
	private JTextField placa;
	
	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	private ErrorDisplay error;
	private Notificacion notify;
	private Verify verificador;
	
	public RevisarVehiculo(Principal principal) {
		
		this.verificador = new Verify();
		this.principal = principal;
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        
        JLabel instruccion = new JLabel("Mandar a revisar el Vehiculo:");
		subTitleText(instruccion);
		
		placa = new JTextField(textFieldSize);
		ponerTextitoGris(placa,"Placa");
		
		confirmar = new JButton("Confirmar");
		confirmar.setActionCommand("empleadoMostrador");
		confirmar.addActionListener(this);
		formatGradientButton(confirmar, cuteYellow, Color.PINK); 
		
		volver = new JButton("VOLVER");
		volver.setActionCommand("VOLVER");
		volver.addActionListener(this);
		formatGradientButton(volver, Color.PINK, cutePurple);
		
		
		add(instruccion, gbc);
		addSpace(YSpace);
		
		add(placa, gbc);
		
		addSpace(YSpace*4);
		add(confirmar, gbc);
		
		addSpace(YSpace*2);
		add(volver,gbc);
		
		requestFocus(null);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String grito = e.getActionCommand();
		
		if (grito.equals("VOLVER")) {
			principal.cambiarPanel("empleadoMostrador");
		}
		
		else if (placa.getText().equals("Placa")) {
			error = new ErrorDisplay("PORFAVOR LLENE EL CAMPO");
		}
		else {
			EmpleadoMostrador empleado = (EmpleadoMostrador) principal.usuarioActual;
			Properties pVehiculo = principal.cargaArchivos.cargarVehiculos();
			boolean verifyPlaca = verificador.verifyCarroInSede(pVehiculo, placa.getText(), empleado.getSede());
			if (verifyPlaca == false) {
				error = new ErrorDisplay("CARRO FUERA DE MI JURISDICCIÓN O NO ENCONTRADO");
			}
			else {
				String estado = empleado.mandarRevision(placa.getText());
				if (estado == null) {
					error = new ErrorDisplay("CARRO NO DISPONIBLE AUN");
				}
				else {
					notify = new Notificacion("CARRO SE MANDÓ A REVISIÓN CORRECTAMENTE");
				}
			}
			
		}
		principal.cambiarPanel(grito);
		
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
//        Color color1 = new Color(255, 165, 0);
        Color color1 = cuteYellow;
        Color color2 = Color.PINK;

        // Create a gradient paint
        GradientPaint gradientPaint = new GradientPaint(start, color1, end, color2);

        // Set the paint for the graphics context
        g2d.setPaint(gradientPaint);

        // Fill the background with the gradient paint
        g2d.fillRect(0, 0, width, height);
    }

}
