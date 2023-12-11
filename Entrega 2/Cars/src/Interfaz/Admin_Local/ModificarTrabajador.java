package Interfaz.Admin_Local;

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
import RentadoraModelo.AdministradorLocal;

public class ModificarTrabajador extends JPanel implements MetodosAuxiliares, ActionListener {
	
	Principal principal;
	
	private GridBagConstraints gbc;
	
	static final int textFieldSize = 20;
	private JComboBox<String> aspectoTrabajador;
	private JTextField  nuevoValor;
	private JTextField  nombreTrabajador;
	static final int YSpace = 5;
	private ErrorDisplay error;
	private Notificacion notify;
	private Verify verificador;
	private JButton volver;

	public ModificarTrabajador(Principal principal) {
		
		this.verificador = new Verify();
		this.principal = principal;
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        
        JLabel trabajadorLabel = new JLabel("Trabajador a modificar");
		subTitleText(trabajadorLabel);
		
		nombreTrabajador = new JTextField(textFieldSize);
		ponerTextitoGris(nombreTrabajador, "Usuario del Trabajador");
		
		JLabel aspectoLabel = new JLabel("Aspecto a Modificar");
		subTitleText(aspectoLabel);
		
		String[] campos = {"Nombre","FechaNacimiento dd/mm/YYYY","Nacionalidad","Documento de Identidad",
				"Imagen", "Sede en la que trabaja","Rol del empleado"};
	
		aspectoTrabajador = new JComboBox<String>();
		for (String valor: campos) {
			aspectoTrabajador.addItem(valor);
		}
		
		nuevoValor = new JTextField(textFieldSize);
		ponerTextitoGris(nuevoValor, "Nuevo Valor");
		
		JButton confirmar = new JButton("Confirmar");
		confirmar.setActionCommand("consolaAdminLocal");
		confirmar.addActionListener(this);
		formatGradientButton(confirmar, pastelGreen, pastelBlue);
		
		add(trabajadorLabel,gbc);
		
		add(nombreTrabajador,gbc);
		
		addSpace(YSpace*3);
		
		add(aspectoLabel,gbc);
		
		add(aspectoTrabajador,gbc);
		
		addSpace(YSpace);
		
		add(nuevoValor,gbc);
		
		addSpace(YSpace*5);
		
		add(confirmar,gbc);
		
		addSpace(YSpace*2);
		volver = new JButton("VOLVER");
		volver.setActionCommand("VOLVER");
		volver.addActionListener(this);
		formatGradientButton(volver, pastelGreen, pastelBlue);
		add(volver, gbc);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String grito = e.getActionCommand();
		
		if (grito.equals("VOLVER")) {
			principal.cambiarPanel("consolaAdminLocal");
		}
		
		else if (nuevoValor.getText().equals("Nuevo Valor") || nombreTrabajador.getText().equals("Usuario del Trabajador")) {
			error = new ErrorDisplay("PORFAVOR LLENE TODOS LOS CAMPOS");
		}
		else {
			AdministradorLocal admin = (AdministradorLocal) principal.usuarioActual;
			Properties pLogin = principal.cargaArchivos.cargarLogin();
			boolean verifyMismaSede = verificador.verifyEmpleadoSede(pLogin, nombreTrabajador.getText(), admin.getSede());
			if (verifyMismaSede == true) {
				admin.modificarInformacionTrabajador(nombreTrabajador.getText(), (String) aspectoTrabajador.getSelectedItem()
						,nuevoValor.getText(), admin.getSede());
				notify = new Notificacion("CAMBIOS REALIZADOS CORRECTAMENTE");
				principal.cambiarPanel("consolaAdminLocal");
			}
			else {
				error = new ErrorDisplay("NO ENCONTRE A ESE EMPLEADO DENTRO DE MI JURISDICCIÓN");
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
        Color color1 = cutePurple;
        Color color2 = Color.PINK;

        // Create a gradient paint
        GradientPaint gradientPaint = new GradientPaint(start, color1, end, color2);

        // Set the paint for the graphics context
        g2d.setPaint(gradientPaint);

        // Fill the background with the gradient paint
        g2d.fillRect(0, 0, width, height);
    }
	
}
