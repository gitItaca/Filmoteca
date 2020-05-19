package peliculas;

import java.util.ArrayList;

public class Pelicula {

	private String nombre;
	private ArrayList<Actor> susActores;
	private ArrayList<Director> susDirectores;
		
//CONSTRUCTOR
	public Pelicula(String nombre, ArrayList<Actor> susActores, ArrayList<Director> susDirectores) {
		this.nombre = nombre;
		this.susActores = susActores;
		this.susDirectores = susDirectores;
	}
	
	public Pelicula(String nombre) {
		this.nombre = nombre;
		susActores = new ArrayList<Actor>();
		susDirectores = new ArrayList<Director>();

	}
	
//GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Actor> getSusActores() {
		return susActores;
	}
	public void setSusActores(ArrayList<Actor> susActores) {
		this.susActores = susActores;
	}

	public ArrayList<Director> getSusDirectores() {
		return susDirectores;
	}
	public void setSusDirectores(ArrayList<Director> susDirectores) {
		this.susDirectores = susDirectores;
	}
	
//METODOS
	public void addActorToPelicula(Actor miActor) {
		susActores.add(miActor);
	}
	public void addDirectorToPelicula(Director miDirector) {
		susDirectores.add(miDirector);
	}
	
	public void deleteActorFromPelicula(Actor miActor) {
		susActores.remove(miActor);
	}
	public void deleteDirectorFromPelicula(Director miDirector) {
		susDirectores.remove(miDirector);
	}
	
	@Override
	public String toString() {
		String texto = "";
		texto = texto + "PELÍCULA________________________\n" + "   " + this.getNombre() + "\n";
		texto = texto +  " " + "ACTUACIÓN\n";
		for(Actor unActor:this.getSusActores()) {
			texto = texto + "   " + unActor + "\n";
			
		}
		texto = texto +  " " + "DIRECCIÓN\n";
		for(Director unDirector:this.getSusDirectores()) {
			texto = texto + "   " + unDirector + "\n";
		}
		return texto;
	}
	
	
	
}//FIN PELICULA
