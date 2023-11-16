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
import java.util.Properties;

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
import Interfaz.Principal.Verify;
import RentadoraModelo.Alquiler;

public class FormalizarAlquiler extends JPanel implements MetodosAuxiliares, ActionListener{
	
	private int cantidadConductores;
	Principal principal;
	private JTextField idReserva;
	private JLabel deseaAñadirConductores;
	private JButton nuevosConductores;
	private JLabel deseaCambiarReserva;
	private JButton cambiarReserva;
	private JComboBox<String> seguros;
	private JButton confirmar;
	private GridBagConstraints gbc;
	static final int textFieldSize = 20;
	static final int YSpace = 5;
	private ErrorDisplay error;
	private Verify verificador = new Verify();
	private Notificacion notify;
	private JButton volver;
	
	public FormalizarAlquiler(Principal principal) {
		
		this.principal = principal;
		this.cantidadConductores = 0;
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridx = 0;
        gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		
		JLabel rellenar = new JLabel("Rellena el siguiente campo:");
		subTitleText(rellenar);
		
		JLabel escogeSeguro = new JLabel("Selecciona un seguro");
		subTitleText(escogeSeguro);
		
		crearAtributos();
		
		add(rellenar, gbc);
		gbc.gridy++;
		addSpace(YSpace);
		
		add(idReserva, gbc);
		gbc.gridy++;
		addSpace(YSpace*3);

		add(deseaAñadirConductores, gbc);
		gbc.gridx = 1;
		
		add(nuevosConductores, gbc);
		gbc.gridy++;
		gbc.gridx = 0;
		
		add(deseaCambiarReserva,gbc);
		gbc.gridx = 1;
		
		add(cambiarReserva, gbc);
		gbc.gridy++;
		gbc.gridx = 0;
		
		addSpace(YSpace*3);
		add(escogeSeguro,gbc);
		gbc.gridy++;
		
        add(seguros,gbc);
        gbc.gridy++;
        
        addSpace(YSpace*4);
        add(confirmar, gbc);
        gbc.gridy++;
        
        volver = new JButton("VOLVER");
		volver.setActionCommand("VOLVER");
		volver.addActionListener(this);
		formatButton(volver);
		add(volver, gbc);
        
        addSpace(YSpace);

	}
	
	private void crearAtributos() {
		
		idReserva = new JTextField(textFieldSize);
        ponerTextitoGris(idReserva, "ID de reserva");
        
        deseaAñadirConductores = new JLabel("Desea añadir nuevos conductores? ");
        subTitleText(deseaAñadirConductores);
        
        deseaCambiarReserva = new JLabel("Desea cambiar datos de su reserva? ");
        subTitleText(deseaCambiarReserva);
        
        nuevosConductores = new JButton("→");
		formatLeftArrowButton(nuevosConductores);
		nuevosConductores.setActionCommand("nuevosConductores");
		nuevosConductores.addActionListener(this);
		
		cambiarReserva = new JButton("→");
		formatLeftArrowButton(cambiarReserva);
		cambiarReserva.setActionCommand("cambiarReserva");
		cambiarReserva.addActionListener(this);
		
		seguros = new JComboBox<>();
		for (String seguro: principal.cargaArchivos.cargarListaSeguros()) {
			seguros.addItem(seguro);
		}
		
		confirmar = new JButton("confirmar");
		formatButton(confirmar);
		confirmar.setActionCommand("menuCliente");
		confirmar.addActionListener(this);
		
	}

	private void addSpace(int Yspace) {
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
		gbc.gridy++;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Properties pReserva = principal.cargaArchivos.cargarReserva();
		boolean verifyIdValida = verificador.verifyIdReserva(pReserva, idReserva.getText(),principal.sedeActual);
		
		String grito = e.getActionCommand();
		
		if (grito.equals("VOLVER")) {
			principal.cambiarPanel("menuCliente");
		}
		
		else if(verifyIdValida == false) {
			error = new ErrorDisplay("NO EXISTE UNA RESERVA A ESE NOMBRE EN ESTA SEDE");
		}
		else {
			principal.idReservaActual = idReserva.getText();

			
			if (grito.equals("menuCliente")) {
				String seguro =  (String) seguros.getSelectedItem();
				ArrayList<Object> listaAlquiler = principal.cargaArchivos.cargarAlquiler(principal.idReservaActual,seguro,cantidadConductores);
				Alquiler alquilerActual = (Alquiler) listaAlquiler.get(1);
				
				alquilerActual.formalizarAlquiler((String[])listaAlquiler.get(0),idReserva.getText(),principal.usernameActual);
				
				String mensaje = "";
				mensaje += "Precio Final a Pagar: " + alquilerActual.getPrecioTarifa()+"\n";
				mensaje += "Pagando restante del 30% abonado... \n";
				mensaje += "Pago realizado con exito, su tarjeta quedo deshabilitada, hasta que devuelva el vehiculo \n";
				mensaje += "PORFAVOR RECUERDE LA PLACA DE SU CARRO PARA ENTREGARLO \n";
				mensaje += "Gracias por confiar en nosotros nos vemos pronto!!!! \n";
				
				notify = new Notificacion(mensaje);
				principal.cambiarPanel(grito);
			}
			else if (grito.equals("nuevosConductores")){
				cantidadConductores += 1;
				principal.cambiarPanel(grito);				
			}
			else if (grito.equals("cambiarReserva")) {
				principal.cambiarPanel(grito);
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
