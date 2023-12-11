package RentadoraModelo;

public class CarroNoDisponible extends Vehiculo{
	
	//Attributes
	
	private String motivoNoDisponible;
	private String fechaRehabilitado;
	
	//Constructor
	
	public CarroNoDisponible(String placa, String marca, String modelo, String color, String tipoDeTransmision,
			String combustible, int capacidad, boolean reservado,boolean alquilado, String sede, Categoria categoria) {
		super(placa, marca, modelo, color, tipoDeTransmision, combustible, capacidad,reservado, alquilado, sede, categoria);
		this.motivoNoDisponible = "Dañado";
		this.fechaRehabilitado = "22/10/2023";
		
	}

	public String getMotivoNoDisponible() {
		return motivoNoDisponible;
	}

	public void setMotivoNoDisponible(String motivoNoDisponible) {
		this.motivoNoDisponible = motivoNoDisponible;
	}

	public String getFechaRehabilitado() {
		return fechaRehabilitado;
	}

	public void setFechaRehabilitado(String fechaRehabilitado) {
		this.fechaRehabilitado = fechaRehabilitado;
	}
		
	
}