package RentadoraModelo;

public class Pagos {
	
	public MetodoTransaccion pasarela;
	private double monto;
	private String cuenta;
	private String transaccion;
	private boolean estado;
	
	
	//Constructor 
	
	
	public Pagos(String pasarela, double monto, String cuenta) {
		super();
		
		
		this.monto = monto;
		this.cuenta = cuenta;
	}
	
	//Getters y Setters
	
	public MetodoTransaccion getMetodo() {
		return pasarela;
	}
	public void setMetodo(MetodoTransaccion metodo) {
		this.pasarela = metodo;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getTransaccion() {
		return transaccion;
	}
	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}	
		

}
