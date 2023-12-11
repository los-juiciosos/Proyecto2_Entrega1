package RentadoraModelo;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Tarifa 
{
	//Attributes
		
	private Categoria categoriaVehiculo;
	private double precio;
	private boolean mismaSede;
	private int conductorAdicional;
		
	//Constructor
		
	public Tarifa(Categoria categoriaVehiculo,boolean mismaSede,String fechaLlegada,String fechaDevolucion) {
		this.categoriaVehiculo = categoriaVehiculo;
		this.precio = 0;
		this.mismaSede = mismaSede;
		this.conductorAdicional = 0;
		calcularTarifa(fechaLlegada,fechaDevolucion);
		}
	
	//Getters y Setters
		
	public Categoria getCategoriaVehiculo() {
		return categoriaVehiculo;
	}
	
	public void setCategoriaVehiculo(Categoria categoriaVehiculo) {
		this.categoriaVehiculo = categoriaVehiculo;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public boolean isMismaSede() {
		return mismaSede;
	}
	
	public void setMismaSede(boolean mismaSede) 
	{
		this.mismaSede = mismaSede;
	}	
		
		
	//Method 
		
	public void calcularTarifa(String fechaLlegada,String fechaDevolucion) 
	{
		
		Properties pTarifa = new Properties();
		double precioFinal = 0;
		try {
		pTarifa.load(new FileInputStream(new File("./RentadoraStorage/Tarifa.txt")));
		int precioCategoria = (int) Integer.parseInt((String)pTarifa.get(categoriaVehiculo.getNombre()));
		double precioIncrementoSede = 0;
		if (mismaSede == false)
		{
			precioIncrementoSede = 0.10*precioCategoria;
		}
			int diasPrestamo = diasEntreFechas(fechaLlegada,fechaDevolucion);
			precioFinal = precioCategoria + precioIncrementoSede + (diasPrestamo*precioCategoria);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			
			this.precio = precioFinal;
		}
	
	public int diasEntreFechas(String fechaInicio, String fechaFinal) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = sdf.parse(fechaInicio);
            Date endDate = sdf.parse(fechaFinal);

            long differenceInMillis = endDate.getTime() - startDate.getTime();
            long diasDiferencia = differenceInMillis / (1000 * 60 * 60 * 24);
            int diasRetorno = (int) diasDiferencia;
            
            return diasRetorno;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; 
        }
	}
}
			
	
