package Interfaz.Empleados;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Interfaz.Principal.ErrorDisplay;
import Interfaz.Principal.MetodosAuxiliares;
import Interfaz.Principal.Notificacion;
import Interfaz.Principal.Principal;
import Interfaz.Principal.Verify;
import RentadoraModelo.EmpleadoMostrador;

public class RevisarVehiculo extends JPanel implements MetodosAuxiliares, ActionListener {

	Principal principal;
	
	private GridBagConstraints gbc;
	
	private JButton confirmar;
	
	private JButton volver;
	
	private JTextField placa;
	
	static final int textFieldSize = 20;
	
	static final int YSpace = 5;
	private ErrorDisplay error;
	private Notificacion notify;
	private Verify verificador;
	
	public RevisarVehiculo(Principal principal) {
		
		this.verificador = new Verify();
		this.principal = principal;
		
		setLayout(new GridBagLayout());
		
		this.gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        
        JLabel instruccion = new JLabel("Mandar a revisar el Auto con placa:");
		subTitleText(instruccion);
		
		placa = new JTextField(textFieldSize);
		ponerTextitoGris(placa,"Placa");
		
		confirmar = new JButton("Confirmar");
		confirmar.setActionCommand("empleadoMostrador");
		confirmar.addActionListener(this);
		
		volver = new JButton("VOLVER");
		volver.setActionCommand("VOLVER");
		volver.addActionListener(this);
		
		add(instruccion, gbc);
		
		add(placa, gbc);
		
		add(confirmar, gbc);
		
		add(volver,gbc);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String grito = e.getActionCommand();
		
		if (grito.equals("VOLVER")) {
			principal.cambiarPanel("empleadoMostrador");
		}
		
		else if (placa.getText().equals("Placa")) {
			error = new ErrorDisplay("PORFAVOR LLENE EL CAMPO");
		}
		else {
			EmpleadoMostrador empleado = (EmpleadoMostrador) principal.usuarioActual;
			Properties pVehiculo = principal.cargaArchivos.cargarVehiculos();
			boolean verifyPlaca = verificador.verifyCarroInSede(pVehiculo, placa.getText(), empleado.getSede());
			if (verifyPlaca == false) {
				error = new ErrorDisplay("CARRO FUERA DE MI JURISDICCIÓN O NO ENCONTRADO");
			}
			else {
				String estado = empleado.mandarRevision(placa.getText());
				if (estado == null) {
					error = new ErrorDisplay("CARRO NO DISPONIBLE AUN");
				}
				else {
					notify = new Notificacion("CARRO SE MANDÓ A REVISIÓN CORRECTAMENTE");
				}
			}
			
		}
		principal.cambiarPanel(grito);
		
	}
	
	private void addSpace(int Yspace) {
		add(Box.createRigidArea(new Dimension(0, Yspace)), gbc);
	}

}
