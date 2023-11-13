package Interfaz.Principal;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import javax.swing.JFileChooser;

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
	
	
	
}