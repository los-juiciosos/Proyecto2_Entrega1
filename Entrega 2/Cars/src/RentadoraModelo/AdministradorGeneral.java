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
	
	public void eliminarMantenimiento(String placa) {
		Properties pMantenimiento = new Properties();
		try {
			pMantenimiento.load(new FileInputStream(new File("./RentadoraStorage/Mantenimiento.txt")));
			pMantenimiento.remove(placa);
			guardarLogin(pMantenimiento,"Mantenimiento");
			
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
			pVehiculo.load(new FileInputStream(new File("./RentadoraStorage/Vehiculo.txt")));
			pVehiculo.put(placaAgregar, infoFinal);
			guardarLogin(pVehiculo,"Vehiculo");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void modificarOfertaSeguros(String nuevoSeguro, String precio) {
		
		Properties pSeguro = new Properties();
		
		
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
			
			if (opcion.equals("Direccion")) {
				listaSede[1] = modificacion;
			}
			else if (opcion.equals("Hora Apertura")) {
				listaSede[2] = modificacion;

			}
			else if (opcion.equals("Hora Cierre")) {
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
	
	public void actualizarEstadoVehículo(String placa) {
		
		Properties pVehiculo = new Properties();
		Properties pMantenimiento = new Properties();
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
	
	public String mostrarLog(String placaLog) {
		
		Properties pHistorial = new Properties();
		Properties pAlquiler = new Properties();
		String mensaje = "";
		try {
			pHistorial.load(new FileInputStream(new File("./RentadoraStorage/HistorialVehiculo.txt")));
			pAlquiler.load(new FileInputStream(new File("./RentadoraStorage/Alquiler.txt")));
			Set<Object> llaves = pAlquiler.keySet();
			Set<Object> llavesHisto = pHistorial.keySet();
			for (Object histo: llavesHisto) {
				pHistorial.remove(histo);
			}
			
			for (Object placa: llaves) {
				String placaLarga = (String) placa;
				if (placaLarga.contains(placaLog)) {
					pHistorial.put(placaLog, pAlquiler.get(placaLarga));
					String info = (String) pAlquiler.get(placaLarga);
					mensaje = mensaje + info + "\n";
				}
			}
			
			guardarLogin(pHistorial,"HistorialVehiculo");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return mensaje;
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
