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

import Interfaz.Pagos.RealizarPago;
import Interfaz.Principal.ErrorDisplay;
import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Notificacion;
import Interfaz.Principal.Principal;
import Interfaz.Principal.Verify;
import RentadoraModelo.Categoria;
import RentadoraModelo.Cliente;
import RentadoraModelo.Pagos;
import RentadoraModelo.Reserva;
import RentadoraModelo.Sede;

public class HacerReserva extends JPanel implements MetodosAuxiliares, ActionListener  {

	Principal principal;
	private JComboBox<String> sedes;
	private JComboBox<String> categorias;
	private JComboBox<String> metodoPago;
	private JButton confirmar;
	private JButton pagar;
	private JLabel estado;
	private GridBagConstraints gbc;
	static final int textFieldSize = 20;
	static final int YSpace = 5;
	private ArrayList<JTextField> listaCampos;
	private Verify verificador;
	private ErrorDisplay error;
	private Notificacion notify;
	private JButton volver;
	ArrayList<String> campos = new ArrayList<String>();
	
	public HacerReserva(Principal principal) {
		this.principal = principal;
		this.listaCampos = new ArrayList<JTextField>();
		this.verificador = new Verify();
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		
		JLabel instruccion = new JLabel("Rellena los campos:");
		subTitleText(instruccion);
		add(instruccion, gbc);
		
		addCampos();
		
		addBoxes();
		addSpace(YSpace*2);
		
		pagar = new JButton("PAGAR");
		pagar.setActionCommand("PAGAR");
		pagar.addActionListener(this);
		formatButton(pagar);
		add(pagar, gbc);
		
		addSpace(YSpace*2);
		estado = new JLabel("Pago? ...... "+principal.estadoTransaccion);
		subTitleText(estado);
		add(estado, gbc);
		
		
		addSpace(YSpace*2);
		confirmar = new JButton("Confirmar");
		formatButton(confirmar);
		confirmar.setActionCommand("CONFIRMAR");
		confirmar.addActionListener(this);
		add(confirmar,gbc);
		
		volver = new JButton("VOLVER");
		volver.setActionCommand("VOLVER");
		volver.addActionListener(this);
		formatButton(volver);
		add(volver, gbc);
		

	}
	
	private void addBoxes() {
		
		ArrayList<String> todasSedes = principal.cargaArchivos.cargarSedes();
		sedes = new JComboBox<>();
		for (String sede: todasSedes) {			
        sedes.addItem(sede);
		}
		add(sedes,gbc);
		addSpace(YSpace);
		
		ArrayList<String> todasCategorias = principal.cargaArchivos.cargarCategoria();
        
		categorias = new JComboBox<>();
		for (String categoria: todasCategorias) {
        categorias.addItem(categoria);
		}
		add(categorias,gbc);
		addSpace(YSpace);
		
		ArrayList<String> todosPagos = principal.cargaArchivos.cargarPagos();
        
		metodoPago = new JComboBox<>();
		for (String paguito: todosPagos) {
        metodoPago.addItem(paguito);
		}
		add(metodoPago,gbc);
		addSpace(YSpace);
		
	}

	private void addCampos() {
		campos.add("Día recogida (dd/mm/YYYY)");
		campos.add("Hora Recogida (HH:MM)");
		campos.add("Día entrega (dd/mm/YYYY)");
		campos.add("Hora Entrega (HH:MM)");

        
        for (String mensaje : campos) {
        	JTextField campo = new JTextField(textFieldSize);
            ponerTextitoGris(campo, mensaje);
            addSpace(YSpace);
            listaCampos.add(campo);
            add(campo,gbc);
		}
        
        addSpace(YSpace);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String grito = e.getActionCommand();
				
		if (grito.equals("VOLVER")) {
			principal.cambiarPanel("menuCliente");
		}
		else if (grito.equals("CONFIRMAR") || grito.equals("PAGAR")) {	
			
			String sede = (String) sedes.getSelectedItem();
			String categoria = (String) categorias.getSelectedItem();
			
			ArrayList<String> info = new ArrayList<String>();
			for (JTextField textCampo: listaCampos) {
				String texto = (String) textCampo.getText();
				if (campos.contains(texto)) {
					info.add("");
				}
				else {
					info.add(texto);
				}
			}
			boolean verifyLleno = verificador.verifyLleno(info);
			if (verifyLleno == true) {
			Properties pLogin = principal.cargaArchivos.cargarLogin();
			boolean verifyTarjeta = verificador.verifyTarjetaBloqueada(pLogin, principal.usernameActual);

			boolean verify = verificador.verifyFechaValida(principal.sedePresente,info.get(1),info.get(3));
				
			if (verify == true) {
				Cliente cliente = (Cliente) principal.usuarioActual;
				
				Categoria categoriaEscogida = new Categoria(categoria);
				categoriaEscogida.setMejorCategoria(categoriaEscogida);
				
				Reserva reservaSeleccionado = cliente.iniciarReserva(info.get(0), info.get(1),info.get(2),info.get(3),
										principal.sedePresente, categoriaEscogida,sede);
				
				if (reservaSeleccionado.getCarroEscogido() == null) {
					error = new ErrorDisplay("NO SE ENCONTRO VEHICULOS ACORDES A SU BUSQUEDA,INTENTE MÁS TARDE");
				}
				else {
					
				if (grito.equals("PAGAR")) {
					reservaSeleccionado.setTarifa();
					Double cobro30 = 0.3*reservaSeleccionado.getTarifa().getPrecio();
					Principal.precioActual = cobro30;
					Principal.porcentaje = "30";
					Principal.estadoTarjeta = verifyTarjeta;
					Principal.devolverPago = "hacerReserva";
					Principal.pasarelaActual = (String) metodoPago.getSelectedItem();
					RealizarPago pago = new RealizarPago();
					
					estado.setText("Pago? ...... "+principal.estadoTransaccion);
					
				}
				else if (Principal.estadoTransaccion == true) {
					reservaSeleccionado.setTarifa();
					principal.estadoTransaccion = false;
					estado.setText("Pago? ...... "+principal.estadoTransaccion);
					principal.cargaArchivos.cargarTarReserva(reservaSeleccionado, cliente);
					String mensaje = "";
					mensaje += "=======================CARACTERISTICAS RESERVA ============================\n";
					mensaje += "El carro que le fue asignado tiene las siguientes propiedades: \n";
					mensaje += "Si no coincide con su categoria es que se agotaron, y le dimos una superior \n";
					mensaje +="=========================================================================== \n";
					mensaje +="placa: " + reservaSeleccionado.getCarroEscogido().getPlaca()+"\n";
					mensaje +="marca: " + reservaSeleccionado.getCarroEscogido().getMarca()+"\n";
					mensaje +="modelo: " + reservaSeleccionado.getCarroEscogido().getModelo()+"\n";
					mensaje +="color: " + reservaSeleccionado.getCarroEscogido().getColor()+"\n";
					mensaje +="transmision: " + reservaSeleccionado.getCarroEscogido().getTipoDeTransmision()+"\n";
					mensaje +="combustible: " + reservaSeleccionado.getCarroEscogido().getCombustible()+"\n";
					mensaje +="categoria: " + reservaSeleccionado.getCarroEscogido().getCategoria().getNombre()+"\n";
					mensaje +="=========================================================================== \n";
					mensaje +="Ya le cobraremos el 30% de la tarifa dada\n";
					mensaje +="Transaccion Hecha Satisfactoriamente \n";
					mensaje +="Reserva Guardada Satisfactoriamente \n";
					mensaje +="=========================================================================== \n";
					mensaje +="EL ID DE SU RESERVA ES: " + reservaSeleccionado.getId()+cliente.getNombre()+"\n";
					mensaje +="RECUERDE EL ID PORQUE SINO NO PODRA FINALIZAR EL ALQUILER \n";
					mensaje +="===========================================================================";
					notify = new Notificacion(mensaje);
				
				
				principal.cambiarPanel("menuCliente");
				     }
				else if (Principal.estadoTransaccion == false) {
					error = new ErrorDisplay("NO HA PAGADO!!!!!!!!!!!!!!!!");
				}
				
				  }
				}
				else {
					error = new ErrorDisplay("LA HORA NO ESTÁ DENTRO DEL HORARIO DE ATENCIÓN, PORFAVOR ESCOGERLA DE NUEVO");
				}
			
			}
			else {
				error = new ErrorDisplay("LLENE TODO LOS CAMPOS");
			}
			
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
