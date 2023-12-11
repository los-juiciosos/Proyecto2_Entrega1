package Interfaz.Admin_General;

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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Interfaz.Principal.ErrorDisplay;
import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Notificacion;
import Interfaz.Principal.Principal;
import Interfaz.Principal.Verify;
import RentadoraModelo.AdministradorGeneral;


public class RegistrarVehiculo extends JPanel implements MetodosAuxiliares, ActionListener {

Principal principal;
	
	private GridBagConstraints gbc;
	
	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	
	private ArrayList<JTextField> listaCampos;
	private JButton confirmar;
	private JButton volver;
	private ArrayList<String> campos;
	private ErrorDisplay error;
	private Notificacion notify;
	private Verify verificador;
	
	public RegistrarVehiculo(Principal principal) {
		
		this.principal = principal;
		this.verificador = new Verify();
		setLayout(new GridBagLayout());
		this.campos = new ArrayList<String>();
		this.listaCampos = new ArrayList<JTextField>();
		this.gbc = new GridBagConstraints();
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
		
        
		JLabel instruccion = new JLabel("Registrar Vehículo");
		titleText(instruccion);
		
		add(instruccion,gbc);
		gbc.gridy++;
		
		addCampos();
		
		addSpace(YSpace*2);
		confirmar = new JButton("Confirmar");
        formatButton(confirmar);
        confirmar.setActionCommand("CONFIRMAR");
        confirmar.addActionListener(this);
        add(confirmar,gbc);
        gbc.gridy++;
        
        volver = new JButton("Volver");
        formatButton(volver);
        volver.setActionCommand("consolaAdminGeneral");
        volver.addActionListener(this);
        add(volver,gbc);
        gbc.gridy++;
		

		setVisible(true);
	}
	
	private void addCampos() {
		
		campos.add("Placa");
		campos.add("Marca");
		campos.add("Modelo");
		campos.add("Color");
		campos.add("Transimisión");
		campos.add("Combustible");
		campos.add("Capacidad de Pasajeros");
		campos.add("Sede");
		campos.add("Categoría");
        
        
        for (String mensaje : campos) {
        	JTextField campo = new JTextField(textFieldSize);
            ponerTextitoGris(campo, mensaje);
            addSpace(YSpace);
            add(campo,gbc);
            gbc.gridy++;
            listaCampos.add(campo);
		}
        
        
	}
	
	
	
	private void addSpace(int Yspace) {
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
		gbc.gridy++;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String grito = e.getActionCommand();
		
		if (grito.equals("consolaAdminGeneral")){
			principal.cambiarPanel(grito);
		}
		else {
			ArrayList<String> infoCampos = new ArrayList<String>();
			for (JTextField campitos: listaCampos) {
				if (campos.contains(campitos.getText())) {
					infoCampos.add("");
				}
				else {
					infoCampos.add(campitos.getText());
				}
			}
			
			boolean verifyLleno = verificador.verifyLleno(infoCampos);
			if (verifyLleno == true) {
				AdministradorGeneral admin = (AdministradorGeneral) principal.usuarioActual;
				admin.registrarNuevoVehiculo(infoCampos.get(0), infoCampos.get(1), infoCampos.get(2), 
			infoCampos.get(3), infoCampos.get(4), infoCampos.get(5), infoCampos.get(6), infoCampos.get(7), infoCampos.get(8));
			notify = new Notificacion("GUARDADO CON EXITO EL NUEVO CARRO!!!!!!!!!!!!");
			principal.cambiarPanel("consolaAdminGeneral");
			}
			else {
				error = new ErrorDisplay("PORFAVOR LLENAR TODOS LOS CAMPOS");
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