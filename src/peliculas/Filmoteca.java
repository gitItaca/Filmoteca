package peliculas;

import java.util.ArrayList;

public class Filmoteca {
	
	private ArrayList<Pelicula> susPeliculas;

		
//CONSTRUCTOR
	public Filmoteca(ArrayList<Pelicula> susPeliculas) {
		this.susPeliculas = susPeliculas;
	}//FIN constructor 2

	public Filmoteca() {
		susPeliculas = new ArrayList<Pelicula>();
	}//FIN constructor 0
	
//GETTERS Y SETTERS
	public ArrayList<Pelicula> getSusPeliculas() {
		return susPeliculas;
	}
	public void setSusPeliculas(ArrayList<Pelicula> susPeliculas) {
		this.susPeliculas = susPeliculas;
	}
		
//METODOS
	public void addPeliToFilmoteca(Pelicula miPelicula) {
		susPeliculas.add(miPelicula);
	}
	
	public void deletePeliFromFilmoteca(Pelicula miPelicula) {
		susPeliculas.remove(miPelicula);
	}
	
	@Override
	public String toString() {
		String texto = "";
		for(Pelicula unaPelicula:this.getSusPeliculas()) {
			texto = texto + unaPelicula + "\n";
		}
		return texto;
	}
	
}//FIN FILMOTECA
