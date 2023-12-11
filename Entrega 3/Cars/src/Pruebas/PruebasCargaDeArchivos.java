package Pruebas;

import org.junit.jupiter.api.Test;

import RentadoraModelo.AdministradorGeneral;
import RentadoraModelo.AdministradorLocal;
import RentadoraModelo.CargaArchivos;
import RentadoraModelo.Cliente;
import RentadoraModelo.EmpleadoInventario;
import RentadoraModelo.EmpleadoMostrador;
import RentadoraModelo.Reserva;
import RentadoraModelo.Sede;
import RentadoraModelo.Seguros;
import RentadoraModelo.Tarifa;
import RentadoraModelo.Vehiculo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Properties;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;


public class PruebasCargaDeArchivos {
	
	CargaArchivos cargaArchivos = new CargaArchivos();
	
	//Login.txt
	Cliente cliente;
	EmpleadoInventario empleadoInventario;
	EmpleadoMostrador empleadoMostrador;
	AdministradorGeneral administradorGeneral;
	AdministradorLocal administradorLocal;
	
	//Sedes.txt
	Sede RadiatorSpring;
	Sede CarburetorCanyon;
	Sede Emeryville;
	Sede MotorSpeedway;
	
	//Vehiculos.txt
	Vehiculo tractor;
	Vehiculo lujo;
	
	//Tarifa.trxt
	Tarifa tarifa;
	
	//Seguros.txt
	Seguros seguros;
	
	//Reservas.txt
	Reserva reserva;
	

	@Test
	@DisplayName("Exception en CargarReserva()")
	void testCargarReservaException() {
		
		assertThrows(CargaArchivoException.class, () -> {
			cargaArchivos.cargarReserva("Esta id no existe");
		});
	}
	
	@Test
	@DisplayName("Exception en cargarVehiculoIndiv()")
	void testCargarVehiculoException() {
		
		assertThrows(CargaArchivoException.class, () -> {
			cargaArchivos.cargarVehiculoIndiv("Esta id tampoco existe");
		});
	}
	
	@Test
	@DisplayName("Prueba para cargar Login.txt")
	void testLogin_txt() {
		
		Properties pLogin = cargaArchivos.cargarLogin();
		
		cliente = cargaArchivos.cargarCliente(pLogin,"Guido");
		empleadoInventario = cargaArchivos.cargarEmpInventario(pLogin, "Sally");
		empleadoMostrador = cargaArchivos.cargarMostrador(pLogin, "Rayo");
		administradorGeneral = cargaArchivos.cargarGeneral(pLogin, "DocHudson");
		administradorLocal = cargaArchivos.cargarLocal(pLogin, "Mate");
		
		//Probamos un atributo de cada clase
		assertEquals(cliente.getNacionalidad(), "Italia");
		assertEquals(empleadoInventario.getNacionalidad(), "Alemana");
		assertEquals(empleadoMostrador.getNacionalidad(), "Americana");
		assertEquals(administradorGeneral.getNacionalidad(), "EstadosUnidos");
		assertEquals(administradorLocal.getNacionalidad(), "EstadosUnidos");
	}
	
	@Test
	@DisplayName("Prueba para cargar Sedes.txt")
	void testSedes_txt() {
		
		RadiatorSpring = cargaArchivos.cargarSedeIndiv("RadiatorSpring");
		CarburetorCanyon = cargaArchivos.cargarSedeIndiv("CarburetorCanyon");
		Emeryville = cargaArchivos.cargarSedeIndiv("Emeryville");
		MotorSpeedway = cargaArchivos.cargarSedeIndiv("MotorSpeedway");
		
		assertEquals(RadiatorSpring.getUbicacion(), "cra7#21-42");
		assertEquals(CarburetorCanyon.getUbicacion(), "cra80#41-29");
		assertEquals(Emeryville.getUbicacion(), "cra12#12-13");
		assertEquals(MotorSpeedway.getUbicacion(), "cra1#79-42");
		
	}
	
	@Test
	@DisplayName("Prueba para cargar Vehiculos.txt")
	void testVehiculos_txt() {
		
		Properties pVehiculos = cargaArchivos.cargarVehiculos();
		
		tractor = cargaArchivos.cargarVehiculoIndiv(pVehiculos,"LAA743");
		lujo = cargaArchivos.cargarVehiculoIndiv(pVehiculos,"WOT712");
		
		assertEquals(tractor.getCategoria().getNombre(), "tractor");
		assertEquals(lujo.getCategoria().getNombre(), "lujo");
		
	}
	
	@Test
	@DisplayName("Prueba para cargar Seguros.txt")
	void testSeguros_txt() {
		
		Properties pSeguros = cargaArchivos.cargarSeguros();
		
		assertEquals(pSeguros.get("SoloChoques"), "80000");
		
	}
	
	@Test
	@DisplayName("Prueba para cargar Reservas.txt")
	void testrReservas_txt() throws CargaArchivoException {
		
		String[] reserva = cargaArchivos.cargarReserva("2Guido");
		
		assertEquals(reserva[4], "WOT712");
		
	}
	
	@Test
	@DisplayName("Prueba para cargar Tarifa.txt")
	void testrTarifa_txt() throws CargaArchivoException {
		
		ArrayList<String> tarifas = cargaArchivos.cargarCategoria();
		
		assertEquals(tarifas.get(0), "todoterreno");
		
	}
}
