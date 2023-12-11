package RentadoraModelo;

public class Empleado extends Usuario {

	protected String sede;
	private boolean activo;
	
	//Constructor
	
	public Empleado(String nombre, String fechaDeNacimiento, String nacionalidad,
			String documentoIdentidad, String imagen, String rol, String sede, boolean activo) {
		super(nombre, fechaDeNacimiento, nacionalidad, documentoIdentidad, imagen, rol);
		this.sede = sede;
		this.activo = activo;
	}
	
	
	//Getters y Setters

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	//Methods
	
	private void renunciar() {
		System.out.println("RENUNCIO!!");	
		setActivo(false);
	}
	
	
	
}
