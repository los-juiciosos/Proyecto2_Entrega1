package RentadoraModelo;

public class Seguros {
	
	//Attributes
	
	private String nombre;
	private float precio;
	private String descripcion;
	private String fechaOferta;
	
	
	
	//Constructors
	
	public Seguros(String nombre, float precio, String descripcion, String fechaOferta) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.fechaOferta = fechaOferta;
	}

	//Getters & Setters
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public float getPrecio() {
		return precio;
	}


	public void setPrecio(float precio) {
		this.precio = precio;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getFechaOferta() {
		return fechaOferta;
	}


	public void setFechaOferta(String fechaOferta) {
		this.fechaOferta = fechaOferta;
	}
	
	//Methods

	public void calcularPrecioSeguro() {
	
	}
}