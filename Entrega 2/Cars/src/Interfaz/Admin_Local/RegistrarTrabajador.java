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

public class RegistrarTrabajador extends JPanel implements MetodosAuxiliares, ActionListener  {

	Principal principal;
	
	private GridBagConstraints gbc;
	
	private JButton confirmar;
	private JTextField campoFoto;
	private JComboBox tipoEmpleado;
	private JComboBox sedes;
	ArrayList<String> campos;
	ArrayList<JTextField> listaCampos;
	private Verify verificador;
	private ErrorDisplay error;
	private Notificacion notify;
	private JButton volver;

	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	
	public RegistrarTrabajador(Principal principal) {
		this.verificador = new Verify();
		this.campos = new ArrayList<String>();
		this.listaCampos = new ArrayList<JTextField>();
		this.principal = principal;
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        
        addCampos();
		
		addDesplegables();
        
		addSpace(YSpace*4);
		confirmar = new JButton("Confirmar");
		confirmar.setActionCommand("consolaAdminLocal");
		confirmar.addActionListener(this);
		formatGradientButton(confirmar, pastelGreen, pastelBlue);
		add(confirmar,gbc);
		
		addSpace(YSpace);
		volver = new JButton("VOLVER");
		volver.setActionCommand("VOLVER");
		volver.addActionListener(this);
		formatGradientButton(volver, pastelGreen, pastelBlue);
		add(volver, gbc);
	}
	
	private void addDesplegables() {
		
		ArrayList<String> todasSedes = principal.cargaArchivos.cargarSedes();
		sedes = new JComboBox<>();
		for (String sede: todasSedes) {			
        sedes.addItem(sede);
		}
		
        addSpace(YSpace);
		add(sedes,gbc);
        
        tipoEmpleado = new JComboBox<>();
        tipoEmpleado.addItem("inventario");
        tipoEmpleado.addItem("mostrador");
        addSpace(YSpace);
        add(tipoEmpleado,gbc);
		
	}

	private void addCampos() {
		
		JLabel instruccion = new JLabel("Datos Personales");
		subTitleText(instruccion);
		add(instruccion,gbc);
        
		campos.add("Usuario");
		campos.add("Contraseña");
		campos.add("Nombre completo");
		campos.add("Fecha de nacimiento (dd/mm/YYYY)");
		campos.add("Nacionalidad");
		campos.add("No. Documento de identidad");
		
        for (String mensaje : campos) {
        	JTextField campo = new JTextField(textFieldSize);
            ponerTextitoGris(campo, mensaje);
            addSpace(YSpace);
            listaCampos.add(campo);
            add(campo,gbc);
		}
        
        addSpace(YSpace*2);
        JButton fotoDocumento = new JButton("Foto Documento");
        fotoDocumento.setActionCommand("FOTO");
        fotoDocumento.addActionListener(this);
        formatButton(fotoDocumento, cutePurple);
        add(fotoDocumento,gbc);
        
        campoFoto = new JTextField(textFieldSize);
        campoFoto.setEnabled(false);
        addSpace(YSpace);
        listaCampos.add(campoFoto);
        add(campoFoto,gbc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String grito = e.getActionCommand();
		
		if (grito.equals("VOLVER")) {
			principal.cambiarPanel("consolaAdminLocal");
		}
		
		else if (grito.equals("FOTO")) {
			String fotoName = verificador.chooseFile();
			if (fotoName == null) {
				fotoName = "ERROR";
			}
			campoFoto.setText(fotoName);
		}
		
		else {
			ArrayList<String> listaInfo = new ArrayList<String>();
			for (JTextField field : listaCampos) {
				if (campos.contains(field.getText())) {
					listaInfo.add("");
				}
				else {
					listaInfo.add(field.getText());
				}
			}
			
			String rol = (String) tipoEmpleado.getSelectedItem();
			String sede = (String) sedes.getSelectedItem();
			
			listaInfo.add(sede);
			listaInfo.add("true");
			listaInfo.add(rol);
			
			boolean verifyCampos = verificador.verifyLleno(listaInfo);
			if (verifyCampos == true) {
				Properties pLogin = principal.cargaArchivos.cargarLogin();
				boolean verifyUser = verificador.verifyExistUser(pLogin, listaInfo.get(0));
				if (verifyUser == true) {
					principal.cargaArchivos.guardarNuevoUsuario(listaInfo);
					notify = new Notificacion("GUARDADO EXITOSAMENTE");
				}
				else {
					error = new ErrorDisplay("USUARIO YA EXISTENTE, INGRESE OTRO USUARIO");
				}
			}
			else {
				error = new ErrorDisplay("LLENE TODO LOS CAMPOS CORRECTAMENTE");
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
