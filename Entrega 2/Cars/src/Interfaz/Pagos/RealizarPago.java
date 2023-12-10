package Interfaz.Pagos;

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

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Interfaz.Principal.ErrorDisplay;
import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Notificacion;
import Interfaz.Principal.Principal;
import RentadoraModelo.AdministradorGeneral;
import RentadoraModelo.EmpleadoInventario;

public class RealizarPago extends JPanel implements MetodosAuxiliares, ActionListener {
	
	Principal principal;
	
	private GridBagConstraints gbc;
	
	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	
	private JTextField nuevaTarifa;
	private JComboBox<String> seguros;
	private JButton consultar;
	private JButton confirmar;
	private JButton logOut;
	private JLabel titulo;
	private JLabel placa;
	
	private ErrorDisplay error;
	private Notificacion notify;
	
	public RealizarPago(Principal principal) {
		setLayout(new GridBagLayout());
		this.principal = principal;
		this.gbc = new GridBagConstraints();
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        
        
        placa = new JLabel("Placa del vehículo: ");
        subTitleText(placa);
        add(placa, gbc);
        gbc.gridy++;
        addSpace(YSpace);
        
        consultar = new JButton("Consultar");
        formatGradientButton(consultar, Color.PINK, pastelBlue);
        consultar.setActionCommand("Consultar");
        consultar.addActionListener(this);
        add(consultar,gbc);
        gbc.gridy++;
        
        
		nuevaTarifa = new JTextField(textFieldSize);
		nuevaTarifa.setEditable(false);
        add(nuevaTarifa,gbc);
        gbc.gridy++;
        addSpace(YSpace*3);
        
        JLabel instruccion = new JLabel("¿Poner en mantenimiento?");
		subTitleText(instruccion);
		
		add(instruccion, gbc);
		gbc.gridy++;

		String[] listaSeguros = {"Si", "No"};
		seguros = new JComboBox<>(listaSeguros);
		add(seguros, gbc);
		gbc.gridy++;
		
		addSpace(YSpace*4);
		confirmar = new JButton("Confirmar");
		formatGradientButton(confirmar, pastelGreen, Color.PINK); 
        confirmar.setActionCommand("confirmation");
        confirmar.addActionListener(this);
        add(confirmar,gbc);
        gbc.gridy++;
        
        addSpace(YSpace*2);
        logOut = new JButton("LOG OUT");
		formatGradientButton(logOut, pastelGreen, pastelBlue);        
        logOut.setActionCommand("login");
        logOut.addActionListener(this);
        add(logOut,gbc);
        gbc.gridy++;
        
		
		setVisible(true);
		
        
	}

	private void addSpace(int Yspace) {
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
		gbc.gridy++;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String grito = e.getActionCommand();
		
		if (grito.equals("login")) {
			principal.cambiarPanel(grito);
		}
		else {
			if (nuevaTarifa.getText().equals("Ingrese la placa del carro a revisar")) {
				error = new ErrorDisplay("PORFAVOR LLENE TODOS LOS CAMPOS");
			}
			else if (grito.equals("Consultar")){
				EmpleadoInventario empleado = (EmpleadoInventario) principal.usuarioActual;
				String placaRevisar = empleado.obtenerPlacaRevision();
				if (placaRevisar == null) {
					nuevaTarifa.setText("NO HAY POR AHORA");
				}
				else {
					nuevaTarifa.setText(placaRevisar);
				}
			}
			else if (grito.equals("confirmation")) {
				EmpleadoInventario empleado = (EmpleadoInventario) principal.usuarioActual;
				String placaRevisar = nuevaTarifa.getText();
				String seleccion = (String) seguros.getSelectedItem();
				if (seleccion.equals("Si")) {
					empleado.revisarVehículo(placaRevisar,true);
					notify = new Notificacion("SE MANDÓ A MANTENIMIENTO SATISFACTORIAMENTE");
				}
				else {
					empleado.actualizarEstadoVehículo(placaRevisar);
					notify = new Notificacion("EL VEHICULO ESTÁ LISTO PARA ANDAR");
						
				}
				nuevaTarifa.setText("");
				principal.cambiarPanel("empleadoInventario");
				}
				
			}
			
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
        Color color1 = Color.PINK;
        Color color2 = pastelBlue;

        // Create a gradient paint
        GradientPaint gradientPaint = new GradientPaint(start, color1, end, color2);

        // Set the paint for the graphics context
        g2d.setPaint(gradientPaint);

        // Fill the background with the gradient paint
        g2d.fillRect(0, 0, width, height);
    }
		
	}
	