package Interfaz.Admin_General;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Interfaz.Principal.ErrorDisplay;
import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Notificacion;
import Interfaz.Principal.Principal;
import Interfaz.Principal.Verify;
import RentadoraModelo.AdministradorGeneral;


public class ConsolaAdminGeneral extends JPanel implements MetodosAuxiliares, ActionListener {

	private Principal principal;
	

	static final int textFieldSize = 20;
	static final int YSpace = 5;
	private JTextField bajaVehiculo;
	private JButton darBaja;
	private JButton generarLog;
	private JButton campoNuevoVehiculo;
	private JButton campoModificarSeguro;
	private JButton campoModificarSede;
	private JButton campoRevisarGraficoSede;
	private JButton campoModificarCategoria;
	private JButton campoRevisarMantenimiento;
	
	private RegistrarVehiculo registrarVehiculo;
	private ModificarSeguros  modificarSeguros;
	private ModificarInformacionSede modificarInformacionSede;
	private ModificarTarifaCategoria modificarTarifaCategoria;
	private RevisarMantenimiento revisarMantenimiento;
	private RevisarGrafico revisarGrafico; 
	
	private ErrorDisplay error;
	private Notificacion notify;
	private Verify verificador;
	private JButton logOut;
	
	private GridBagConstraints gbc;
	
	public ConsolaAdminGeneral(Principal principal) {
		this.verificador = new Verify();
		this.principal = principal;
		this.registrarVehiculo = new RegistrarVehiculo(principal);
		this.modificarSeguros = new ModificarSeguros(principal);
		this.modificarInformacionSede = new ModificarInformacionSede(principal);
		this.modificarTarifaCategoria = new ModificarTarifaCategoria(principal);
		this.revisarMantenimiento = new RevisarMantenimiento(principal);
		this.revisarGrafico = new RevisarGrafico(principal);
		
		principal.addPanel(registrarVehiculo, "RegistrarVehiculo");
		principal.addPanel(modificarSeguros, "ModificarSeguros");
		principal.addPanel(modificarInformacionSede, "ModificarInformacionSede");
		principal.addPanel(modificarTarifaCategoria, "ModificarTarifaCategoria");
		principal.addPanel(revisarMantenimiento, "RevisarMantenimiento");
		principal.addPanel(revisarGrafico, "revisarGrafico");
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
		
        JLabel info = new JLabel("Eliga la opción con la que desea trabajar");
		titleText(info);
        
		add(info, gbc);
		gbc.gridy++;
		
		addCamposBotones();
		
		bajaVehiculo = new JTextField(textFieldSize);
		ponerTextitoGris(bajaVehiculo, "Inserte una Placa");
		add(bajaVehiculo, gbc);
		gbc.gridy++;
		
		darBaja = new JButton("Dar de Baja");
		formatButton(darBaja);
		darBaja .setActionCommand("BAJA");
		darBaja .addActionListener(this);
		addSpace(YSpace);
		add(darBaja , gbc);
		gbc.gridy++;
		
		generarLog = new JButton("Generar Log");
		formatButton(generarLog);
		generarLog.setActionCommand("LOG");
		generarLog.addActionListener(this);
		addSpace(YSpace);
		add(generarLog, gbc);
		gbc.gridy++;
		
		logOut = new JButton("LOG OUT");
        formatButton(logOut);
        logOut.setActionCommand("login");
        logOut.addActionListener(this);
        add(logOut,gbc);
		
		
		setVisible(true);
		
        
	}
	
	
	
	private void addCamposBotones() {
		

		String[] campos = {"Registrar nuevo vehículo", "Modificar seguros", "Modificar información sede",
				"Revisar Gráfico por Sede", "Modificar Tarifa por Categoría", "Revisar Mantenimiento"};
		
			campoNuevoVehiculo = new JButton(campos[0]);
			formatButton(campoNuevoVehiculo);
			addSpace(YSpace);
			add(campoNuevoVehiculo, gbc);
			campoNuevoVehiculo.setActionCommand("RegistrarVehiculo");
			campoNuevoVehiculo.addActionListener(this);
			gbc.gridy++;
			
			campoModificarSeguro = new JButton(campos[1]);
			formatButton(campoModificarSeguro);
			campoModificarSeguro.setActionCommand("ModificarSeguros");
			campoModificarSeguro.addActionListener(this);
			addSpace(YSpace);
			add(campoModificarSeguro, gbc);
			gbc.gridy++;
			
			campoModificarSede = new JButton(campos[2]);
			formatButton(campoModificarSede);
			campoModificarSede.setActionCommand("ModificarInformacionSede");
			campoModificarSede.addActionListener(this);
			addSpace(YSpace);
			add(campoModificarSede, gbc);
			gbc.gridy++;
			
			campoRevisarGraficoSede = new JButton(campos[3]);
			formatButton(campoRevisarGraficoSede);
			campoRevisarGraficoSede.setActionCommand("revisarGrafico");
			campoRevisarGraficoSede.addActionListener(this);
			addSpace(YSpace);
			add(campoRevisarGraficoSede, gbc);
			gbc.gridy++;
			
			campoModificarCategoria = new JButton(campos[4]);
			formatButton(campoModificarCategoria);
			campoModificarCategoria.setActionCommand("ModificarTarifaCategoria");
			campoModificarCategoria.addActionListener(this);
			addSpace(YSpace);
			add(campoModificarCategoria, gbc);
			gbc.gridy++;
			
			campoRevisarMantenimiento = new JButton(campos[5]);
			formatButton(campoRevisarMantenimiento);
			campoRevisarMantenimiento.setActionCommand("RevisarMantenimiento");
			campoRevisarMantenimiento.addActionListener(this);
			addSpace(YSpace);
			add(campoRevisarMantenimiento, gbc);
			gbc.gridy++;
			
			
		
	}
	

		
	
	
private void addCamposTexto() {

        
        String[] campos = {"Dar de baja vehículo Placa: ", "Generar Log de un Vehículo Placa: "};
        
        
        for (String mensaje : campos) {
			JTextField campo = new JTextField(textFieldSize);
			ponerTextitoGris(campo, mensaje);
			addSpace(YSpace);
			add(campo, gbc);
			gbc.gridy++;
			
		}
		
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
		
		else if (grito.equals("BAJA")) {
			Properties pVehiculo = principal.cargaArchivos.cargarVehiculos();
			if (verificador.verifyExistPlaca(pVehiculo, bajaVehiculo.getText()) == false) {
				error = new ErrorDisplay("PORFAVOR INGRESE UNA PLACA VALIDA");
			}
			else {
				AdministradorGeneral admin = (AdministradorGeneral) principal.usuarioActual;
				admin.darBajaVehiculo(bajaVehiculo.getText());
				notify = new Notificacion("SE DIO DE BAJA CORRECTAMENTE");
			}
		}
		else if(grito.equals("LOG")) {
			Properties pVehiculo = principal.cargaArchivos.cargarVehiculos();
			if (verificador.verifyExistPlaca(pVehiculo, bajaVehiculo.getText()) == false) {
				error = new ErrorDisplay("PORFAVOR INGRESE UNA PLACA VALIDA");
			}
			else {
				AdministradorGeneral admin = (AdministradorGeneral) principal.usuarioActual;
				String logaso = admin.mostrarLog(bajaVehiculo.getText());
				notify = new Notificacion(logaso);
			}
		}
		else {
		principal.cambiarPanel(grito);
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