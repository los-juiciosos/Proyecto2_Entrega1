package RentadoraModelo;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

public class Sede {
	
	//Attributes
	
	private String nombre;
	private String ubicacion;
	private String horaApertura;
	private String horaCierre;
	private ArrayList<CarroNoDisponible> noDisponibles;
	private ArrayList<Vehiculo> disponibles;
	
	//Constructor
	
	public Sede(String nombre, String ubicacion, String horaApertura, String horaCierre) {
		super();
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
		this.noDisponibles = new ArrayList<CarroNoDisponible>();
		this.disponibles = new ArrayList<Vehiculo>();
	}
	
	
	
	//Getters y Setters

	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getUbicacion() {
		return ubicacion;
	}



	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}



	public String getHoraApertura() {
		return horaApertura;
	}



	public void setHoraApertura(String horaApertura) {
		this.horaApertura = horaApertura;
	}



	public String getHoraCierre() {
		return horaCierre;
	}



	public void setHoraCierre(String horaCierre) {
		this.horaCierre = horaCierre;
	}

	public void setDisponibilidades() {
		Properties pVehiculos = new Properties();
		try {
		pVehiculos.load(new FileInputStream(new File("./RentadoraStorage/Vehiculo.txt")));
		Set<Object> placaVehiculo = pVehiculos.keySet();
		
		for (Object placaActual: placaVehiculo) {
			
			String placa = (String) placaActual;
			String stringVehiculo = (String) pVehiculos.get(placa);
			String[] infoVehiculo = stringVehiculo.split(";");
			if (infoVehiculo[9].equals(nombre)) {
				Categoria categoriaVehiculo = new Categoria(infoVehiculo[10]);
				categoriaVehiculo.setMejorCategoria(categoriaVehiculo);
			
				Vehiculo vehiculo = new Vehiculo(infoVehiculo[0],infoVehiculo[1],infoVehiculo[2],
											infoVehiculo[3],infoVehiculo[4],infoVehiculo[5],
											Integer.parseInt(infoVehiculo[6]),
								Boolean.parseBoolean(infoVehiculo[7]), Boolean.parseBoolean(infoVehiculo[8]),
								infoVehiculo[9],categoriaVehiculo);
			
				if (Boolean.parseBoolean(infoVehiculo[8]) == true) {
					disponibles.add(vehiculo);
				}
				else if (Boolean.parseBoolean(infoVehiculo[8]) == false) {
					CarroNoDisponible vehiculoNoDisponible = new CarroNoDisponible(
							infoVehiculo[0],infoVehiculo[1],infoVehiculo[2],
							infoVehiculo[3],infoVehiculo[4],infoVehiculo[5],
							Integer.parseInt(infoVehiculo[6]),
							Boolean.parseBoolean(infoVehiculo[7]), Boolean.parseBoolean(infoVehiculo[8]),
							infoVehiculo[9],categoriaVehiculo);
	
					noDisponibles.add(vehiculoNoDisponible);
				}
			 }
			
		  }
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	//Methods
	
	public int getInventario()
	{
		int nodis = noDisponibles.size();
		int dis = disponibles.size();
		
		return nodis + dis;
	}
	
	public ArrayList<Vehiculo> getDisponibles()
	{
		return disponibles;
	}
	
	public ArrayList<CarroNoDisponible> getNoDisponibles()
	{
		return noDisponibles;
	}
}

