package RentadoraModelo;

public class Categoria 
{
	//Attributes
	
	private String nombre;
	private Categoria mejorCategoria;
	
	//Constructor
	
	public Categoria(String nombre) {
		this.nombre = nombre;
	}
	
	//Getter y Setters

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria getMejorCategoria() {
		return mejorCategoria;
	}
	
	
	public void setMejorCategoria(Categoria categoriaActual) {
		if (categoriaActual.getNombre().equals("lujo")) {
			this.mejorCategoria = null;
		}
		else if (categoriaActual.getNombre().equals("clasico")) {
			Categoria categoriaRecursive1 = new Categoria("lujo");
			categoriaRecursive1.setMejorCategoria(categoriaRecursive1);
			this.mejorCategoria = categoriaRecursive1;
		}
		else if (categoriaActual.getNombre().equals("todoterreno")) {
			Categoria categoriaRecursive2 = new Categoria("clasico");
			categoriaRecursive2.setMejorCategoria(categoriaRecursive2);
			this.mejorCategoria = categoriaRecursive2;
		}
		else if (categoriaActual.getNombre().equals("vans")) {
			Categoria categoriaRecursive3 = new Categoria("todoterreno");
			categoriaRecursive3.setMejorCategoria(categoriaRecursive3);
			this.mejorCategoria = categoriaRecursive3;
		}
		else if (categoriaActual.getNombre().equals("moto")) {
			Categoria categoriaRecursive4 = new Categoria("vans");
			categoriaRecursive4.setMejorCategoria(categoriaRecursive4);
			this.mejorCategoria = categoriaRecursive4;
		}
		else if (categoriaActual.getNombre().equals("bicicleta")) {
			Categoria categoriaRecursive4 = new Categoria("moto");
			categoriaRecursive4.setMejorCategoria(categoriaRecursive4);
			this.mejorCategoria = categoriaRecursive4;
		}
		else if (categoriaActual.getNombre().equals("atv")) {
			Categoria categoriaRecursive4 = new Categoria("bicicleta");
			categoriaRecursive4.setMejorCategoria(categoriaRecursive4);
			this.mejorCategoria = categoriaRecursive4;
		}
		else if (categoriaActual.getNombre().equals("patineta")) {
			Categoria categoriaRecursive4 = new Categoria("atv");
			categoriaRecursive4.setMejorCategoria(categoriaRecursive4);
			this.mejorCategoria = categoriaRecursive4;
		}
		else if (categoriaActual.getNombre().equals("tractor")) {
			Categoria categoriaRecursive4 = new Categoria("patineta");
			categoriaRecursive4.setMejorCategoria(categoriaRecursive4);
			this.mejorCategoria = categoriaRecursive4;
		}
		else if (categoriaActual.getNombre().equals("suv")) {
			Categoria categoriaRecursive5 = new Categoria("tractor");
			categoriaRecursive5.setMejorCategoria(categoriaRecursive5);
			this.mejorCategoria = categoriaRecursive5;
		}
	}
}