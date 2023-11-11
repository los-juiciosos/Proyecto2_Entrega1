package RentadoraModelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Properties;

import RentadoraConsola.Consola;

public class Cliente extends Usuario {
	
	//Attributes
	
	private String numeroDeCelular;
	private String email;
	private MetodoPago medioPago;
	private Licencia licencia;
	private Reserva reservaActual;
	private boolean estadoTarjeta;
		
	//Constructor
	public Cliente(String nombre, String fechaDeNacimiento, String nacionalidad,
			String documentoIdentidad, String imagen, String rol, String numeroDeCelular, String email,
			String tipoPago, String numeroTarjetaCredito, String fechaVencimiento,
			String numeroLicencia, String paisExpedicion, String vencimientoLicencia,boolean estadoTarjeta){
		super(nombre, fechaDeNacimiento, nacionalidad, documentoIdentidad, imagen, rol);
		this.numeroDeCelular = numeroDeCelular;
		this.email = email;
		this.medioPago = new MetodoPago(tipoPago,numeroTarjetaCredito,fechaVencimiento,estadoTarjeta);
		this.licencia = new Licencia(numeroLicencia, paisExpedicion, vencimientoLicencia,imagen);
		this.estadoTarjeta = estadoTarjeta;
	}
	
	//Getters y Setters
	public String getNumeroDeCelular() {
		return numeroDeCelular;
	}
	public void setNumeroDeCelular(String numeroDeCelular) {
		this.numeroDeCelular = numeroDeCelular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public MetodoPago getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(MetodoPago medioPago) {
		this.medioPago = medioPago;
	}
	public Licencia getLicencia() {
		return licencia;
	}
	public void setLicencia(Licencia licencia) {
		this.licencia = licencia;
	}
	
	public Reserva getReservaActual() {
		return reservaActual;
	}

	public void setReservaActual(Reserva reservaActual) {
		this.reservaActual = reservaActual;
	}

	public boolean isEstadoTarjeta() {
		return estadoTarjeta;
	}

	public void setEstadoTarjeta(boolean estadoTarjeta) {
		this.estadoTarjeta = estadoTarjeta;
	}
	
	
	//Methods

	public Reserva iniciarReserva(String fechaLlegada, String horaLlegada,
	String fechaDevolucion, String horaDevolucion, Sede sedeEntrega, 
	Categoria categoriaVehiculo,String sedeFinal){
		reservaActual = new Reserva(fechaLlegada,horaLlegada,fechaDevolucion,horaDevolucion,
									sedeEntrega,categoriaVehiculo,sedeFinal);
		
		Vehiculo carroEscogido = reservaActual.obtenerVehiculo(sedeEntrega,categoriaVehiculo);
		if (carroEscogido == null) {
			reservaActual.setCarroEscogido(null);
			return null;
		}
		else {
			reservaActual.setCarroEscogido(carroEscogido);
		}
		Properties pReserva = new Properties();
		try {
		pReserva.load(new FileInputStream(new File("./RentadoraStorage/Reservas.txt")));
		ArrayList<String> listaReserva = new ArrayList<String>(); 
		
		listaReserva.add(fechaLlegada);
		listaReserva.add(horaLlegada);
		listaReserva.add(fechaDevolucion);
		listaReserva.add(horaDevolucion);
		listaReserva.add(carroEscogido.getPlaca());
		listaReserva.add(sedeEntrega.getNombre());
		listaReserva.add(sedeFinal);


		String registroReserva = String.join(";",listaReserva);
		String idReserva = reservaActual.getId()+ nombre;
		pReserva.put(idReserva, registroReserva);
		guardarLogin(pReserva,"Reservas");
		
		return reservaActual;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
		
	public String entregarVehiculo(String idReserva,String placa) {
		
		Properties pAlquiler = new Properties();
		Properties pVehiculo = new Properties();
		Properties pUsuario = new Properties();
		try {
		pAlquiler.load(new FileInputStream(new File("./RentadoraStorage/Alquiler.txt")));
		String infoAlquiler = (String) pAlquiler.get(placa + " "+idReserva);
		String[] listaAlquiler = infoAlquiler.split(";");
		String sedeRegreso = listaAlquiler[6];
		listaAlquiler[8] = "true";
		
		String registroAlquiler= String.join(";",listaAlquiler);
		pAlquiler.put(placa + " "+idReserva, registroAlquiler);
		
		pVehiculo.load(new FileInputStream(new File("./RentadoraStorage/Vehiculo.txt")));
		String infoVehiculo = (String) pVehiculo.get(placa);
		String[] listaVehiculo = infoVehiculo.split(";");
		listaVehiculo[8] = "false";
		listaVehiculo[9] = sedeRegreso;
		
		String registroVehiculo= String.join(";",listaVehiculo);
		pVehiculo.put(placa, registroVehiculo);
		
		pUsuario.load(new FileInputStream(new File("./RentadoraStorage/Login.txt")));
		String infoUsuario = (String) pUsuario.get(nombre);
		String[] listaUsuario = infoUsuario.split(";");
		listaUsuario[14] = "true";
		String infoUpdated = String.join(";", listaUsuario);
		pUsuario.put(nombre, infoUpdated);
		
		guardarLogin(pUsuario,"Login");
		guardarLogin(pAlquiler,"Alquiler");
		guardarLogin(pVehiculo,"Vehiculo");
		
		
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
