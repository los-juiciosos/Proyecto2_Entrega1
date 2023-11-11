package RentadoraModelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

public class EmpleadoMostrador extends Empleado {

	//Constructor
	
	public EmpleadoMostrador(String nombre, String fechaDeNacimiento, String nacionalidad,
			String documentoIdentidad, String imagen, String rol,String sede, boolean activo) {
		super(nombre, fechaDeNacimiento, nacionalidad, documentoIdentidad, imagen, rol, sede, activo);
	}
	
	
	//Methods
	
	public String iniciarReservaEspecial(String fechaLlegada,String horaLlegada,
			String fechaDevolucion, String horaDevolucion, String sedeDestino,String placa) {
		
		Reserva reservaEspecial = new Reserva(fechaLlegada,horaLlegada,fechaDevolucion,
				horaDevolucion,null,null,sedeDestino);
		String resEspecial = reservaEspecial.guardarReservaEspecial(placa,sedeDestino);
		if (resEspecial == null) {
			return null;
		}
		else {
			return "si";
		}
		
		
	}
		
	public String mandarRevision(String placa) {
		
		Properties pVehiculo = new Properties();
		Properties pRevision = new Properties();
		try {
			pVehiculo.load(new FileInputStream(new File("./RentadoraStorage/Vehiculo.txt")));
			String infoReserva = (String) pVehiculo.get(placa);
			String[] listaInfoLogin = infoReserva.split(";");
			if (listaInfoLogin[8] == "false") {
				return null;
			}
			listaInfoLogin[8] = "false";
			String informacionJunta = String.join(";", listaInfoLogin);
			pVehiculo.setProperty(placa,informacionJunta);
			guardarLogin(pVehiculo,"Vehiculo");
			
			pRevision.put(placa, informacionJunta);
			guardarLogin(pRevision,"Revision");
			
			return "si";
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void guardarLogin(Properties p,String name) {
		try {
		p.save(new FileOutputStream(new File ("./RentadoraStorage/"+name+".txt")),"");
		}
		catch(FileNotFoundException e) {
			System.err.println("File not found: " + e.getMessage());
		}
	}
	

}
