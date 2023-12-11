package RentadoraModelo;

public class Vehiculo 
{
	//Attributes
	
	private String placa;
	private String marca;
	private String modelo;
	private String color;
	private String tipoDeTransmision;
	private String combustible;
	private int capacidad;
	private boolean reservado;
	private boolean disponible;
	private String sede;	
	private Categoria categoria;
	
	//Constructor
	
	public Vehiculo(String placa, String marca, String modelo, String color, String tipoDeTransmision,
			String combustible, int capacidad, boolean reservado,boolean disponible,String sede, Categoria categoria) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.tipoDeTransmision = tipoDeTransmision;
		this.combustible = combustible;
		this.capacidad = capacidad;
		this.reservado = reservado;
		this.disponible = disponible;
		this.sede = sede;
		this.categoria = categoria;
	}

	//Getters y Setters
	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTipoDeTransmision() {
		return tipoDeTransmision;
	}

	public void setTipoDeTransmision(String tipoDeTransmision) {
		this.tipoDeTransmision = tipoDeTransmision;
	}

	public String getCombustible() {
		return combustible;
	}

	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public void setAlquilado(boolean reservado) {
		this.reservado = reservado;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}	
	
	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	public boolean isReservado() {
		return reservado;
	}

	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}
	//Methods
	
	

	public void getEstado() {
	}
	
	public void getUbicacion() {
	}
	
}
