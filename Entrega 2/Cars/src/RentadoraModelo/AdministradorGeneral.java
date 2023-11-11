package RentadoraModelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Set;

public class AdministradorGeneral extends Usuario{

	//Constructor
	
	public AdministradorGeneral(String nombre, String fechaDeNacimiento, String nacionalidad,
			String documentoIdentidad, String imagen, String rol) {
		super (nombre, fechaDeNacimiento, nacionalidad, documentoIdentidad, imagen, rol);
	}

	//Methods
	
	public void darBajaVehiculo(String placa) {
		
		Properties pUsuario = new Properties();
		try {
			pUsuario.load(new FileInputStream(new File("./RentadoraStorage/Vehiculo.txt")));
			pUsuario.remove(placa);
			guardarLogin(pUsuario,"Vehiculo");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void registrarNuevoVehiculo(String placaAgregar,String marca, String modelo,
			String color,String transmision,String combustible,String capacidad,
			String sedeAgregar,String categoriaAgregar) {
		
		String infoFinal = placaAgregar+";"+marca+";"+modelo+";"+color+";"+transmision+";"+
		combustible+";"+capacidad +";false;true;"+sedeAgregar+";"+categoriaAgregar+";";
		
		Properties pVehiculo = new Properties();
		try {
			pVehiculo.load(new FileInputStream(new File("./RentadoraStorage/Login.txt")));
			pVehiculo.put(placaAgregar, infoFinal);
			guardarLogin(pVehiculo,"Vehiculo");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void modificarOfertaSeguros(String seguro, String precio) {
		
		Properties pSeguro = new Properties();
		String nuevoSeguro = "Multas";
		if (seguro.equals("1")) {
			nuevoSeguro = "TodoRiesgo";
		}
		else if (seguro.equals("2")) {
			nuevoSeguro = "SoloChoques";
		}
		else if (seguro.equals("3")) {
			nuevoSeguro = "Multas";
		}
		
		try {
			pSeguro.load(new FileInputStream(new File("./RentadoraStorage/Seguros.txt")));
			pSeguro.put(nuevoSeguro,precio);
			
			guardarLogin(pSeguro,"Seguros");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void modificarInformacionSedes(String sede, String opcion, String modificacion) {
		
		Properties pSede = new Properties();
		try {
			pSede.load(new FileInputStream(new File("./RentadoraStorage/Sedes.txt")));
			String infoSede = (String) pSede.get(sede);
			String[] listaSede = infoSede.split(";");
			
			if (opcion.equals("1")) {
				listaSede[1] = modificacion;
			}
			else if (opcion.equals("2")) {
				listaSede[2] = modificacion;

			}
			else if (opcion.equals("3")) {
				listaSede[3] = modificacion;

			}
			
			String informacionJunta = String.join(";", listaSede);
			pSede.put(sede, informacionJunta);
			guardarLogin(pSede,"Sedes");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void modificarTarifaCategoria(String categoria, String precio) {
		
		Properties pTarifa = new Properties();
		
		try {
			pTarifa.load(new FileInputStream(new File("./RentadoraStorage/Tarifa.txt")));
			pTarifa.put(categoria,precio);
			
			guardarLogin(pTarifa,"Tarifa");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public String obtenerPlacaMantenimiento() {
		
		Properties pManten = new Properties();
		
		try {
			pManten.load(new FileInputStream(new File("./RentadoraStorage/Mantenimiento.txt")));
			Set<Object> llaves = pManten.keySet();
			for (Object placa: llaves) {
				String newPlaca = (String) placa;
				return newPlaca;
			}
			
			
			guardarLogin(pManten,"Mantenimiento");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	public void mostrarLog(String placaLog) {
		
		Properties pHistorial = new Properties();
		Properties pAlquiler = new Properties();
		
		try {
			pHistorial.load(new FileInputStream(new File("./RentadoraStorage/HistorialVehiculo.txt")));
			pAlquiler.load(new FileInputStream(new File("./RentadoraStorage/Alquiler.txt")));
			Set<Object> llaves = pAlquiler.keySet();
			Set<Object> llavesHisto = pHistorial.keySet();
			for (Object histo: llavesHisto) {
				pHistorial.remove(histo);
			}
			
			for (Object placa: llaves) {
				if (placaLog.equals(placa)) {
					pHistorial.put(placaLog, pAlquiler.get(placa));
				}
			}
			
			guardarLogin(pHistorial,"HistorialVehiculo");
		}
		catch (Exception e) {
			e.printStackTrace();
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
