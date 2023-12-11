package RentadoraModelo;

public class Licencia {
	
	//Attributes
	
	private String numero;
	private String paisDeExpedicion;
	private String fechaDeVencimiento;
	private String imagen;
	
	//Constructors
	
	public Licencia(String numero, String paisDeExpedicion, String fechaDeVencimiento, String imagen) {
		this.numero = numero;
		this.paisDeExpedicion = paisDeExpedicion;
		this.fechaDeVencimiento = fechaDeVencimiento;
		this.imagen = imagen;
	}

	//Getters & Setters

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPaisDeExpedicion() {
		return paisDeExpedicion;
	}

	public void setPaisDeExpedicion(String paisDeExpedicion) {
		this.paisDeExpedicion = paisDeExpedicion;
	}

	public String getFechaDeVencimiento() {
		return fechaDeVencimiento;
	}

	public void setFechaDeVencimiento(String fechaDeVencimiento) {
		this.fechaDeVencimiento = fechaDeVencimiento;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
}
