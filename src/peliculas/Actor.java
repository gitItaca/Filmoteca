package peliculas;

public class Actor {
	
	private String nombre;
	private String apellidos;
	private int edad;
	
//CONSTRUCTOR
	public Actor(String nombre, String apellidos, int edad) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
	}//FIN constructor 3
	
	public Actor() {
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
	
//METODOS
	@Override
	public String toString() {
		return nombre + " " + apellidos + ": " + edad + " años";
	}
}//FIN ACTOR
