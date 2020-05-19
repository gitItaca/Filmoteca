package peliculas;

public class Director {

	private String nombre;
	private String apellidos;
	private int edad;	
	
//CONSTRUCTOR
	public Director(String nombre, String apellidos, int edad) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
	}//FIN constructor 3
	
	public Director() {
		this.nombre = null;
		this.apellidos = null;
		this.edad = 0;
	}//FIN constructor 0

	
//GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public void mostrarNombre(String nombre, String apellidos) {
		System.out.print(nombre + apellidos);
	}

//METODOS
	@Override
	public String toString() {
		return nombre + " " + apellidos + ": " + edad + " años";
	}
	
}//FIN DIRECTOR
