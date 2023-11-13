package RentadoraModelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JTable;

import java.util.Properties;
import java.util.Set;

public class CargaArchivos {

	public ArrayList<String> cargarSedes(){
		
		Properties pSedes = new Properties();
		try {
		pSedes.load(new FileInputStream(new File("./RentadoraStorage/Sedes.txt")));
		ArrayList<String> sedes = new ArrayList<>();
		for (Object sede: pSedes.keySet()) {
			sedes.add((String)sede);
		}
		return sedes;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
public ArrayList<String> cargarCategoria(){
		
		Properties pSedes = new Properties();
		try {
		pSedes.load(new FileInputStream(new File("./RentadoraStorage/Tarifa.txt")));
		ArrayList<String> sedes = new ArrayList<>();
		for (Object sede: pSedes.keySet()) {
			sedes.add((String)sede);
		}
		return sedes;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object[][] cargarAutos(String sede){
		Properties pCarros = new Properties();
		try {
			pCarros.load(new FileInputStream(new File("./RentadoraStorage/Vehiculo.txt")));
			ArrayList<String[]> carros = new ArrayList<>();
			
			Set<Entry<Object, Object>> entrySet = pCarros.entrySet();
			
			
	        for (Entry<Object, Object> entry : entrySet) {
	            Object placa = entry.getKey();
	            String valores = (String) entry.getValue();
	            String[] carro = valores.split(";");
	            
	            if (carro[9].equals(sede) && carro[7].equals("false")) {
	            	carros.add(carro);
	            }
	        }
	        return carros.toArray(new Object[0][0]);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
	//LOGIN RELATED
	
	public Properties cargarLogin() {
		Properties pLogin = new Properties();
		try {
			pLogin.load(new FileInputStream(new File("./RentadoraStorage/Login.txt")));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return pLogin;
	}
	
	public void guardarNuevoUsuario(ArrayList<String> infoUsuario) {
		String user = infoUsuario.get(0);
		infoUsuario.remove(0);
		Properties pLogin = cargarLogin();
		String mensaje = String.join(";", infoUsuario);
		pLogin.put(user, mensaje);
		guardarArchivo(pLogin,"Login");
		
	}
	
	public void guardarArchivo(Properties p,String name) {
		try {
		p.save(new FileOutputStream(new File ("./RentadoraStorage/"+name+".txt")),"");
		}
		catch(FileNotFoundException e) {
			System.err.println("File not found: " + e.getMessage());
		}
	}
}
