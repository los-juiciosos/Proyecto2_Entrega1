package RentadoraModelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

public abstract class MetodoTransaccion {
	
	public void guardarTxt(String numeroCuenta,double monto, String pasarela) {
		Properties pUsuario = new Properties();
		try {
		
			pUsuario.load(new FileInputStream(new File("./transacciones/"+pasarela+"Trans.txt")));
			String infoUpdated = numeroCuenta + ";" + monto + ";" + pasarela + ";";
			pUsuario.put(numeroCuenta, infoUpdated);
			
			
			try {
				pUsuario.save(new FileOutputStream(new File ("./transacciones/"+pasarela+"Trans.txt")),"");
				}
				catch(FileNotFoundException e) {
					System.err.println("File not found: " + e.getMessage());
				}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getMetodo() {
		return null;
	}
	
	public boolean cobrar(String numeroCuenta) {
		return true;
	}
	
	public void bloquearTarjeta(String nombre) {
		
		Properties pUsuario = new Properties();
		try {
		
			pUsuario.load(new FileInputStream(new File("./RentadoraStorage/Login.txt")));
			String infoUsuario = (String) pUsuario.get(nombre);
			String[] listaUsuario = infoUsuario.split(";");
			listaUsuario[16] = "false";
			String infoUpdated = String.join(";", listaUsuario);
			pUsuario.put(nombre, infoUpdated);
			guardarLogin(pUsuario,"Login");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean verificar() {
		return true;
	}
	
	public void generarFactura(String numeroCuenta,double monto, String pasarela) {
		
	}
	
	private void guardarLogin(Properties p,String name) {
		try {
		p.save(new FileOutputStream(new File ("./RentadoraStorage/"+name+".txt")),"");
		}
		catch(FileNotFoundException e) {
			System.err.println("File not found: " + e.getMessage());
		}
	}
}
