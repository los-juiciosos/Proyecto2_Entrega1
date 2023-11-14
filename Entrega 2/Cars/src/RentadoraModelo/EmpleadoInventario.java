package RentadoraModelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Set;

public class EmpleadoInventario extends Empleado{

	//Constructor 
	
	public EmpleadoInventario(String nombre, String fechaDeNacimiento, String nacionalidad,
			String documentoIdentidad, String imagen, String rol,String sede, boolean activo) {
		super(nombre, fechaDeNacimiento, nacionalidad, documentoIdentidad, imagen, rol, sede, activo);
	}
	
	//Methods
	
	public void revisarVehículo(String placa, boolean mantenimiento) {
		Properties pRevision = new Properties();
		Properties pMantenimiento = new Properties();
		try {
			pRevision.load(new FileInputStream(new File("./RentadoraStorage/Revision.txt")));
			pMantenimiento.load(new FileInputStream(new File("./RentadoraStorage/Mantenimiento.txt")));
			String infoRevisar = (String) pRevision.get(placa);
			String[] listaRevisar = infoRevisar.split(";");
			if (mantenimiento == true) {
				pMantenimiento.put(placa, infoRevisar);
			}
			else {
			   actualizarEstadoVehículo(placa);
			   
			}
			pRevision.remove(placa);
			guardarLogin(pRevision,"Revision");
			guardarLogin(pMantenimiento,"Mantenimiento");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void actualizarEstadoVehículo(String placa) {
		
		Properties pVehiculo = new Properties();
		try {
			pVehiculo.load(new FileInputStream(new File("./RentadoraStorage/Vehiculo.txt")));
			String infoReserva = (String) pVehiculo.get(placa);
			String[] listaInfoLogin = infoReserva.split(";");
			listaInfoLogin[7] = "false";
			listaInfoLogin[8] = "true";
			String informacionJunta = String.join(";", listaInfoLogin);
			pVehiculo.put(placa, informacionJunta);
			
			guardarLogin(pVehiculo,"Vehiculo");
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public String obtenerPlacaRevision() {
		
		Properties pRevision = new Properties();
		try {
			pRevision.load(new FileInputStream(new File("./RentadoraStorage/Revision.txt")));
			Set<Object> llaves = pRevision.keySet();
			for (Object placaRev: llaves) {
				
				String infoCarroR = (String) pRevision.get(placaRev);
				String[] listaInfoR = infoCarroR.split(";");
				System.out.println(listaInfoR[9]);
				
				if (listaInfoR[9].equals(sede)) {
					return listaInfoR[0];
				}
			}
			return null;
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
