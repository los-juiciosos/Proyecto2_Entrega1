package RentadoraModelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Properties;

public class AdministradorLocal extends Empleado{
	
	//Attributes
	private ArrayList<Empleado> listaEmpleados;

	//Constructor
	
	public AdministradorLocal(String nombre, String fechaDeNacimiento, String nacionalidad,
			String documentoIdentidad, String imagen, String rol, String sede, boolean activo) {
		super(nombre, fechaDeNacimiento, nacionalidad, documentoIdentidad, imagen, rol, sede, activo);
		this.listaEmpleados = new ArrayList<Empleado>();
	}

	//Getters y Setters
	public ArrayList<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}
	
	//Methods
	
	public void crearUsuario(String nombre,String nacimiento,String nacionalidad,
			String documento,String imagen,String celular,String email,String metodoPago,
			String tarjetaCredito,String vencimientoCredito,String numLicencia,
			String paisLicencia,String vencimientoLicencia) {	
		
		String infoUsuario = nombre + ";"+ nombre+";"+nacimiento+";"+nacionalidad+";"+documento+";"+
		imagen+";"+celular+";"+email+";"+metodoPago+";"+tarjetaCredito+";"+vencimientoCredito+";"+
		numLicencia+";"+paisLicencia+";"+vencimientoLicencia+";"+"true;cliente";
		
		Properties pUsuario = new Properties();
		try {
			pUsuario.load(new FileInputStream(new File("./RentadoraStorage/Login.txt")));
			pUsuario.put(nombre, infoUsuario);
			guardarLogin(pUsuario,"Login");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void registrarTrabajador(String nombre,String nacimiento, String nacionalidad, 
			String documento, String imagen,String sedeTrabajar,String rolTrabajador) {
		
		String infoTrabajador = nombre+";"+nombre+";"+nacimiento+";"+nacionalidad+";"+documento+";"+
		imagen+";"+sedeTrabajar+";true;"+rolTrabajador;
		
		Properties pUsuario = new Properties();
		try {
			pUsuario.load(new FileInputStream(new File("./RentadoraStorage/Login.txt")));
			pUsuario.put(nombre, infoTrabajador);
			guardarLogin(pUsuario,"Login");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String modificarInformacionTrabajador(String nombre, String opcion, String modificacion,
			String sedeLocal) {
		
		Properties pUsuario = new Properties();
		try {
			pUsuario.load(new FileInputStream(new File("./RentadoraStorage/Login.txt")));
			String infoEmpleado = (String) pUsuario.get(nombre);
			if (infoEmpleado == null) {
				return null;
			}
			String[] listaInfo = infoEmpleado.split(";");
			if (listaInfo[6].equals(sedeLocal)) {
			if (opcion.equals("1")) {
				listaInfo[1] = modificacion;
			}
			else if (opcion.equals("2")) {
				listaInfo[2] = modificacion;
			}
			else if (opcion.equals("3")) {
				listaInfo[3] = modificacion;
			}
			else if (opcion.equals("4")) {
				listaInfo[4] = modificacion;
			}
			else if (opcion.equals("5")) {
				listaInfo[5] = modificacion;
			}
			else if (opcion.equals("6")) {
				listaInfo[6] = modificacion;
			}
			else if (opcion.equals("7")) {
				listaInfo[8] = modificacion;
			}
			
			String informacionJunta = String.join(";", listaInfo);
			pUsuario.put(nombre, informacionJunta);
			guardarLogin(pUsuario,"Login");
			return "si";
			}
			else {
				return "no";
			}
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
