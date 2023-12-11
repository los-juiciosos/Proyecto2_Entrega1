package RentadoraModelo;

public class MetodoPago {
	
	//Attributes
	
	private String tipoDePago;
	private String numeroTarjetaCredito;
	private String fechaVencimiento;
	private boolean estadoTarjeta;
	
	//Constructors
	
	public MetodoPago(String tipoDePago, String numeroTarjetaCredito, String fechaVencimiento
			,boolean estadoTarjeta) {
		super();
		this.tipoDePago = tipoDePago;
		this.numeroTarjetaCredito = numeroTarjetaCredito;
		this.fechaVencimiento = fechaVencimiento;
		this.estadoTarjeta = estadoTarjeta;
	}
	
	//Getters & Setters
	
	public String getTipoDePago() {
		return tipoDePago;
	}

	public void setTipoDePago(String tipoDePago) {
		this.tipoDePago = tipoDePago;
	}

	public String getNumeroTarjetaCredito() {
		return numeroTarjetaCredito;
	}

	public void setNumeroTarjetaCredito(String numeroTarjetaCredito) {
		this.numeroTarjetaCredito = numeroTarjetaCredito;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public boolean isEstadoTarjeta() {
		return estadoTarjeta;
	}

	public void setEstadoTarjeta(boolean estadoTarjeta) {
		this.estadoTarjeta = estadoTarjeta;
	}
	

}