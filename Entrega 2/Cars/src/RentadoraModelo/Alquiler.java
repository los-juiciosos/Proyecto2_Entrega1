package RentadoraModelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Alquiler {
	
	//Attributes
	
	private String sedeFinal;
	private ArrayList<Seguros> listaSeguros;
	private String precioTarifa;
	
	//Constructors
	

	public Alquiler(String sedeFinal,String precioTarifa) {
		this.sedeFinal = sedeFinal;
		this.listaSeguros = new ArrayList<Seguros>();
		this.precioTarifa = precioTarifa;
	}

	//Getters & Setters

	public String getSedeFinal() {
		return sedeFinal;
	}
	
	public void setSedeFinal(String sedeFinal) {
		this.sedeFinal = sedeFinal;
	}
	
	public ArrayList<Seguros> getListaSeguros() {
		return listaSeguros;
	}
	
	public void setListaSeguros(ArrayList<Seguros> listaSeguros) {
		this.listaSeguros = listaSeguros;
	}
	
	public String getPrecioTarifa() {
		return precioTarifa;
	}
	
	public void setPrecioTarifa(String precioTarifa) {
		this.precioTarifa = precioTarifa;
	}
	


	//Methods
	
	public void modificarReserva() {
		
	}

	
	public void formalizarAlquiler(String[] infoAlquiler,String idReserva,String nombre) {
		
		Properties pAlquiler = new Properties();
		Properties pReserva = new Properties();
		
		try {
			pAlquiler.load(new FileInputStream(new File("./RentadoraStorage/Alquiler.txt")));
			String registroAlquiler = String.join(";",infoAlquiler);
			registroAlquiler += ";false";
			pAlquiler.put(infoAlquiler[4] + " " + idReserva, registroAlquiler);
			guardarLogin(pAlquiler,"Alquiler");
			
			pReserva.load(new FileInputStream(new File("./RentadoraStorage/Reservas.txt")));
			pReserva.remove(idReserva);
			guardarLogin(pReserva,"Reservas");
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void cobroTotal() {
		
	}
	
	public void bloquearTarjeta() {
		
	}
	
	public void ofrecerSeguros() {
		
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