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

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Interfaz.Principal.ErrorDisplay;
import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Principal;

public class CambiarReserva extends JPanel implements MetodosAuxiliares, ActionListener {

	Principal principal;
	
	private GridBagConstraints gbc;
	private JLabel instruccion;
	private JComboBox<String> categorias;
	private JTextField nuevoValor;
	private JButton confirmar;
	static final int textFieldSize = 20;
	static final int YSpace = 5;
	private ErrorDisplay error;
	private JButton volver;
	
	public CambiarReserva(Principal principal) {
		
		this.principal = principal;
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.WEST;
		
		crearAtributos();
		
		add(instruccion, gbc);
		addSpace(YSpace);
		
		add(categorias, gbc);
		addSpace(YSpace);
		
		add(nuevoValor, gbc);
		addSpace(YSpace*3);
		
		add(confirmar,gbc);
		addSpace(YSpace);
		
		volver = new JButton("Volver");
        formatButton(volver);
        volver.setActionCommand("VOLVER");
        volver.addActionListener(this);
        add(volver,gbc);
        gbc.gridy++;
		
	}

	private void crearAtributos() {
		
		instruccion = new JLabel("¿Que categoria deseas editar?");
		subTitleText(instruccion);
		
		categorias = new JComboBox<>();
		categorias.addItem("Fecha Devolucion");
		categorias.addItem("Hora Devolucion");
		categorias.addItem("Sede Entrega");	
		
		nuevoValor = new JTextField(textFieldSize);
		ponerTextitoGris(nuevoValor, "Nuevo Valor");
		
		confirmar = new JButton("Confirmar");
		formatButton(confirmar);
		confirmar.setActionCommand("formalizarAlquiler");
		confirmar.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String grito = e.getActionCommand();
		
		if (grito.equals("VOLVER")) {
			principal.cambiarPanel("formalizarAlquiler");
		}
		
		else if (nuevoValor.getText().equals("Nuevo Valor")){
			error = new ErrorDisplay("PORFAVOR LLENE TODOS LOS CAMPOS");
		}
		else{
			String aspecto = (String) categorias.getSelectedItem();
		    String modificacion = nuevoValor.getText();
		    principal.cargaArchivos.modificarReserva(principal.idReservaActual, aspecto, modificacion);
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
