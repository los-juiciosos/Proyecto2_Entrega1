package Interfaz.Principal;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import javax.swing.JFileChooser;

import RentadoraModelo.Sede;

public class Verify{
	
	public boolean checkLogin(Properties pLogin, String user, String password) {
		
		boolean verify = false;
		Set<Object> llavesLogin = pLogin.keySet();
		if (llavesLogin.contains(user)) {
			String infoLogin = (String) pLogin.get(user);
			String[] listaInfoLogin = infoLogin.split(";");
			
			if (password.equals(listaInfoLogin[0])) {
				verify = true;
			}
		}
		return verify;
	}
	
	public String getRol(Properties pLogin, String user) {
		
		String infoLogin = (String) pLogin.get(user);
		String[] listaInfoLogin = infoLogin.split(";");
		String mensaje = listaInfoLogin[listaInfoLogin.length -1];
		
		return mensaje;
	}
	
	
	public String chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        String selectedFileName = null;

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            selectedFileName = selectedFile.getName();
        }
        
        return selectedFileName;
    }
	
	public boolean verifyExistUser(Properties pLogin, String user) {
		boolean verify = true;
		Set<Object> llavesLogin = pLogin.keySet();
		
		if (llavesLogin.contains(user)) {
			verify = false;
		}
		
		return verify;
		
	}
	
public boolean verifyIdReserva(Properties pReserva, String id,String sedeActual) {
		boolean verify = false;
		Set<Object> llavesLogin = pReserva.keySet();
		if (llavesLogin.contains(id)) {
			String infoLogin = (String) pReserva.get(id);
			String[] listaInfoLogin = infoLogin.split(";");
			String mensaje = listaInfoLogin[5];
			
			if (mensaje.equals(sedeActual)) {
				verify = true;
			}
		}
		return verify;
	}
	
	public boolean verifyLleno(ArrayList<String> listaInfo) {
		boolean verify = true;
		for (String dato: listaInfo) {
			if (dato == null) {
				verify = false;
			}
			else if(dato.equals("")) {
				verify = false;
			}
		}
		return verify;
	}
	
	public boolean verifyTarjetaBloqueada(Properties pLogin, String user) {
		
		boolean verify = true;
		
		Set<Object> llavesLogin = pLogin.keySet();
		if (llavesLogin.contains(user)) {
			String infoLogin = (String) pLogin.get(user);
			String[] listaInfoLogin = infoLogin.split(";");
			String mensaje = listaInfoLogin[16];
			if (mensaje.equals("false")) {
				verify = false;
			}
		}
		
		return verify;
	}
	
	public boolean verifyFechaValida(Sede sede, String horaLlegada , String horaDevolucion) {
		boolean revisar = true;
		String horaInicio = sede.getHoraApertura();
		String horaCierre = sede.getHoraCierre();
		
		try {
            SimpleDateFormat sdf = new SimpleDateFormat("mm:HH");
            Date startTime = sdf.parse(horaInicio);
            Date endTime = sdf.parse(horaCierre);
            Date checkTime = sdf.parse(horaLlegada);
            Date checkDevolTime = sdf.parse(horaDevolucion);

            if (checkTime.after(startTime) && checkTime.before(endTime)) {
            	if (checkDevolTime.after(startTime) && checkDevolTime.before(endTime)) {
                    revisar = true;
                } else {
                	revisar = false;
                }
            } else {
            	revisar = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		return revisar;
	}
	
	public boolean verifyExistPlaca(Properties pVehiculos, String placa) {
		boolean verify = false;
		Set<Object> llavesLogin = pVehiculos.keySet();
		
		if (llavesLogin.contains(placa)) {
			verify = true;
		}
		
		return verify;
	}
	
	
	
	
}