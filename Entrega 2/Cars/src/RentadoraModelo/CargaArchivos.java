package RentadoraModelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JTable;

import Pruebas.CargaArchivoException;

import java.util.Properties;
import java.util.Set;

public class CargaArchivos {

	public ArrayList<String> cargarSedes(){
		
		Properties pSedes = new Properties();
		try {
		pSedes.load(new FileInputStream(new File("./RentadoraStorage/Sedes.txt")));
		ArrayList<String> sedes = new ArrayList<>();
		for (Object sede: pSedes.keySet()) {
			sedes.add((String)sede);
		}
		return sedes;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Properties cargarSeguros(){
		Properties pSeguros = new Properties();
		try {
			pSeguros.load(new FileInputStream(new File("./RentadoraStorage/Seguros.txt")));
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return pSeguros;
	}
	
	public Properties cargarVehiculos(){
		Properties pSeguros = new Properties();
		try {
			pSeguros.load(new FileInputStream(new File("./RentadoraStorage/Vehiculo.txt")));
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return pSeguros;
	}
	
	public Vehiculo cargarVehiculoIndiv(Properties pVehiculos, String placa) {
		String stringVehiculo = (String) pVehiculos.get(placa);
		String[] infoVehiculo = stringVehiculo.split(";");
		Categoria categoriaVehiculo = new Categoria(infoVehiculo[10]);
		Vehiculo vehiculo = new Vehiculo(infoVehiculo[0],infoVehiculo[1],infoVehiculo[2],
				infoVehiculo[3],infoVehiculo[4],infoVehiculo[5],
				Integer.parseInt(infoVehiculo[6]),
				Boolean.parseBoolean(infoVehiculo[7]), Boolean.parseBoolean(infoVehiculo[8]),
				infoVehiculo[9],categoriaVehiculo);
		return vehiculo;
		
	}
	
	public Vehiculo cargarVehiculoIndiv(String placa) throws CargaArchivoException {
		
		Properties pVehiculos = cargarVehiculos();
		
		Object info = pVehiculos.get(placa);
		
		if (info == null) { throw new CargaArchivoException("La placa " + placa + " no existe");}
		
		String stringVehiculo = (String) pVehiculos.get(placa);
		String[] infoVehiculo = stringVehiculo.split(";");
		Categoria categoriaVehiculo = new Categoria(infoVehiculo[10]);
		Vehiculo vehiculo = new Vehiculo(infoVehiculo[0],infoVehiculo[1],infoVehiculo[2],
				infoVehiculo[3],infoVehiculo[4],infoVehiculo[5],
				Integer.parseInt(infoVehiculo[6]),
				Boolean.parseBoolean(infoVehiculo[7]), Boolean.parseBoolean(infoVehiculo[8]),
				infoVehiculo[9],categoriaVehiculo);
		return vehiculo;
		
	}
	
	public ArrayList<String> cargarListaSeguros(){
		Properties pSeguros = cargarSeguros();
		try {
			ArrayList<String> seguro = new ArrayList<>();
			for (Object segurito: pSeguros.keySet()) {
				seguro.add((String)segurito);
			}
			return seguro;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	
	public Sede cargarSedeIndiv(String nombre) {
		Properties pSedes = new Properties();
		Sede sedeActual = null;
		try {
		pSedes.load(new FileInputStream(new File("./RentadoraStorage/Sedes.txt")));
		String infoTotal = (String) pSedes.get(nombre);
		String[] infoSede = infoTotal.split(";");
		
		sedeActual = new Sede(infoSede[0],infoSede[1],infoSede[2],infoSede[3]);
		sedeActual.setDisponibilidades();
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return sedeActual;
	}
	public ArrayList<Object> cargarAlquiler(String idReserva,String seguro,int numConductores){
		Alquiler nuevoAlquiler = null;
		ArrayList<Object> listaRetorno = new ArrayList<Object>();
		try {
			Properties pAlquiler = new Properties();
			pAlquiler.load(new FileInputStream(new File("./RentadoraStorage/Reservas.txt")));
			Set<Object> llavesReserva = pAlquiler.keySet();
			String infoReservaAl = (String) pAlquiler.get(idReserva);
			String[] listaReservaAl = infoReservaAl.split(";");
			
			nuevoAlquiler = new Alquiler(listaReservaAl[6],listaReservaAl[7]);
			
			double precioActual = Double.parseDouble(listaReservaAl[7]);
			double nuevoPrecio = (numConductores*(0.10*precioActual))+precioActual;
			
			Properties seguros = cargarSeguros();
			Double precioSeguro = Double.parseDouble(seguros.getProperty(seguro));
			nuevoPrecio += precioSeguro;
			
			listaReservaAl[7] = Double.toString(nuevoPrecio);	
			listaRetorno.add(listaReservaAl);
			nuevoAlquiler.setPrecioTarifa(Double.toString(nuevoPrecio));
			listaRetorno.add(nuevoAlquiler);
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return listaRetorno;
	}
	
	
	public void cargarTarReserva(Reserva reservaActual, Cliente clienteActual) {
			Properties pReserva = new Properties();
			try {
			pReserva.load(new FileInputStream(new File("./RentadoraStorage/Reservas.txt")));
			String infoReserva =(String) pReserva.get(reservaActual.getId()+clienteActual.getNombre());
			String nuevaInfo = infoReserva + ";" + reservaActual.getTarifa().getPrecio();
			pReserva.put(reservaActual.getId()+clienteActual.getNombre(), nuevaInfo);
			guardarArchivo(pReserva,"Reservas");
			
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public ArrayList<String> cargarCategoria(){
		
		Properties pSedes = new Properties();
		try {
		pSedes.load(new FileInputStream(new File("./RentadoraStorage/Tarifa.txt")));
		ArrayList<String> sedes = new ArrayList<>();
		for (Object sede: pSedes.keySet()) {
			sedes.add((String)sede);
		}
		return sedes;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object[][] cargarAutos(String sede){
		Properties pCarros = new Properties();
		try {
			pCarros.load(new FileInputStream(new File("./RentadoraStorage/Vehiculo.txt")));
			ArrayList<String[]> carros = new ArrayList<>();
			
			Set<Entry<Object, Object>> entrySet = pCarros.entrySet();
			
			
	        for (Entry<Object, Object> entry : entrySet) {
	            Object placa = entry.getKey();
	            String valores = (String) entry.getValue();
	            String[] carro = valores.split(";");
	            
	            if (carro[9].equals(sede) && carro[7].equals("false")) {
	            	carros.add(carro);
	            }
	        }
	        return carros.toArray(new Object[0][0]);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
	//LOGIN RELATED
	
	public Properties cargarLogin() {
		Properties pLogin = new Properties();
		try {
			pLogin.load(new FileInputStream(new File("./RentadoraStorage/Login.txt")));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return pLogin;
	}
	
	public Cliente cargarCliente(Properties pLogin, String user) {
		String infoCliente = (String) pLogin.get(user);
		String[] info = infoCliente.split(";");
		Cliente cliente =  new Cliente(info[1],info[2],info[3],info[4],
				info[6],info[17],info[5],info[7],info[8],info[9],
				info[10],info[12],info[13],info[14],Boolean.parseBoolean(info[16]));
		
		return cliente;
	}
	
	public AdministradorGeneral cargarGeneral(Properties pLogin, String user) {
		String infoCliente = (String) pLogin.get(user);
		String[] info = infoCliente.split(";");
		
		AdministradorGeneral adminGeneral = new AdministradorGeneral(info[1],info[2],
				info[3],info[4],info[5],info[6]);
		
		return adminGeneral;
	}
	
	public AdministradorLocal cargarLocal(Properties pLogin, String user) {
		
		String infoCliente = (String) pLogin.get(user);
		String[] info = infoCliente.split(";");
		
		AdministradorLocal adminLocal = new AdministradorLocal(info[1],info[2],
				info[3],info[4],info[5],info[8],info[6], Boolean.parseBoolean(info[7]));
		return adminLocal;
	}
	
	public EmpleadoInventario cargarEmpInventario(Properties pLogin, String user) {
		String infoCliente = (String) pLogin.get(user);
		String[] info = infoCliente.split(";");
		EmpleadoInventario inventario = new EmpleadoInventario(info[1],info[2],
				info[3],info[4],info[5],info[8],info[6], Boolean.parseBoolean(info[7]));
		return inventario;
	}
	
	public EmpleadoMostrador cargarMostrador(Properties pLogin, String user) {
		String infoCliente = (String) pLogin.get(user);
		String[] info = infoCliente.split(";");
		EmpleadoMostrador mostrador = new EmpleadoMostrador(info[1],info[2],
				info[3],info[4],info[5],info[8],info[6], Boolean.parseBoolean(info[7]));
		return mostrador;
	}
	
	public void guardarNuevoUsuario(ArrayList<String> infoUsuario) {
		String user = infoUsuario.get(0);
		infoUsuario.remove(0);
		Properties pLogin = cargarLogin();
		String mensaje = String.join(";", infoUsuario);
		pLogin.put(user, mensaje);
		guardarArchivo(pLogin,"Login");
		
	}
	
	public Properties cargarReserva() {
		Properties pReserva = new Properties();
		try {
		pReserva.load(new FileInputStream(new File("./RentadoraStorage/Reservas.txt")));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(pReserva.keySet());
		return pReserva;
	}
	
	public String[] cargarReserva(String id) throws CargaArchivoException {
		Properties pReserva = new Properties();

		try {
			pReserva.load(new FileInputStream(new File("./RentadoraStorage/Reservas.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Object info = pReserva.get(id);
		
		if (info == null) {throw new CargaArchivoException("La llave " + id + " no existe");}
		
		String infoReserva = (String) info;
		
		return infoReserva.split(";");
	}
	
	
	
	public void modificarReserva(String id, String aspecto, String modificacion) {
		Properties pReserva = cargarReserva();
		String infoReserva = (String) pReserva.get(id);
		String[] info = infoReserva.split(";");
		int i = 0;
		if (aspecto.equals("Fecha Devolucion")) {
			i = 2;
		}
		else if (aspecto.equals("Hora Devolucion")) {
			i = 3;
		}
		else if (aspecto.equals("Sede Entrega")) {
			i = 6;
		}
		System.out.println(info.length);
		System.out.println(infoReserva);
		info[i] = modificacion;
		String mensaje = String.join(";", info);
		pReserva.put(id, mensaje);
		guardarArchivo(pReserva,"Reservas");
	}
	
	public void guardarArchivo(Properties p,String name) {
		try {
		p.save(new FileOutputStream(new File ("./RentadoraStorage/"+name+".txt")),"");
		}
		catch(FileNotFoundException e) {
			System.err.println("File not found: " + e.getMessage());
		}
	}
}
