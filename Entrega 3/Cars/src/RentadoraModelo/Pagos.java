package RentadoraModelo;

import java.io.IOException;

import javax.swing.JTextField;

public class Pagos {
	
	private MetodoTransaccion pasarela;
	private double monto;
	private String cuenta;
	private String transaccion;
	private boolean estado;
	
	
	//Constructor 
	
	
	public Pagos(String pasarela, double monto, String cuenta,boolean estado) {
		super();
		this.monto = monto;
		this.cuenta = cuenta;
		this.estado = estado;
		//Instanciacion dinamica
		
		try {
			
			Class clase = Class.forName("RentadoraModelo."+pasarela);
			this.pasarela = (MetodoTransaccion) clase.getDeclaredConstructor(null).newInstance(null);
			
			
		}
		catch (ClassNotFoundException e)
		{
		System.out.println("No existe la clase " + pasarela);
		}
		catch (Exception e)
		{
		System.out.println("Hubo otro error construyendo la agenda telefónica: " + e.getMessage());
		e.printStackTrace();
		}
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
	
	public void generarFactura(String pasarelita) {
		pasarela.generarFactura(cuenta, monto, pasarelita);
	}

}
