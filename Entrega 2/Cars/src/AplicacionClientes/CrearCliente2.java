package AplicacionClientes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Interfaz.Principal.ErrorDisplay;
import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;
import Interfaz.Principal.Verify;

public class CrearCliente2 extends JPanel implements MetodosAuxiliares,ActionListener{
	
	AplicacionClientes aplicacionClientes;
	private GridBagConstraints gbc;
	static final int textFieldSize = 20;
	static final int YSpace = 5;
	static final int XSpace = 300;
	private ArrayList<JTextField> listaCampos;
	private Verify verificador;
	private String fotoName;
	private String documentoName;
	private JTextField campoDocumento;
	private JTextField campoFoto;
	private ErrorDisplay error;
	private JPanel panelIzquierda;
	private JPanel panelDerecha;
	private JPanel panelTitulo;
	private JPanel panelCrearUsuario;
	private JPanel centerPanel;
	
	public CrearCliente2(AplicacionClientes aplicacionClientes) {		
		this.aplicacionClientes = aplicacionClientes;
		this.listaCampos = new ArrayList<JTextField>();
		this.verificador = new Verify();
		
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(40, 40, 40, 40)); //PARA PONER MARGENES
		centerPanel = newPanelTransparente();
		this.gbc = new GridBagConstraints();
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
		
		addTitulo();
		
		addCamposIzquierda();
				
		addCamposDerecha();
		
		add(centerPanel,BorderLayout.CENTER);
		
		addBotonCrearUsuario();
		
		setBackground(Color.ORANGE);
		
		requestFocus(null);
		
		setVisible(true);
	}
	
	private void addBotonCrearUsuario() {
		
		panelCrearUsuario = newPanelTransparente();
		panelCrearUsuario.setLayout(new GridBagLayout());
		gbc.gridx = 0;
        gbc.gridy = 0;
		
		addSpaceY(YSpace*4, panelCrearUsuario);
		JButton guardar = new JButton("¡Crear Usuario!");
        guardar.setActionCommand("GUARDAR");
        guardar.addActionListener(this);
        formatGradientButton(guardar,pastelPurple, cutePurple);
        panelCrearUsuario.add(guardar, gbc);
        gbc.gridy++;
        
        addSpaceY(YSpace, panelCrearUsuario);
		JButton volver = new JButton("Volver");
        volver.setActionCommand("login");
        volver.addActionListener(this);
        formatButton(volver, cutePurple);
        panelCrearUsuario.add(volver, gbc);
        
        add(panelCrearUsuario, BorderLayout.SOUTH);
		
	}

	private void addTitulo() {
		
		panelTitulo = newPanelTransparente();
		panelTitulo.setLayout(new GridBagLayout());
		gbc.gridx = 0;
        gbc.gridy = 0;
		
		JLabel instruccion = new JLabel("Rellene los siguientes campos");
		titleText(instruccion);
		
		gbc.gridwidth = 2;
		panelTitulo.add(instruccion,gbc);
		gbc.gridy++;
		gbc.gridwidth = 1;
		addSpaceY(YSpace*5, panelTitulo);
		
		add(panelTitulo, BorderLayout.NORTH);
		
	}

	private void addCamposIzquierda() {
		
		panelIzquierda = newPanelTransparente();
		panelIzquierda.setLayout(new GridBagLayout());
		gbc.gridx = 0;
        gbc.gridy = 0;
        
        JLabel instruccion = new JLabel("Datos Personales");
		subTitleText(instruccion);
		panelIzquierda.add(instruccion,gbc);
		gbc.gridy++;
        
        String[] campos = {"Usuario","Contraseña","Nombre completo", "Fecha de nacimiento (dd/mm/YYYY)", "Nacionalidad", 
                "No. Documento de identidad", "Celular", "Email"};
        
        for (String mensaje : campos) {
        	JTextField campo = new JTextField(textFieldSize);
        	listaCampos.add(campo);
            ponerTextitoGris(campo, mensaje);
            addSpaceY(YSpace, panelIzquierda);
            panelIzquierda.add(campo,gbc);
            gbc.gridy++;
		}
        
        addSpaceY(YSpace*2, panelIzquierda);
        JButton fotoDocumento = new JButton("Foto Documento");
        fotoDocumento.setActionCommand("DOCUMENTO");
        fotoDocumento.addActionListener(this);
        formatButton(fotoDocumento, pastelPurple);
        panelIzquierda.add(fotoDocumento,gbc);
        gbc.gridy++;

        
    	campoDocumento = new JTextField(textFieldSize);
    	campoDocumento.setEnabled(false);
        addSpaceY(YSpace, panelIzquierda);
        panelIzquierda.add(campoDocumento,gbc);
        gbc.gridy++;
        
        centerPanel.add(panelIzquierda, BorderLayout.WEST);
        
	}
	
	private void addCamposDerecha() {
		
		panelDerecha = newPanelTransparente();
		panelDerecha.setLayout(new GridBagLayout());
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
		
        JLabel instruccion = new JLabel("Tarjeta de Credito");
		subTitleText(instruccion);
		panelDerecha.add(instruccion,gbc);
		gbc.gridy++;
		
        String[] campos = {"Tipo de Tarjeta", "No. Tarjeta", 
        		"Fecha Vencimiento (mm/YYYY)", "Codigo de Seguridad"};
        
        for (String mensaje : campos) {
        	JTextField campo = new JTextField(textFieldSize);
        	listaCampos.add(campo);
            ponerTextitoGris(campo, mensaje);
            addSpaceY(YSpace,panelDerecha);
            panelDerecha.add(campo,gbc);
            gbc.gridy++;
		}
        
        addSpaceY(YSpace*2, panelDerecha);
        instruccion = new JLabel("Licencia de Conduccion");
		subTitleText(instruccion);
		addSpaceY(YSpace*2,panelDerecha);
		panelDerecha.add(instruccion,gbc);
		gbc.gridy++;
		
        campos = new String[]{"No. Licencia de Conducción", "País de expedición", "Fecha de Vencimiento"};
        
        for (String mensaje : campos) {
        	JTextField campo = new JTextField(textFieldSize);
            ponerTextitoGris(campo, mensaje);
            addSpaceY(YSpace,panelDerecha);
        	listaCampos.add(campo);
        	panelDerecha.add(campo,gbc);
            gbc.gridy++;
		}
        
        addSpaceY(YSpace*2, panelDerecha);
        JButton fotoDocumento = new JButton("Foto Licencia");
        fotoDocumento.setActionCommand("FOTO");
        fotoDocumento.addActionListener(this);
        formatButton(fotoDocumento, cutePurple);
        panelDerecha.add(fotoDocumento,gbc);
        gbc.gridy++;

        
        campoFoto = new JTextField(textFieldSize);
    	campoFoto.setEnabled(false);
        addSpaceY(YSpace,panelDerecha);
        panelDerecha.add(campoFoto,gbc);
        gbc.gridy++;
        
        centerPanel.add(panelDerecha, BorderLayout.EAST);

	}
	
	private void addSpaceY(int Yspace) {
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
		gbc.gridy++;
	}
	
	private void addSpaceY(int Yspace, JPanel panel) {
		panel.add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
		gbc.gridy++;
	}
	
	private void addSpaceX(int Xspace) {
		add(Box.createRigidArea(new Dimension(Xspace, 0)), gbc);
		gbc.gridy++;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String grito = e.getActionCommand();
		
		if (grito.equals("login")) {
			aplicacionClientes.cambiarPanel("login");
		}
		
		else if (grito.equals("GUARDAR")){
			
			ArrayList<String> listaInfo = new ArrayList<String>();
			for (JTextField field : listaCampos) {
				listaInfo.add(field.getText());
			}
			listaInfo.add(7,documentoName);
			listaInfo.add(fotoName);
			listaInfo.add("true");
			listaInfo.add("cliente");
			
			boolean verifyCampos = verificador.verifyLleno(listaInfo);
			if (verifyCampos == true) {
				Properties pLogin = aplicacionClientes.cargaArchivos.cargarLogin();
				boolean verifyUser = verificador.verifyExistUser(pLogin, listaInfo.get(0));
				if (verifyUser == true) {
					aplicacionClientes.cargaArchivos.guardarNuevoUsuario(listaInfo);
					aplicacionClientes.cambiarPanel("login");
				}
				else {
					error = new ErrorDisplay("USUARIO YA EXISTENTE, INGRESE OTRO USUARIO");
				}
			}
			else {
				error = new ErrorDisplay("LLENE TODO LOS CAMPOS CORRECTAMENTE");
			}
			
		}
		else if (grito.equals("FOTO")) {
			fotoName = verificador.chooseFile();
			if (fotoName == null) {
				fotoName = "ERROR";
			}
			campoFoto.setText(fotoName);
		}
		else if (grito.equals("DOCUMENTO")) {
			documentoName = verificador.chooseFile();
			if (documentoName == null) {
				documentoName = "ERROR";
			}
			campoDocumento.setText(documentoName);
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
