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
import Interfaz.Principal.Principal;
import Interfaz.Principal.Verify;
import RentadoraModelo.OtrosConductores;

public class NuevosConductores extends JPanel implements MetodosAuxiliares, ActionListener {
	
	Principal principal;
	
	private JButton fotoDocumento;
	private GridBagConstraints gbc;
	private JButton confirmar;
	static final int textFieldSize = 20;
	static final int YSpace = 5;
	private ArrayList<JTextField> listaCampos = new ArrayList<JTextField>();
	private Verify verificador = new Verify();
	private JTextField foto;
	private ErrorDisplay error;
	
	public NuevosConductores(Principal principal) {
		
		this.principal = principal;
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		
		addCampos();
		
		fotoDocumento = new JButton("Foto Documento");
		fotoDocumento.setActionCommand("FOTO");
		fotoDocumento.addActionListener(this);
		formatButton(fotoDocumento);
		add(fotoDocumento,gbc);
		
		foto = new JTextField(textFieldSize);
        addSpace(YSpace);
        foto.setEnabled(false);
        add(foto,gbc);
		
		confirmar = new JButton("Confirmar");
		formatButton(confirmar);
		confirmar.setActionCommand("formalizarAlquiler");
		confirmar.addActionListener(this);
		add(confirmar,gbc);
	}
	
	private void addCampos() {
		
	    String[] campos = {"Nombre", "Número de licencia", "País de licencia",
	    		"Fecha de Vencimiento"};
	    
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
		
		if (grito.equals("FOTO")) {
			String fotoName = verificador.chooseFile();
			if (fotoName == null) {
				fotoName = "ERROR";
			}
			foto.setText(fotoName);
		}
		else {
			
			ArrayList<String> info = new ArrayList<String>();
			for (JTextField campitos: listaCampos) {
				info.add(campitos.getText());
			}
			info.add(foto.getText());
			boolean verifyLleno = verificador.verifyLleno(info);
		
			if (verifyLleno == true) {
				OtrosConductores conductorAdicional = new OtrosConductores(info.get(0),info.get(1),info.get(2),
									info.get(3),info.get(4));
				conductorAdicional.registrarLicencia(principal.idReservaActual);
				principal.cambiarPanel(grito);
			
			}
			else {
				error = new ErrorDisplay("PORFAVOR LLENE TODOS LOS CAMPOS!!");
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
        Color color2 = Color.MAGENTA;

        // Create a gradient paint
        GradientPaint gradientPaint = new GradientPaint(start, color1, end, color2);

        // Set the paint for the graphics context
        g2d.setPaint(gradientPaint);

        // Fill the background with the gradient paint
        g2d.fillRect(0, 0, width, height);
    }

}
