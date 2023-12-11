package RentadoraModelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Reserva {
	
	//Attributes
	private static int id = 0;
	private String fechaLlegadaAgencia;
	private String horaLlegadaAgencia;
	private String fechaDevolucion;
	private String horaDevolucion;
	private Sede sedeFinal;
	private Categoria categoriaVehiculo;
	private Vehiculo carroEscogido;
	private Tarifa tarifa;
	private String sedeRegreso;

	//Constructors
	
	public Reserva(String fechaLlegadaAgencia, String horaLlegadaAgencia, String fechaDevolucion,
			String horaDevolucion,Sede sedeFinal, Categoria categoriaVehiculo,String sedeRegreso) {
		super();
		id +=1;
		this.fechaLlegadaAgencia = fechaLlegadaAgencia;
		this.horaLlegadaAgencia = horaLlegadaAgencia;
		this.fechaDevolucion = fechaDevolucion;
		this.horaDevolucion = horaDevolucion;
		this.sedeFinal = sedeFinal;
		this.categoriaVehiculo = categoriaVehiculo;
		this.carroEscogido = null;
		this.tarifa = null;
		this.sedeRegreso = sedeRegreso;
	}
		
	//Getters & Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFechaLlegadaAgencia() {
		return fechaLlegadaAgencia;
	}

	public void setFechaLlegadaAgencia(String fechaLlegadaAgencia) {
		this.fechaLlegadaAgencia = fechaLlegadaAgencia;
	}

	public String getHoraLlegadaAgencia() {
		return horaLlegadaAgencia;
	}

	public void setHoraLlegadaAgencia(String horaLlegadaAgencia) {
		this.horaLlegadaAgencia = horaLlegadaAgencia;
	}

	public String getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public String getHoraDevolucion() {
		return horaDevolucion;
	}

	public void setHoraDevolucion(String horaDevolucion) {
		this.horaDevolucion = horaDevolucion;
	}

	public Sede getSedeFinal() {
		return sedeFinal;
	}

	public void setSedeFinal(Sede sedeFinal) {
		this.sedeFinal = sedeFinal;
	}

	public Categoria getCategoriaVehiculo() {
		return categoriaVehiculo;
	}

	public void setCategoriaVehiculo(Categoria categoriaVehiculo) {
		this.categoriaVehiculo = categoriaVehiculo;
	}
	
	public Vehiculo getCarroEscogido() {
		return carroEscogido;
	}

	public void setCarroEscogido(Vehiculo carroEscogido) {
		this.carroEscogido = carroEscogido;
	}
	public Tarifa getTarifa() {
		return tarifa;
	}

	public void setTarifa() {
		boolean sedeDiferente = true;
		if(sedeRegreso.equals(sedeFinal.getNombre())) {
			sedeDiferente = false;
		}
		Tarifa tarifaActual = new Tarifa(categoriaVehiculo,sedeDiferente,fechaLlegadaAgencia
										,fechaDevolucion);
		this.tarifa = tarifaActual;
	}

	//Methods
	
	public void guardarReserva(Vehiculo vehiculo) {
		Properties pReserva = new Properties();
		try {
			pReserva.load(new FileInputStream(new File("./RentadoraStorage/Vehiculo.txt")));
			String infoReserva = (String) pReserva.get(vehiculo.getPlaca());
			String[] listaInfoLogin = infoReserva.split(";");
			listaInfoLogin[7] = "true";
			String informacionJunta = String.join(";", listaInfoLogin);
			pReserva.setProperty(vehiculo.getPlaca(),informacionJunta);
			guardarLogin(pReserva,"Vehiculo");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cobrarReserva() {
		
	}
	
	public Vehiculo obtenerVehiculo(Sede sede,Categoria categoria) {
		
		Vehiculo vehiculoRetorno = null;
		ArrayList<Vehiculo> disponibles = sede.getDisponibles();
		Categoria categoriaActual = null;
		while (vehiculoRetorno == null) {
			for (Vehiculo vehiculoActual:disponibles) {
				categoriaActual = vehiculoActual.getCategoria();
				if (categoriaActual.getNombre().equals(categoria.getNombre())) {
					if(vehiculoActual.isReservado() == false) {
						vehiculoRetorno = vehiculoActual;
						guardarReserva(vehiculoRetorno);
						}
					}
				}
			
				if (vehiculoRetorno == null) {
					if (categoria.getMejorCategoria() == null) {
						System.out.println(categoriaActual.getMejorCategoria());
						return null;
					}
					else {
						categoria = categoria.getMejorCategoria();
					}
				}
			
		}
			
		return vehiculoRetorno;
	}
	
	public String guardarReservaEspecial(String placa, String sede) {
		Properties pEspecial = new Properties();
		try {
			pEspecial.load(new FileInputStream(new File("./RentadoraStorage/Vehiculo.txt")));
			String infoReserva = (String) pEspecial.get(placa);
			String[] listaInfoLogin = infoReserva.split(";");
			if (listaInfoLogin[7] == "true") {
				return null; 
			}
			listaInfoLogin[9] = sede;
			String informacionJunta = String.join(";", listaInfoLogin);
			pEspecial.setProperty(placa,informacionJunta);
			guardarLogin(pEspecial,"Vehiculo");
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




