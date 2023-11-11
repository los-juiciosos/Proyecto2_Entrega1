package RentadoraModelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Properties;

public class OtrosConductores {

	
	//Attributes
	private String nombre;
	private Licencia licencia;

	//Constructors
	
	public OtrosConductores(String nombre, String numero, String pais, String fechaVencimiento,String imagen) {
		this.nombre = nombre;
		Licencia licenciaActual = new Licencia(numero,pais,fechaVencimiento,imagen);
		this.licencia = licenciaActual;
	}

	//Getters & Setters
	
	public Licencia getLicencia() {
		return licencia;
	}

	public void setLicencia(Licencia licencia) {
		this.licencia = licencia;
	}
	
	//Metodos
	
	public void registrarLicencia(String idReserva){
		Properties pConductor = new Properties();
		try {
			pConductor.load(new FileInputStream(new File("./RentadoraStorage/OtrosConductores.txt")));
			String infoConductor = nombre+";"+licencia.getNumero()+";"+licencia.getPaisDeExpedicion()+";"
							+licencia.getFechaDeVencimiento()+";"+licencia.getImagen();
			String llaveConductor = nombre + " "+ idReserva;
			pConductor.put(llaveConductor,infoConductor);
			guardarLogin(pConductor, "OtrosConductores");
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
