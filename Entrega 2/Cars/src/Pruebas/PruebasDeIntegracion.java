package Pruebas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

import RentadoraModelo.CargaArchivos;
import RentadoraModelo.Categoria;
import RentadoraModelo.Cliente;
import RentadoraModelo.OtrosConductores;
import RentadoraModelo.Reserva;
import RentadoraModelo.Sede;
import RentadoraModelo.Vehiculo;

public class PruebasDeIntegracion {
	
	
	Cliente cliente;
	CargaArchivos cargaArchivos;
	Vehiculo vehiculo;
	
	@BeforeEach
	void crearCliente() {
		
		this.cargaArchivos = new CargaArchivos();
		String user = "Guido";
		Properties pLogin = cargaArchivos.cargarLogin();
		this.cliente = cargaArchivos.cargarCliente(pLogin,user);
	}
	
	@AfterEach
	private void revertirReserva() {
				
		Properties pReserva = new Properties();
		Properties pVehiculos = new Properties();
		
		try {
			
			pVehiculos.load(new FileInputStream(new File("./RentadoraStorage/Vehiculo.txt")));
			String infoReserva = (String) pVehiculos.get(vehiculo.getPlaca());
			String[] listaInfoLogin = infoReserva.split(";");
			listaInfoLogin[7] = "false";
			String informacionJunta = String.join(";", listaInfoLogin);
			pVehiculos.setProperty(vehiculo.getPlaca(),informacionJunta);
			FileOutputStream fileOutputStream = new FileOutputStream(new File ("./RentadoraStorage/Vehiculo.txt"));
			pVehiculos.store(fileOutputStream, "uwu");
			
			pReserva.load(new FileInputStream(new File("./RentadoraStorage/Reservas.txt")));
			pReserva.remove("1Guido");
			FileOutputStream fileOutputStream2 = new FileOutputStream(new File ("./RentadoraStorage/Reservas.txt"));
			pReserva.store(fileOutputStream2, "uwu");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
    @DisplayName("Hacer Reserva")
	void hacerReserva() {
		Reserva.resetId();
		Sede sede = cargaArchivos.cargarSedeIndiv("RadiatorSpring");
		Categoria categoria = new Categoria("lujo");
		Reserva reserva = cliente.iniciarReserva("01/01/2024", "18\\:00","05/01/2024","18\\:00",
				sede, categoria,"RadiatorSpring");
		vehiculo = reserva.getCarroEscogido();
		
		assertEquals(reserva.getCategoriaVehiculo().getNombre(),"lujo");
		
		assertEquals(reserva.getSedeFinal().getNombre(),"RadiatorSpring");
		
		assertEquals(reserva.getFechaDevolucion(),"05/01/2024");
		
		
	}
	
	@Test
    @DisplayName("Cambiar Reserva")
	void cambiarReserva() throws CargaArchivoException {
		Reserva.resetId();
		Sede sede = cargaArchivos.cargarSedeIndiv("RadiatorSpring");
		Categoria categoria = new Categoria("lujo");
		Reserva reserva = cliente.iniciarReserva("01/01/2024", "18\\:00","05/01/2024","18\\:00",
				sede, categoria,"RadiatorSpring");
		reserva.setTarifa();
		cargaArchivos.cargarTarReserva(reserva, cliente);
		vehiculo = reserva.getCarroEscogido();
		
		cargaArchivos.modificarReserva("1Guido", "Sede Entrega", "Emeryville");
		
		String sedeModificada = cargaArchivos.cargarReserva("1Guido")[6];
		
		assertEquals(sedeModificada,"Emeryville");
		
	}
	
	@Test
    @DisplayName("Agregar conductores a una reserva")
	void agregarConductores() {
		Reserva.resetId();
		Sede sede = cargaArchivos.cargarSedeIndiv("RadiatorSpring");
		Categoria categoria = new Categoria("lujo");
		Reserva reserva = cliente.iniciarReserva("01/01/2024", "18\\:00","05/01/2024","18\\:00",
				sede, categoria,"RadiatorSpring");
		reserva.setId(1);
		vehiculo = reserva.getCarroEscogido();
		
		OtrosConductores conductorAdicional = new OtrosConductores("Hanni","17","Australia",
				"06/09/2004","imagen.jpg");
		conductorAdicional.registrarLicencia("1Guido");
		
		assertEquals(conductorAdicional.getLicencia().getPaisDeExpedicion(),"Australia");
		
	}

}
