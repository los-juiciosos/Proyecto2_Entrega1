package RentadoraConsola;

import RentadoraModelo.AdministradorGeneral;
import RentadoraModelo.AdministradorLocal;
import RentadoraModelo.Alquiler;
import RentadoraModelo.Categoria;
import RentadoraModelo.Cliente;
import RentadoraModelo.EmpleadoInventario;
import RentadoraModelo.EmpleadoMostrador;
import RentadoraModelo.OtrosConductores;
import RentadoraModelo.RentadoraCarros;
import RentadoraModelo.Reserva;
import RentadoraModelo.Sede;
import RentadoraModelo.Usuario;
import RentadoraModelo.Vehiculo;

import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.FileNotFoundException;
import java.io.File;

public class Consola {

	//Attributes
	
	private RentadoraCarros rentadora;
	
	//Constructor
	
	public Consola() {
			this.rentadora = new RentadoraCarros("Cuchau Motors");
			ejecutarConsola();
		}

	public void ejecutarConsola() {
		System.out.println("===============================================================");
		System.out.println("¡¡¡Bienvenido a CUCHAU MOTORS!!!");
		System.out.println("Donde prestamos carros más rapidos que el propio Rayo McQueen");
		System.out.println("===============================================================");
		
		Scanner reader = new Scanner(System.in);
		boolean flag = true;
		while (flag == true) {
			System.out.println("======= MENU PRINCIPAL ============");
			System.out.println("Escriba la opción que desea ejecutar");
			System.out.println("1. Registrarse en CUCHAU MOTORS (Nueva Cuenta para clientes)");
			System.out.println("2. Logearse en CUCHAU MOTORS (Cuenta Existente)");
			System.out.println("0. Salir de esta maravillosa aplicacion (Adios, CUCHAU)");
			
			String answer = reader.next();
			Properties pLogin = new Properties();
			
			//PRIMERA OPCION
			if (answer.equals("1")) {
				System.out.println("Cual va a ser su nuevo User?");
				answer = reader.next();
				try {
				pLogin.load(new FileInputStream(new File("./RentadoraStorage/Login.txt")));
				Set<Object> llavesLogin = pLogin.keySet();
				
				if (llavesLogin.contains(answer)) {
					System.out.println("Lo siento, el usuario está tomado, tiene que ser mas creativo");
					System.out.println("No se guardo su login");
				}
				else {
					String userLogin = answer;
					ArrayList<String> datosPersonales = new ArrayList<String>();
					
					System.out.println("Digite su contraseña y recuerdela porfavor");
					datosPersonales.add(reader.next());
					System.out.println("Ahora le pedire unos datos personales antes de crear su cuenta");
					System.out.println("PORFAVOR NO INCLUIR ESPACIOS EN LAS RESPUESTAS \n");
					System.out.println("Cuál es su nombre completo");
					datosPersonales.add(reader.next());
					System.out.println("Cual es su fecha de nacimiento en formato dd/mm/YYYY");
					datosPersonales.add(reader.next());
					System.out.println("Cuál es su nacionalidad");
					datosPersonales.add(reader.next());
					System.out.println("Cuál es su documento de identidad");
					datosPersonales.add(reader.next());
					datosPersonales.add("Imagen.pdf");
					System.out.println("Cuál es su numero de celular");
					datosPersonales.add(reader.next());
					System.out.println("Cuál es su numero de email");
					datosPersonales.add(reader.next());
					System.out.println("Cuál es su metodo de pago");
					datosPersonales.add(reader.next());
					System.out.println("Cuál es el número de su tarjeta de credito");
					datosPersonales.add(reader.next());
					System.out.println("Cuando vence su tarjeta de credito en mm/YYYY");
					datosPersonales.add(reader.next());
					System.out.println("Cual es el número de su licencia de conducción");
					datosPersonales.add(reader.next());
					System.out.println("En que país se expidió la licencia");
					datosPersonales.add(reader.next());
					System.out.println("Cuando vence su licencia en dd/mm/YYYY");
					datosPersonales.add(reader.next());
					datosPersonales.add("true");
					datosPersonales.add("cliente");

					String informacionJunta = String.join(";", datosPersonales);
					 
					pLogin.put(userLogin, informacionJunta);
				}
				
				guardarLogin(pLogin,"Login");
				
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			//SEGUNDA OPCION
			
			else if (answer.equals("2")) {
				System.out.println("Ingrese su User");
				answer = reader.next();
				try {
					pLogin.load(new FileInputStream(new File("./RentadoraStorage/Login.txt")));
					Set<Object> llavesLogin = pLogin.keySet();
					
					if (llavesLogin.contains(answer)) {
						System.out.println("Ingrese su contraseña");
						String infoLogin = (String) pLogin.get(answer);
						String[] listaInfoLogin = infoLogin.split(";");
						String password = reader.next();
						
						if (password.equals(listaInfoLogin[0])) {
							String rolUsuario = (String) listaInfoLogin[listaInfoLogin.length-1];
							menuUsuario(rolUsuario, listaInfoLogin,answer);
						}
						
						else {
							System.out.println("Contraseña Incorrecta, no tiene acceso");
						}
					}
					
					else {
						System.out.println("No está en nuestro sistema, por favor registrese");
					}
					
					
					}
				catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
			
			//OPCION SALIDA
			
			else if (answer.equals("0")) {
				flag = false;
			}
			
			else {
				System.out.println("Ingrese una opcion valida");
			}
			
		}
		
	}
	
	//LOAD SPECIFIC MENU BY ROLE
	
	public void menuUsuario(String rol, String[] info, String user) {
		
		if (rol.equals("cliente")) {
			Cliente usuarioActual =  new Cliente(info[1],info[2],info[3],info[4],
										info[5],rol,info[6],info[7],info[8],info[9],
										info[10],info[11],info[12],info[13],Boolean.parseBoolean(info[14]));
			menuCliente(usuarioActual,user);
		}
		else if (rol.equals("local")) {
			AdministradorLocal usuarioActual = new AdministradorLocal(info[1],info[2],
					info[3],info[4],info[5],rol,info[6], Boolean.parseBoolean(info[7]));
			menuLocal(usuarioActual,user);
		}
		else if (rol.equals("general")) {
			AdministradorGeneral usuarioActual = new AdministradorGeneral(info[1],info[2],
					info[3],info[4],info[5],rol);
			menuGeneral(usuarioActual,user);
		}
		else if (rol.equals("mostrador")) {
			EmpleadoMostrador usuarioActual = new EmpleadoMostrador(info[1],info[2],
					info[3],info[4],info[5],rol,info[6], Boolean.parseBoolean(info[7]));
			menuMostrador(usuarioActual,user);
		}
		else if (rol.equals("inventario")) {
			EmpleadoInventario usuarioActual = new EmpleadoInventario(info[1],info[2],
					info[3],info[4],info[5],rol,info[6], Boolean.parseBoolean(info[7]));
			menuInventario(usuarioActual,user);
		}
		
	}
	
	//ADMINISTRADOR GENERAL
	
	public void menuGeneral(AdministradorGeneral admin, String rol) {
		Scanner reader = new Scanner(System.in);
		boolean flag = true;
		
		while (flag == true) {
			System.out.println("=======================================================");
			System.out.println("BIENVENIDO NUESTRO QUERIDO ADMINISTRADOR GENERAL " + admin.getNombre());
			System.out.println("=======================================================");
			
			System.out.println("Escoja una de las siguientes opciones");
			System.out.println("1. Dar de baja un vehiculo");
			System.out.println("2. Registrar nuevo vehiculo");
			System.out.println("3. Modificar Seguros");
			System.out.println("4. Modificar Informacion Sede");
			System.out.println("5. Modificar Tarifa por Categoria");
			System.out.println("6. Revisar Mantenimiento");
			System.out.println("7. Generar Log de un Vehiculo (su historial)");
			System.out.println("0. Cerrar sesion");
			
			String answer = reader.next();
			
			if (answer.equals("1")) {
				System.out.println("===== DAR DE BAJA ====");
				System.out.println("Ingrese la placa del Vehículo que hay que dar de baja");
				String placa = reader.next();
				admin.darBajaVehiculo(placa);
			}
			else if (answer.equals("2")) {
				System.out.println("===== AGREGAR VEHICULO ====");
				System.out.println("Cual es la placa");
				String placaAgregar = reader.next();
				System.out.println("Cual es la marca");
				String marca = reader.next();
				System.out.println("Cual es el modelo");
				String modelo  = reader.next();
				System.out.println("Cual es el color");
				String color = reader.next();
				System.out.println("Cual es el tipo de transmision");
				String transmision = reader.next();
				System.out.println("Cual es el tipo de combustible");
				String combustible = reader.next();
				System.out.println("Cual es la capacidad del vehiculo en número entero");
				String capacidad = reader.next();
				System.out.println("Cual es la sede en la que va a estar");
				String sedeAgregar = reader.next();
				System.out.println("Cual es la categoria del vehiculo");
				String categoriaAgregar = reader.next();
				
				admin.registrarNuevoVehiculo(placaAgregar,marca,modelo,color,transmision,
						combustible,capacidad,sedeAgregar,categoriaAgregar);
				
				System.out.println("Agregado al sistema con exito");
				
			}
			else if (answer.equals("3")) {
				System.out.println("===== MODIFICAR SEGURO =====");
				System.out.println("Cual seguro desea modificar");
				System.out.println("1. TodoRiesgo");
				System.out.println("2. SoloChoques");
				System.out.println("3. Multas");
				String seguro = reader.next();
				System.out.println("Cual va a ser el nuevo precio de este seguro");
				String nuevoPrecio = reader.next();
				
				admin.modificarOfertaSeguros(seguro,nuevoPrecio);
				System.out.println("Se modifico exitosamente");
				
			}
			else if (answer.equals("4")) {
				System.out.println("======= MODIFICAR INFORMACION SEDES======");
				System.out.println("Escriba asi tal cual sin espacios y como se muestra el nombre de la sede a modificar");
				System.out.println("1. RadiatorSpring");
				System.out.println("2. CarburetorCanyon");
				System.out.println("3. Emeryville");
				System.out.println("4. Motor Speedway");
				String sedeModificar = reader.next();
				
				System.out.println("Seleccione que aspecto de la sede va a cambiar");
				System.out.println("1. Direccion");
				System.out.println("2. Hora Apertura");
				System.out.println("3. Hora Cierre");
				String numModificar = reader.next();
				
				System.out.println("Ahora escriba el nuevo valor correspondiete");
				String modificacion = reader.next();
				
				admin.modificarInformacionSedes(sedeModificar,numModificar,modificacion);
				System.out.println("Se modifico exitosamente");
			}
			else if (answer.equals("5")) {
				System.out.println("======== MODIFICAR TARIFAS POR CATEGORIA ======= ");
				System.out.println("Escriba asi tal cual sin espacios como se muestra la categoria a modificar");
				System.out.println("lujo");
				System.out.println("clasico");
				System.out.println("todoterreno");
				System.out.println("vans");
				System.out.println("tractor");
				System.out.println("suv");
				String categoriaEscoger = reader.next();
				System.out.println("Escriba la tarifa nueva de esa categoria");
				String precioCategoria = reader.next();
				admin.modificarTarifaCategoria(categoriaEscoger,precioCategoria);
				
				System.out.println("Se guardo exitosamente");
				
				
			}
			else if (answer.equals("6")) {
				
				System.out.println("====== REVISAR AVISOS DE MANTENIMIENTO =======");
				System.out.println("Se le va a mostrar un carro en mantenimiento");
				String placaRevisar = admin.obtenerPlacaMantenimiento();
				if (placaRevisar == null) {
					System.out.println("Buen trabajo, no hay carros en mantenimiento");
				}
				else {
					System.out.println("Revise el carro de placa "+ placaRevisar);
					System.out.println("Quiere darlo de baja?");
					System.out.println("1. si");
					System.out.println("2. no");
					String opcion = reader.next();
					
					if (opcion.equals("1")) {
						admin.darBajaVehiculo(placaRevisar);
						System.out.println("Dado de baja existosamente!!");
					}
				}
				
			}
			else if (answer.equals("7")) {
				System.out.println("======= GENERAR LOG DE VEHICULO =======");
				System.out.println("Ingrese la placa del carro que quiere revisar su historial");
				String placaLog = reader.next();
				admin.mostrarLog(placaLog);
				System.out.println("Generado Succesfully");
				
				
			}
			else if (answer.equals("0")) {
				flag = false;
			}
			else {
				System.out.println("Ingrese una opcion valida");
			}
			
			
			
		}
		
	}
	
	//ADMINISTRADOR LOCAL
	
	public void menuLocal(AdministradorLocal adminActual, String rol) {
		Properties pSedes = new Properties();
		Scanner reader = new Scanner(System.in);
		boolean flag = true;
		Sede sedeActual = null;
		try {
		pSedes.load(new FileInputStream(new File("./RentadoraStorage/Sedes.txt")));
		
		if (adminActual.getSede().equals("RadiatorSpring")) {
			String stringSede = (String) pSedes.get("RadiatorSpring");
			String[] infoSede = stringSede.split(";");		
			sedeActual = new Sede(infoSede[0],infoSede[1],infoSede[2],infoSede[3]);
			sedeActual.setDisponibilidades();
		}
		
		else if (adminActual.getSede().equals("CarburetorCanyon")) {
			String stringSede = (String) pSedes.get("CarburetorCanyon");
			String[] infoSede = stringSede.split(";");	
			sedeActual = new Sede(infoSede[0],infoSede[1],infoSede[2],infoSede[3]);
			sedeActual.setDisponibilidades();
		}
		
		else if (adminActual.getSede().equals("Emeryville")) {
			String stringSede = (String) pSedes.get("Emeryville");
			String[] infoSede = stringSede.split(";");	
			sedeActual = new Sede(infoSede[0],infoSede[1],infoSede[2],infoSede[3]);
			sedeActual.setDisponibilidades();
		}
		
		else if (adminActual.getSede().equals("MotorSpeedway")) {
			String stringSede = (String) pSedes.get("MotorSpeedway");
			String[] infoSede = stringSede.split(";");	
			sedeActual = new Sede(infoSede[0],infoSede[1],infoSede[2],infoSede[3]);
			sedeActual.setDisponibilidades();
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		while (flag == true) {
			System.out.println("=======================================================");
			System.out.println("BIENVENIDO NUESTRO QUERIDO ADMINISTRADOR LOCAL " + adminActual.getNombre());
			System.out.println("DE LA SEDE: "+ sedeActual.getNombre());
			System.out.println("=======================================================");
			
			System.out.println("Porfavor seleccione una de las siguientes opciones");
			System.out.println("1. Crear Usuario (Cliente) ");
			System.out.println("2. Registrar Trabajador");
			System.out.println("3. Modificar informacion Trabajador");
			System.out.println("0. Salir de la sesion");
			
			String answer = reader.next();
			
			if (answer.equals("1")) {
				System.out.println("============REGISTRAR USUARIO==============");
				System.out.println("Cuál es su nombre completo");
				String nombre = reader.next();
				System.out.println("Cual es su fecha de nacimiento en formato dd/mm/YYYY");
				String nacimiento = reader.next();
				System.out.println("Cuál es su nacionalidad");
				String nacionalidad = reader.next();
				System.out.println("Cuál es su documento de identidad");
				String documento = reader.next();
				System.out.println("Digite el url de una imagen suya (Por ejemplo Juan.jpg)");
				String imagen = reader.next();
				System.out.println("Cuál es su numero de celular");
				String celular = reader.next();
				System.out.println("Cuál es su numero de email");
				String email = reader.next();
				System.out.println("Cuál es su metodo de pago");
				String metodoPago = reader.next();
				System.out.println("Cuál es el número de su tarjeta de credito");
				String tarjetaCredito = reader.next();
				System.out.println("Cuando vence su tarjeta de credito en mm/YYYY");
				String vencimientoCredito = reader.next();
				System.out.println("Cual es el número de su licencia de conducción");
				String numLicencia = reader.next();
				System.out.println("En que país se expidió la licencia");
				String paisLicencia = reader.next();
				System.out.println("Cuando vence su licencia en dd/mm/YYYY");
				String vencimientoLicencia = reader.next();
			
				
				adminActual.crearUsuario(nombre,nacimiento,nacionalidad,documento,imagen,
						celular,email,metodoPago,tarjetaCredito,vencimientoCredito,numLicencia,
						paisLicencia,vencimientoLicencia);
				
				System.out.println("Se guardo con exito, la clave y user será su mismo nombre");
			}
			else if(answer.equals("2")) {
				System.out.println("============REGISTRAR TRABAJADOR==============");
				System.out.println("Cuál es su nombre completo");
				String nombre = reader.next();
				System.out.println("Cual es su fecha de nacimiento en formato dd/mm/YYYY");
				String nacimiento = reader.next();
				System.out.println("Cuál es su nacionalidad");
				String nacionalidad = reader.next();
				System.out.println("Cuál es su documento de identidad");
				String documento = reader.next();
				System.out.println("Digite el url de una imagen suya (Por ejemplo Juan.jpg)");
				String imagen = reader.next();
				System.out.println("En que sede va a trabajar");
				System.out.println("Escriba sin espacios y tal cual a continuacion como se muestra alguna de estas");
				System.out.println("RadiatorSpring");
				System.out.println("CarburetorCanyon");
				System.out.println("Emeryville");
				System.out.println("MotorSpeedway");
				String sedeTrabajar = reader.next();
				if (sedeTrabajar.equals(sedeActual.getNombre())) {
					
				System.out.println("Va a trabajar de mostrador o inventario ");
				System.out.println("1. mostrador");
				System.out.println("2. inventario");
				answer = reader.next();
				String rolTrabajador = "mostrador";
				if (answer.equals("1")) {
					rolTrabajador = "mostrador";
				}
				else if (answer.equals("2")) {
					rolTrabajador = "inventario";
				}
				
				adminActual.registrarTrabajador(nombre, nacimiento, nacionalidad, documento,
						imagen,sedeTrabajar,rolTrabajador);
				}
				else {
					System.out.println("Lo siento está fuera de mi jurisdiccion, no es mi sede correspondiente");
				}
			}
			else if(answer.equals("3")) {
				System.out.println("============MODIFICAR TRABAJADOR==============");
				System.out.println("Escriba el nombre del trabajador que quiere modificar");
				String nombre = reader.next();
				System.out.println("Que aspecto quiere modificar");
				System.out.println("1.nombre");
				System.out.println("2.FechaNacimiento dd/mm/YYYY");
				System.out.println("3.Nacionalidad");
				System.out.println("4.Documento de Identidad");
				System.out.println("5.Imagen");
				System.out.println("6.Sede en la que trabaja");
				System.out.println("7.Rol del empleado (mostrador o inventario)");
				String numero = reader.next();
				System.out.println("Ingrese el nuevo valor de esta area");
				String modificacion = reader.next();
				String resModificar = adminActual.modificarInformacionTrabajador(nombre,numero,modificacion,
						sedeActual.getNombre());	
				
				if (resModificar == null) {
					System.out.println("No se encontro al trabajador que buscaba");
				}
				else if (resModificar == "no") {
					System.out.println("Este empleado esta fuera de mi jurisdiccion, no es de mi sede");
				}
				else {
					System.out.println("Modificacion realizada con exito!!");
				}
			}
			else if(answer.equals("0")) {
				flag = false;
			}
			
		}
	}
	
	//EMPLEADO MOSTRADOR
	
	public void menuMostrador(EmpleadoMostrador empleadoActual,String user) {
		
	
		Properties pSedes = new Properties();
		Scanner reader = new Scanner(System.in);
		boolean flag = true;
		Sede sedeActual = null;
		try {
		pSedes.load(new FileInputStream(new File("./RentadoraStorage/Sedes.txt")));
		
		if (empleadoActual.getSede().equals("RadiatorSpring")) {
			String stringSede = (String) pSedes.get("RadiatorSpring");
			String[] infoSede = stringSede.split(";");		
			sedeActual = new Sede(infoSede[0],infoSede[1],infoSede[2],infoSede[3]);
			sedeActual.setDisponibilidades();
		}
		
		else if (empleadoActual.getSede().equals("CarburetorCanyon")) {
			String stringSede = (String) pSedes.get("CarburetorCanyon");
			String[] infoSede = stringSede.split(";");	
			sedeActual = new Sede(infoSede[0],infoSede[1],infoSede[2],infoSede[3]);
			sedeActual.setDisponibilidades();
		}
		
		else if (empleadoActual.getSede().equals("Emeryville")) {
			String stringSede = (String) pSedes.get("Emeryville");
			String[] infoSede = stringSede.split(";");	
			sedeActual = new Sede(infoSede[0],infoSede[1],infoSede[2],infoSede[3]);
			sedeActual.setDisponibilidades();
		}
		
		else if (empleadoActual.getSede().equals("MotorSpeedway")) {
			String stringSede = (String) pSedes.get("MotorSpeedway");
			String[] infoSede = stringSede.split(";");	
			sedeActual = new Sede(infoSede[0],infoSede[1],infoSede[2],infoSede[3]);
			sedeActual.setDisponibilidades();
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		while (flag == true) {
			System.out.println("=======================================================");
			System.out.println("BIENVENIDO NUESTRO QUERIDO EMPLEADO DE MOSTRADOR " + empleadoActual.getNombre());
			System.out.println("DE LA SEDE: "+ sedeActual.getNombre());
			System.out.println("=======================================================");
			
			System.out.println("Porfavor seleccione una de las siguientes opciones");
			System.out.println("1. Reserva especial (movilizar un carro)");
			System.out.println("2. Mandar a revisar un vehiculo");
			System.out.println("0. Salir de la sesion");
			
			String answer = reader.next();
			
			if (answer.equals("1")) {
				System.out.println("========RESERVA ESPECIAL==========");
				System.out.println("Diga la placa del carro que va a movilizar");
				String placa = reader.next();
				System.out.println("A que sede la va a trasladar");
				String sedeDestino = "RadiatorSpring";
				System.out.println("1. Radiator Spring (7:00-19:00)");
				System.out.println("2. Carburetor Canyon (8:00-20:00)");
				System.out.println("3. Emeryville (7:00-17:00)");
				System.out.println("4. Motor Speedway (11:00-22:00)");
				answer = reader.next();
				if (answer.equals("1")) {
					sedeDestino = "RadiatorSpring";
				}
				else if (answer.equals("2")) {
					sedeDestino = "CarburetorCanyon";
				}
				else if (answer.equals("3")) {
					sedeDestino = "Emeryville";
				}
				else if (answer.equals("4")) {
					sedeDestino = "MotorSpeedway";
				}
				
				System.out.println("Que día lo va a recoger en dd/mm/YYYY");
				String fechaLlegada = reader.next();
				System.out.println("A que hora lo va a recoger en hh:mm");
				String horaLlegada = reader.next();
				System.out.println("Que día llegara a la otra sede en dd/mm/YYYY");
				String fechaDevolucion = reader.next();
				System.out.println("A que hora llegara a la otra sede");
				String horaDevolucion = reader.next();
				
				boolean fechaValida = revisarFechaValida(sedeActual,horaLlegada,horaDevolucion);
				
				if (fechaValida == false) {
					System.out.println("Las horas no estan dentro el horario de atencion intente otravez");
				}
				else {
					String resEspecial = empleadoActual.iniciarReservaEspecial(fechaLlegada,
							horaLlegada,fechaDevolucion,horaDevolucion,sedeDestino,placa);
					
					if (resEspecial == null) {
						System.out.println("Ese carro no está disponible, esta reservado/alquilado ");
					}
					else {
						System.out.println("Se traslado el carro satisfactoriamente");
					}
				}
			}
			else if (answer.equals("2")) {
				System.out.println("Que carro quiere mandar a revisar (por placa)");
				String placaRevisar = reader.next();
				String resRevision = empleadoActual.mandarRevision(placaRevisar);
				if (resRevision == null) {
					System.out.println("El carro no está disponible esta reservado/alquilado");
				}
				else {
					System.out.println("Se mando a revisión correctamente, ahora quedara"
							+ " en manos de nuestros empleados de Inventario");
				}
				
			}
			else if(answer.equals("0")) {
				flag = false;
			}
			else {
				System.out.println("Ingrese una opcion valida");
			}
		}
	}
	
	//EMPLEADO INVENTARIO
	
	public void menuInventario(EmpleadoInventario empleadoActual, String user) {
		
		Scanner reader = new Scanner(System.in);
		boolean flag = true;
		Properties pSedes = new Properties();
		Sede sedeActual = null;
		try {
		pSedes.load(new FileInputStream(new File("./RentadoraStorage/Sedes.txt")));
		
		if (empleadoActual.getSede().equals("RadiatorSpring")) {
			String stringSede = (String) pSedes.get("RadiatorSpring");
			String[] infoSede = stringSede.split(";");		
			sedeActual = new Sede(infoSede[0],infoSede[1],infoSede[2],infoSede[3]);
			sedeActual.setDisponibilidades();
		}
		
		else if (empleadoActual.getSede().equals("CarburetorCanyon")) {
			String stringSede = (String) pSedes.get("CarburetorCanyon");
			String[] infoSede = stringSede.split(";");	
			sedeActual = new Sede(infoSede[0],infoSede[1],infoSede[2],infoSede[3]);
			sedeActual.setDisponibilidades();
		}
		
		else if (empleadoActual.getSede().equals("Emeryville")) {
			String stringSede = (String) pSedes.get("Emeryville");
			String[] infoSede = stringSede.split(";");	
			sedeActual = new Sede(infoSede[0],infoSede[1],infoSede[2],infoSede[3]);
			sedeActual.setDisponibilidades();
		}
		
		else if (empleadoActual.getSede().equals("MotorSpeedway")) {
			String stringSede = (String) pSedes.get("MotorSpeedway");
			String[] infoSede = stringSede.split(";");	
			sedeActual = new Sede(infoSede[0],infoSede[1],infoSede[2],infoSede[3]);
			sedeActual.setDisponibilidades();
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		while (flag == true) {
			System.out.println("=======================================================");
			System.out.println("BIENVENIDO NUESTRO QUERIDO EMPLEADO DE Inventario " + empleadoActual.getNombre());
			System.out.println("DE LA SEDE: "+ sedeActual.getNombre());
			System.out.println("=======================================================");
			System.out.println("Escoja una de las siguientes opciones");
			System.out.println("1. Revisar un Vehiculo");
			System.out.println("0. Cerrar Sesion");
			String answer = reader.next();
			
			if (answer.equals("1")) {
				
				System.out.println("=======REVISION VEHICULO========");
				System.out.println("AHORA SE LE VA A ASIGNAR UNO DE LOS CARROS QUE MANDARON A REVISAR");
				String placaRevisar = empleadoActual.obtenerPlacaRevision();
				if (placaRevisar == null) {
					System.out.println("Felicidades, no tienes que hacer revisiones, no hay carros para revisar");
				}
				else {
					System.out.println("Va a revisar el carro con placa "+placaRevisar);
					System.out.println("1. Mandar a mantenimiento");
					System.out.println("2. Se ve bien para volverlo a poner disponible");
					answer = reader.next();
					boolean mantenimiento = true;
					
					if (answer.equals("1")) {
						mantenimiento = true;
					}
					else if (answer.equals("2")) {
						mantenimiento = false;
					}
					
					empleadoActual.revisarVehículo(placaRevisar,mantenimiento);
				}
			}
			else if (answer.equals("0")) {
				flag = false;
			}
			else {
				System.out.println("Ingrese una opcion valida");
			}
			
		}
		
		
	}
	
	//INDIVIDUAL MENU
	
	public void menuCliente(Cliente clienteActual,String user) {
		Scanner reader = new Scanner(System.in);
		boolean flag = true;
		while (flag == true) {
			System.out.println("=======================================================");
			System.out.println("BIENVENIDO NUESTRO QUERIDO CLIENTE " + clienteActual.getNombre());
			System.out.println("=======================================================");
			Sede sedeActual = null;
			
			boolean sedeFlag = true;
			while (sedeFlag == true) {
			sedeFlag = false;
			System.out.println("Seleccione una sede porfavor");
			System.out.println("Entre parentesis están las horas de atencion de las sedes");
			System.out.println("1. Radiator Spring (7:00-19:00)");
			System.out.println("2. Carburetor Canyon (8:00-20:00)");
			System.out.println("3. Emeryville (7:00-17:00)");
			System.out.println("4. Motor Speedway (11:00-22:00)");
			
			String leerSede = reader.next();
			Properties pSedes = new Properties();
			try {
			pSedes.load(new FileInputStream(new File("./RentadoraStorage/Sedes.txt")));
			
			
			if (leerSede.equals("1")) {
				String stringSede = (String) pSedes.get("RadiatorSpring");
				String[] infoSede = stringSede.split(";");		
				sedeActual = new Sede(infoSede[0],infoSede[1],infoSede[2],infoSede[3]);
				sedeActual.setDisponibilidades();
			}
			
			else if (leerSede.equals("2")) {
				String stringSede = (String) pSedes.get("CarburetorCanyon");
				String[] infoSede = stringSede.split(";");	
				sedeActual = new Sede(infoSede[0],infoSede[1],infoSede[2],infoSede[3]);
				sedeActual.setDisponibilidades();
			}
			
			else if (leerSede.equals("3")) {
				String stringSede = (String) pSedes.get("Emeryville");
				String[] infoSede = stringSede.split(";");	
				sedeActual = new Sede(infoSede[0],infoSede[1],infoSede[2],infoSede[3]);
				sedeActual.setDisponibilidades();
			}
			
			else if (leerSede.equals("4")) {
				String stringSede = (String) pSedes.get("MotorSpeedway");
				String[] infoSede = stringSede.split(";");	
				sedeActual = new Sede(infoSede[0],infoSede[1],infoSede[2],infoSede[3]);
				sedeActual.setDisponibilidades();
			}
			
			else {
				System.out.println("Ingrese una opción valida");
				sedeFlag = true;
			}
			
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
			
			}
			
			System.out.println("Escoga una opcion");
			System.out.println("1. Iniciar una reserva");
			System.out.println("2. Formalizar un alquiler");
			System.out.println("3. Entregar un vehículo (Necesita tener un vehículo alquilado)");
			System.out.println("0. Cerrar Sesion");
			
			String answer = reader.next();
			//RESERVAR UN CARRO
			if (answer.equals("1") && (clienteActual.isEstadoTarjeta() == false)) {
				System.out.println("Lo siento mi estimado cliente pero tiene la tarjeta deshabilitada");
				System.out.println("Si no devuelve el vehiculo no puede seguir reservando");
			}
			
			else if (answer.equals("1")) {
				
				System.out.println("Que día quiere recoger el carro en dd/mm/YYYY");
				String fechaLlegada = reader.next();
				System.out.println("A que hora quiere recoger el carro en HH:mm hora militar");
				String horaLlegada = reader.next();
				System.out.println("Que día va a entregar el carro en dd/mm/YYYY");
				String fechaDevolucion = reader.next();
				System.out.println("A que hora va a entregar el carro en HH:mm hora militar");
				String horaDevolucion = reader.next();
				System.out.println("En que sede va a entregar el carro?");
				System.out.println("1. Radiator Spring");
				System.out.println("2. Carburetor Canyon");
				System.out.println("3. Emeryville");
				System.out.println("4. Motor Speedway");				
				String sedeRegreso = reader.next();
				
				if (sedeRegreso.equals("1")) {
					sedeRegreso = "RadiatorSpring";
				}
				else if (sedeRegreso.equals("2")) {
					sedeRegreso = "CarburetorCanyon";
				}
				else if (sedeRegreso.equals("3")) {
					sedeRegreso = "Emeryville";
				}
				else if (sedeRegreso.equals("4")) {
					sedeRegreso = "MotorSpeedway";
				}
				System.out.println("Que categoría de vehiculo quiere?");
				System.out.println("1. lujo");
				System.out.println("2. clasico");
				System.out.println("3. suv");
				System.out.println("4. vans");
				System.out.println("5. todoterreno");
				System.out.println("6. tractor");
				answer = reader.next();
				Categoria categoriaEscogida = null;
				
				boolean flagCategoria = true;
				while (flagCategoria == true) {
				flagCategoria = false;
				if (answer.equals("1")) {
					categoriaEscogida = new Categoria("lujo");
					categoriaEscogida.setMejorCategoria(categoriaEscogida);
				}
				
				else if (answer.equals("2")) {
					categoriaEscogida = new Categoria("clasico");
					categoriaEscogida.setMejorCategoria(categoriaEscogida);
				}
				else if (answer.equals("3")) {
					categoriaEscogida = new Categoria("suv");
					categoriaEscogida.setMejorCategoria(categoriaEscogida);
				}
				
				else if (answer.equals("4")) {
					categoriaEscogida = new Categoria("vans");
					categoriaEscogida.setMejorCategoria(categoriaEscogida);
				}
				else if (answer.equals("5")) {
					categoriaEscogida = new Categoria("todoterreno");
					categoriaEscogida.setMejorCategoria(categoriaEscogida);
				}
				else if (answer.equals("6")) {
					categoriaEscogida = new Categoria("tractor");
					categoriaEscogida.setMejorCategoria(categoriaEscogida);
				}
				else {
					flagCategoria = true;
					System.out.println("Ingrese una opcion válida porfavor");
				}
				}
				
				boolean fechaValida = revisarFechaValida(sedeActual,horaLlegada,horaDevolucion);
				
				if (fechaValida == true) {
					Reserva reservaSeleccionado = clienteActual.iniciarReserva(fechaLlegada,horaLlegada,
							fechaDevolucion,horaDevolucion,sedeActual,categoriaEscogida,sedeRegreso);
				
					if (reservaSeleccionado == null) {
						System.out.println("No se encontro un vehículo con sus especificaciones lo siento");
					}
					else {
						reservaSeleccionado.setTarifa();
						adicionTarifa(reservaSeleccionado,clienteActual);
						System.out.println("=======================CARACTERISTICAS RESERVA ============================");
						System.out.println("El carro que le fue asignado tiene las siguientes propiedades:");
						System.out.println("Si no coincide con su categoria es que se agotaron, y le dimos una superior");
						System.out.println("===========================================================================");
						System.out.println("placa: " + reservaSeleccionado.getCarroEscogido().getPlaca());
						System.out.println("marca: " + reservaSeleccionado.getCarroEscogido().getMarca());
						System.out.println("modelo: " + reservaSeleccionado.getCarroEscogido().getModelo());
						System.out.println("color: " + reservaSeleccionado.getCarroEscogido().getColor());
						System.out.println("transmision: " + reservaSeleccionado.getCarroEscogido().getTipoDeTransmision());
						System.out.println("combustible: " + reservaSeleccionado.getCarroEscogido().getCombustible());
						System.out.println("categoria: " + reservaSeleccionado.getCarroEscogido().getCategoria().getNombre());
						System.out.println("===========================================================================");
						System.out.println("Ahora le cobraremos el 30% de una tarifa estimada");
						System.out.println("Valor estimado total: " + reservaSeleccionado.getTarifa().getPrecio());
						System.out.println("Se le cobrarará: " + 0.3*reservaSeleccionado.getTarifa().getPrecio());
						System.out.println("Transaccion Hecha Satisfactoriamente");
						System.out.println("Reserva Guardada Satisfactoriamente");
						System.out.println("===========================================================================");
						System.out.println("EL ID DE SU RESERVA ES: " + reservaSeleccionado.getId()+clienteActual.getNombre());
						System.out.println("RECUERDE EL ID PORQUE SINO NO PODRA FINALIZAR EL ALQUILER");
						System.out.println("===========================================================================");
					}
				}
				else {
					System.out.println("Lo siento pero las horas ingresadas no son validas");
					System.out.println("Revise nuevamente los horarios de atencion correspondientes");
				}
			}
			//ALQUILAR UN CARRO
			else if (answer.equals("2")) {
				boolean flagAlquiler = true;
				
				while (flagAlquiler == true) {
				System.out.println("===============MENU ALQUILER =============");
				System.out.println("Ahora vamos a terminar nuestro alquiler");
				System.out.println("Ingrese porfavor el id de su reserva que le fue asignado");
				String idReserva = reader.next();
				Properties pAlquiler = new Properties();
				try {
					pAlquiler.load(new FileInputStream(new File("./RentadoraStorage/Reservas.txt")));
					Set<Object> llavesReserva = pAlquiler.keySet();
					if (llavesReserva.contains(idReserva)) {
						String infoReservaAl = (String) pAlquiler.get(idReserva);
						String[] listaReservaAl = infoReservaAl.split(";");
						
						Alquiler alquilerActual = new Alquiler(listaReservaAl[6],listaReservaAl[7]);
						System.out.println("Quiere adicionar nuevos conductores?");
						System.out.println("1. Si");
						System.out.println("2. No");
						answer = reader.next();
						if (answer.equals("1")) {
							int numConductores = calcularOtrosConductores(idReserva);
							double precioActual = Double.parseDouble(listaReservaAl[7]);
							double nuevoPrecio = (numConductores*(0.10*precioActual))+precioActual;
							listaReservaAl[7] = Double.toString(nuevoPrecio);	
							alquilerActual.setPrecioTarifa(Double.toString(nuevoPrecio));
						}
						
						System.out.println("Desea cambiar datos de su reserva?");
						System.out.println("1. Si");
						System.out.println("2. No");
						answer = reader.next();
						String[] listaFinalReserva = null;
						if (answer.equals("1")) {
							listaFinalReserva = modificarDatosReserva(listaReservaAl);
						}
						else {
							listaFinalReserva = listaReservaAl;
						}
						System.out.println("Que seguro desea?");
						System.out.println("1. TodoRiesgo");
						System.out.println("2. Solo choques");
						System.out.println("3. Multas");
						System.out.println("4. Ninguno (no recomendado)");
						answer = reader.next();
						
						alquilerActual.formalizarAlquiler(listaFinalReserva,idReserva,user);
						System.out.println("Precio Final a Pagar: " + alquilerActual.getPrecioTarifa());
						System.out.println("Pagando restante del 30% abonado...");
						System.out.println("Pago realizado con exito, su tarjeta quedo deshabilitada, hasta que devuelva el vehiculo");
						System.out.println("PORFAVOR RECUERDE LA PLACA DE SU CARRO PARA ENTREGARLO");
						System.out.println("Gracias por confiar en nosotros nos vemos pronto!!!!");
						flagAlquiler = false;
					}
					else{
						System.out.println("No se encontro ninguna reserva asociada");
						System.out.println("No se realizo ningun alquiler");
						flagAlquiler = false;
					}
					
					
				}
				catch (Exception e) {
					e.printStackTrace();
				}
					flagAlquiler = false;
				}
				
			}
		
			else if (answer.equals("3")) {
				
				System.out.println("Ingrese la placa del vehiculo que va a devolver en MAYUSCULAS");
				String placaDevolver = reader.next();
				System.out.println("Ingrese el id de su reserva");
				String id = reader.next();
				String entregaSuccess = clienteActual.entregarVehiculo(id,placaDevolver);
				
				if (entregaSuccess == null) {
					System.out.println("No se encontro el alquiler a devolver que especifica");
				}
				
				else {
					System.out.println("Se entrego el vehiculo con exito");
					System.out.println("Muchas gracias, su tarjeta quedo rehabilitada");
				}
				
			}
			else if (answer.equals("0")) {
				flag = false;
			}
			else {
				System.out.println("Ingrese una opción valia");
			}
			
		}
		
	}
	
	//GUARDAR EN LOGIN.TXT
	
	public void guardarLogin(Properties p,String name) {
		try {
		p.save(new FileOutputStream(new File ("./RentadoraStorage/"+name+".txt")),"");
		}
		catch(FileNotFoundException e) {
			System.err.println("File not found: " + e.getMessage());
		}
	}
	
	public boolean revisarFechaValida(Sede sede, String horaLlegada , String horaDevolucion) {
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
	
	public int calcularOtrosConductores(String idReserva) {
		Scanner reader = new Scanner(System.in);
		int numRetorno = 0;
		boolean flagAdicional = true;
		while (flagAdicional == true) {
		flagAdicional = false;
		System.out.println("Van a haber conductores adicionales?");
		System.out.println("1. Sí");
		System.out.println("2. No");
		String answer = reader.next();
		
		if (answer.equals("1")){
			
			boolean flagRegistrar = true;
			while (flagRegistrar == true) {
				System.out.println("Vamos a registrar a otro conductor");
				System.out.println("Le pediremos unos datos de la Licencia del otro conductor");
				System.out.println("Cual es su nombre?");
				String nombre = reader.next();
				System.out.println("Cual es el numero de licencia");
				String numLicencia = reader.next();
				System.out.println("Cual es el país donde expidio la licencia");
				String paisLicencia = reader.next();
				System.out.println("Cuando vence su licencia en dd/mm/YYYY");
				String fechaLicencia = reader.next();
				System.out.println("Inserte un url de su imagen (puede ser solo su nombre.jpg)");
				String imagen = reader.next();
				
				OtrosConductores conductorAdicional = new OtrosConductores(nombre,numLicencia,paisLicencia,
														fechaLicencia,imagen);
				conductorAdicional.registrarLicencia(idReserva);
				numRetorno += 1;
				System.out.println("Quiere registrar más conductores adicionales?");
				System.out.println("1. Si");
				System.out.println("2. No");
				answer = reader.next();
				if (answer.equals("2")) {
					flagRegistrar = false;
				}
			}
		}
		else if (answer.equals("2")) {
			return numRetorno;
		}
		else {
			System.out.println("Ingrese una opcion valida porfavor");
			flagAdicional = true;
		}
		}
		
		return numRetorno;
	}
	
	public void adicionTarifa (Reserva reservaActual, Cliente clienteActual) {
		Properties pReserva = new Properties();
		try {
		pReserva.load(new FileInputStream(new File("./RentadoraStorage/Reservas.txt")));
		String infoReserva =(String) pReserva.get(reservaActual.getId()+clienteActual.getNombre());
		String nuevaInfo = infoReserva + ";" + reservaActual.getTarifa().getPrecio();
		pReserva.put(reservaActual.getId()+clienteActual.getNombre(), nuevaInfo);
		guardarLogin(pReserva,"Reservas");
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String[] modificarDatosReserva(String[] infoReserva) {
		Scanner reader = new Scanner(System.in);
		String[] retorno = null;
		boolean flagModificar = true;
		while (flagModificar == true) {
		System.out.println("==========CAMBIO DE DATOS============");
		System.out.println("Seleccione el aspecto que desea modificar");
		System.out.println("1. Fecha Devolucion");
		System.out.println("2. Hora Devolucion");
		System.out.println("3. Sede de entrega");
		System.out.println("0. No mas cambios");
		
		String answer = reader.next();
		flagModificar = false;
		
		if(answer.equals("1")) {
			System.out.println("Que fecha en dd/mm/YYYY quiere entregarlo");
			String newFecha = reader.next();
			infoReserva[2] = newFecha;
		}
		else if (answer.equals("2")) {
			System.out.println("A que horas quiere regresar el carro");
			String newHora = reader.next();
			infoReserva[3] = newHora;
		}
		
		else if (answer.equals("3")) {
			System.out.println("A que sede lo va a cambiar");
			System.out.println("1. Radiator Spring");
			System.out.println("2. Carburetor Canyon");
			System.out.println("3. Emeryville");
			System.out.println("4. Motor Speedway");				
			String nuevaSede = reader.next();		
			infoReserva[6] = nuevaSede;
			}
		else if (answer.equals("0")) {
			flagModificar = false;
		}
		}
		retorno = infoReserva;
		return retorno;
	}
	
	//Main method
	public static void main(String[] args) {
		Consola consola = new Consola();
		}


}
