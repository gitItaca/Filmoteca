package peliculas;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class PROGRAMA {

	public static void main(String[] args) {
		
		Filmoteca film = new Filmoteca(); 
				
		/*Pelicula unaPelicula = new Pelicula("Todo sobre mi madre");
		Director unDirector = new Director("Pedro", "Almodovar", 70);
		unaPelicula.addDirectorToPelicula(unDirector);
		Actor unActor = new Actor("Antonia", "San Juan", 58);
		unaPelicula.addActorToPelicula(unActor);
		film.addPeliToFilmoteca(unaPelicula);*/
		
		imprimirMenu(film);				
		
	}//FIN MAIN

	public static void imprimirMenu(Filmoteca Film) {
			
		int opcion;		
		do {
			System.out.println("___________MENU______________");
			System.out.println();
			System.out.println(" 1- Registrar película.");
			System.out.println(" 2- Borrar película.");
			System.out.println(" 3- Modificar película.");
			System.out.println(" 4- Ver lista películas.");
			System.out.println(" 5- Buscar película.");
			System.out.println(" 6- Grabar película txt");
			System.out.println(" 7- Importar película txt");
			System.out.println(" 8- Grabar película binario");
			System.out.println(" 9- Importar película binario");
			System.out.println("10- Grabar película aleatorio");
			System.out.println("11- Importar película aleatorio");
			System.out.println(" 0- Salir");
			System.out.println();
			
			opcion = Leer.pedirEnteroValidar();
			while (opcion < 0 || opcion > 11) {
				System.out.println("Por favor, ingrese uno de los números disponibles");
				opcion = Leer.pedirEnteroValidar();
			}//FIN while opcion
				switch(opcion) {
				case 1: registrarPelicula(Film); break;
				case 2: borrarPelicula(Film); break;
				case 3: modificarPelicula(Film); break;
				case 4: listaPeliculas(Film); break;
				case 5: buscarPelicula(Film); break;
				case 6: grabarPeliculaTxt(Film); break;
				case 7: importarPeliculaTxt(Film); break;
				case 8: grabarPeliculaBinario(Film); break;
				case 9: try {
						importarPeliculaBinario(Film);
					} catch (FileNotFoundException e) {
						System.out.println("Excepción salta.");
					} break;
				case 10: grabarPeliculaAleatorio(Film); break;
				case 11: try {
						importarPeliculaAleatorio(Film);
					} catch (FileNotFoundException e) {
						System.out.println("Excepción salta.");
					} break;
				}//FIN switch
				
		} while(opcion!=0);
	
	}//FIN imprimirMenu
	
	public static void registrarPelicula(Filmoteca miFilmoteca) {
		String nombre, nDirector, aDirector, nActor, aActor;
		int eDirector, eActor, num;
		
		System.out.println("¿Cómo se llama la película?");
		nombre = Leer.pedirCadena();
		Pelicula unaPelicula = new Pelicula(nombre);
		
		System.out.println("¿Cuántos directores tiene?");
		num = Leer.pedirEnteroValidar();
		if(num != 0) {
			for(int x=0; x<num; x++) {
				System.out.println("Nombre del director.");
				nDirector = Leer.pedirCadena();
				System.out.println("Apellidos del director.");
				aDirector = Leer.pedirCadena();
				System.out.println("Edad del director.");
				eDirector = Leer.pedirEnteroValidar();
				
				Director unDirector = new Director(nDirector, aDirector, eDirector);
				unaPelicula.addDirectorToPelicula(unDirector);
			}
		}//FIN if directores
		
		System.out.println("¿Cuántos actores principales tiene?");
		num = Leer.pedirEnteroValidar();
		if(num != 0) {
			for(int x=0; x<num; x++) {
				System.out.println("Nombre del actor.");
				nActor = Leer.pedirCadena();
				System.out.println("Apellidos del actor.");
				aActor = Leer.pedirCadena();
				System.out.println("Edad del actor.");
				eActor = Leer.pedirEnteroValidar();
				
				Actor unActor = new Actor(nActor, aActor, eActor);
				unaPelicula.addActorToPelicula(unActor);
			}
		}//FIN if actores
		miFilmoteca.addPeliToFilmoteca(unaPelicula);
	}//FIN registrarPelicula

	public static void borrarPelicula(Filmoteca Film) {
		int idPeli;
		Pelicula unaPelicula;
		if(Film.getSusPeliculas().isEmpty()) {
			System.out.println("La lista está vacía, registre o importe primero alguna película");
		}else {
			System.out.println("Hay "+ Film.getSusPeliculas().size() + " películas.");
			System.out.println("¿Qué película quieres borrar?");
			
			for(int x=0; x<Film.getSusPeliculas().size(); x++) {
				unaPelicula = Film.getSusPeliculas().get(x);
				System.out.println(x + " - " + unaPelicula.getNombre() + " con la dirección de " + unaPelicula.getSusDirectores().toString()+".");
			}//FIN for lista peliculas
			
			System.out.println("");
			System.out.println("Escribe el número de la película");
			idPeli = Leer.pedirEnteroValidar();
			while (idPeli < 0 || idPeli > Film.getSusPeliculas().size()) {
				System.out.println("Por favor, ingrese uno de los números disponibles");
				idPeli = Leer.pedirEnteroValidar();
			}
			unaPelicula=Film.getSusPeliculas().get(idPeli);
			Film.deletePeliFromFilmoteca(unaPelicula);
			System.out.println("Película eliminada.");
		}
	}//FIN borrarPelicula
	
	public static void modificarPelicula(Filmoteca Film) {
		Pelicula unaPelicula;
		Actor unActor;
		Director unDirector;
		int opcion, x, y;
		String nombre;
		
		if(Film.getSusPeliculas().isEmpty()) {										//Comprueba que la lista este vacia
			System.out.println("No tiene ninguna película. Por favor, registre o importe alguna.");
		}else {
			System.out.println("¿Qué película quieres cambiar?");					//Enseña toda la lista al usuario para que elija la pelicula
			for(x=0; x<Film.getSusPeliculas().size(); x++) {
				unaPelicula = Film.getSusPeliculas().get(x);
				System.out.println(x + " - " + unaPelicula.getNombre() + " con la dirección de " + unaPelicula.getSusDirectores().toString()+".");
			}//FIN for lista peliculas
			x = Leer.pedirEnteroValidar();
			while (x < 0 || x > Film.getSusPeliculas().size()) {					//Comprueba que el usuario haya metido un numero disponible
				System.out.println("Por favor, ingrese uno de los números disponibles.");
				x = Leer.pedirEnteroValidar();
			}
			unaPelicula = Film.getSusPeliculas().get(x);							//Una vez elegido el numero, le pregunta que opcion quiere modificar de la pelicula
			System.out.println("Elige que quieres modificar de la película: \n " + unaPelicula.toString());
			do {
				System.out.println("___________MODIFICAR______________");
				System.out.println("1_ Cambiar nombre de la película.");
				System.out.println("2_ Añadir actor.");
				System.out.println("3_ Añadir director.");
				System.out.println("4_ Borrar actor.");
				System.out.println("5_ Borrar director.");
				System.out.println("0_ Salir.");
				
				opcion = Leer.pedirEnteroValidar();
				while (opcion < 0 || opcion > 5) {									//Comprueba que la opcion del menu sea valida
					System.out.println("Por favor, ingrese uno de los números disponibles.");
					opcion = Leer.pedirEnteroValidar();
				}
				switch(opcion) {
				case 1: 															//Cambiar nombre de la película
					System.out.println("Escriba el nuevo nombre.");
					nombre = Leer.pedirCadena();
					unaPelicula.setNombre(nombre);
					System.out.println("Nombre modificado con éxito.");
					break;
				case 2:																//Añadir actor
					System.out.println("Nombre del actor.");
					String nActor = Leer.pedirCadena();
					System.out.println("Apellidos del actor.");
					String aActor = Leer.pedirCadena();
					System.out.println("Edad del actor.");
					int eActor = Leer.pedirEnteroValidar();
					
					unActor = new Actor(nActor, aActor, eActor);
					unaPelicula.addActorToPelicula(unActor);
					break;
				case 3:																//Añadir director
					System.out.println("Nombre del director.");
					String nDirector = Leer.pedirCadena();
					System.out.println("Apellidos del director.");
					String aDirector = Leer.pedirCadena();
					System.out.println("Edad del director.");
					int eDirector = Leer.pedirEnteroValidar();
					
					unDirector = new Director(nDirector, aDirector, eDirector);
					unaPelicula.addDirectorToPelicula(unDirector);
					break;
				case 4:																//Borrar actor
					if(unaPelicula.getSusActores()==null) {
						System.out.println("No hay actores registrados.");
					}else {
						System.out.println("Escribe el número del actor a borrar.");
						for(y=0; y<unaPelicula.getSusActores().size(); y++) {
							unActor = unaPelicula.getSusActores().get(y);
							System.out.println(y + "- " + unActor);
						}//FIN for lista actores
						y = Leer.pedirEnteroValidar();
						unActor = unaPelicula.getSusActores().get(y);
						unaPelicula.deleteActorFromPelicula(unActor);
						System.out.println("Actor borrado.");
					}
					break;
				case 5:																//Borrar director
					if(unaPelicula.getSusDirectores()==null) {
						System.out.println("No hay directores registrados.");
					}else {
						System.out.println("Escribe el número del director a borrar.");
						for(y=0; y<unaPelicula.getSusDirectores().size(); y++) {
							unDirector = unaPelicula.getSusDirectores().get(y);
							System.out.println(y + "- " + unDirector);
						}//FIN for lista directores
						y = Leer.pedirEnteroValidar();
						unDirector = unaPelicula.getSusDirectores().get(y);
						unaPelicula.deleteDirectorFromPelicula(unDirector);
						System.out.println("Director borrado.");
					}
					break;			
				}//FIN switch				
			}while(opcion!=0);	
		}//FIN if lista vacia
	}//FIN modificarPelicula
	
	public static void listaPeliculas(Filmoteca Film) {
		if(Film.getSusPeliculas().isEmpty()) {
			System.out.println("No tiene ninguna película. Por favor, registre o importe alguna.");
		}else	System.out.println(Film.toString());
	}//FIN listaPeliculas
		
	public static void buscarPelicula(Filmoteca Film) {		
		int opcion;
		int cont;
		String nombre, apellido;
		
		if(Film.getSusPeliculas().isEmpty()) {
			System.out.println("La lista está vacía, registre o importe primero alguna película.");
		}else {
			System.out.println("¿Qué criterio de búsqueda elige?");
			
			do {
			System.out.println("1- Nombre de la película.");	
			System.out.println("2- Nombre del director.");	
			System.out.println("3- Nombre del actor.");	
			System.out.println("0- Salir.");
			
			opcion = Leer.pedirEnteroValidar();
			while (opcion < 0 || opcion > 3) {
				System.out.println("Por favor, ingrese uno de los números disponibles.");
				opcion = Leer.pedirEnteroValidar();
			}
				switch(opcion) {
				case 1:					
					cont = 0;					
					System.out.println("1- Escriba el nombre de la película.");
					nombre = Leer.pedirCadena();
					for(int z=0; z<Film.getSusPeliculas().size();z++) {						
						Pelicula unaPelicula = Film.getSusPeliculas().get(z);
						if(unaPelicula.getNombre().contentEquals(nombre)) {
							System.out.println(unaPelicula.toString());
							cont++;
						}
					}//FIN for
					if(cont==0) System.out.println("No existe.");					
					break;
					
				case 2:					
					cont = 0;					
					System.out.println("Escriba el nombre del director.");
					nombre = Leer.pedirCadena();
					System.out.println("Escriba el apellido del director.");
					apellido = Leer.pedirCadena();
					for(int z=0; z<Film.getSusPeliculas().size();z++) {						
						Pelicula unaPelicula = Film.getSusPeliculas().get(z);
						for(int y=0; y<unaPelicula.getSusDirectores().size();y++) {
							Director unDirector = unaPelicula.getSusDirectores().get(y);
							if(unDirector.getNombre().contentEquals(nombre) && unDirector.getApellidos().contentEquals(apellido)) {
								System.out.println(unaPelicula.toString());
								cont++;
							}
						}
					}//FIN for
					if(cont==0) System.out.println("No existe.");					
					break;
					
				case 3:					
					cont = 0;					
					System.out.println("1- Escriba el nombre del actor.");
					nombre = Leer.pedirCadena();
					System.out.println("Escriba el apellido del actor.");
					apellido = Leer.pedirCadena();
					for(int z=0; z<Film.getSusPeliculas().size();z++) {						
						Pelicula unaPelicula = Film.getSusPeliculas().get(z);
						for(int y=0; y<unaPelicula.getSusDirectores().size();y++) {
							Director unActor = unaPelicula.getSusDirectores().get(y);
							if(unActor.getNombre().contentEquals(nombre) && unActor.getApellidos().contentEquals(apellido)) {
								System.out.println(unaPelicula.toString());
								cont++;
							}
						}
					}//FIN for
					if(cont==0) System.out.println("No existe.");					
					break;					
				}//FIN switch
			}while(opcion!=0);
		
		}//FIN if vacia
	}//FIN buscarPelicula
	
	public static void grabarPeliculaTxt(Filmoteca Film) {
		if(Film.getSusPeliculas().isEmpty()) {
			System.out.println("La lista está vacía, registre o importe primero alguna película.");
		}else {
			try {
				FileWriter fw = new FileWriter("Filmoteca.txt", true);
				
				
				for(Pelicula unaPelicula:Film.getSusPeliculas()) {
					fw.write("PELICULAS\n");
					fw.write(unaPelicula.getNombre()+"\n");
					fw.write("ACTORES\n");
				
					for(Actor unActor:unaPelicula.getSusActores()) {
						fw.write(unActor.getNombre()+"\n");
						fw.write(unActor.getApellidos()+"\n");
						fw.write(unActor.getEdad()+"\n");
					}
					fw.write("DIRECTORES\n");
		
					for(Director unDirector:unaPelicula.getSusDirectores()) {
						fw.write(unDirector.getNombre()+"\n");
						fw.write(unDirector.getApellidos()+"\n");
						fw.write(unDirector.getEdad()+"\n");
					}	
					fw.write("FIN\n");
				}//FIN
				
				fw.close();
			} catch (IOException e) {
				System.out.println("Se produce un IOException.");
			}//FIN try/catch
		}//FIN if lista vacia
	}//FIN grabarPelicula
	
	public static void importarPeliculaTxt(Filmoteca Film) {
		Film.getSusPeliculas().clear();  			//Limpio el array para evitar duplicidades masivas, grabar antes de importar 
		try {
			File fich = new File("Filmoteca.txt");
			BufferedReader fichero = new BufferedReader(new FileReader(fich));
			String linea, nombre, apellido;
			int edad;
			Pelicula unaPelicula = null;
			Actor unActor = null;
			Director unDirector = null;
			
			while((linea  = fichero.readLine())!= null) {
				if (linea.contains("PELICULAS")) {							
					unaPelicula = new Pelicula (fichero.readLine());
					Film.addPeliToFilmoteca(unaPelicula);
				}
				if (linea.contains("ACTORES")) {					
					while (!(linea  = fichero.readLine()).contains("DIRECTORES")) {
						nombre = linea;
						apellido = fichero.readLine();
						edad = Integer.parseInt(fichero.readLine());
						
						unActor = new Actor(nombre, apellido, edad);
						unaPelicula.addActorToPelicula(unActor);
					}
				}
				if (linea.contains("DIRECTORES")) {
					while ( !(linea  = fichero.readLine()).contains("FIN")) {
						nombre = linea;
						apellido = fichero.readLine();
						edad = Integer.parseInt(fichero.readLine());
						
						unDirector = new Director(nombre, apellido, edad);
						unaPelicula.addDirectorToPelicula(unDirector);						
					}
				}
			}//FIN while
			fichero.close();
		} catch (FileNotFoundException e) {
			System.out.println("Se produce un FileNotFoundException.");
		} catch (IOException e) {
			System.out.println("Se produce un IOException.");
		}//FIN try
		
	}//FIN importarPeliculaTxt
	
	public static void grabarPeliculaBinario(Filmoteca Film){
		if(Film.getSusPeliculas().isEmpty()) {
			System.out.println("La lista está vacía, registre o importe primero alguna película.");
		}else {
			try {
				File f = new File("FicheroB.dat");
				FileOutputStream fos = new FileOutputStream(f);
				DataOutputStream dos = new DataOutputStream(fos);
				
				for(Pelicula unaPelicula:Film.getSusPeliculas()) {
					dos.writeUTF("PELICULAS");
					dos.writeUTF(unaPelicula.getNombre());
					dos.writeUTF("ACTORES");
					for(Actor unActor:unaPelicula.getSusActores()) {
						dos.writeUTF(unActor.getNombre());
						dos.writeUTF(unActor.getApellidos());
						dos.writeInt(unActor.getEdad());					
					}
					dos.writeUTF("DIRECTORES");
					for(Director unDirector:unaPelicula.getSusDirectores()) {
						dos.writeUTF(unDirector.getNombre());
						dos.writeUTF(unDirector.getApellidos());
						dos.writeInt(unDirector.getEdad());					
					}
					dos.writeUTF("FIN");
				}
				dos.close();
			} catch (IOException e) {
				System.out.println("Se produce un IOException.");
			}//FIN try/catch
		}
	}//FIN grabarPeliculaBinario
		
	public static void importarPeliculaBinario(Filmoteca Film) throws FileNotFoundException{		
		Pelicula unaPelicula = null;
		Actor unActor = null;
		Director unDirector = null;
		String linea, nombre, apellido;
		int edad;
		
		File f = new File("FicheroB.dat");			
		FileInputStream fis = new FileInputStream(f);
		DataInputStream dis = new DataInputStream(fis);
		
		Film.getSusPeliculas().clear();				//Limpio el array para evitar duplicidades masivas, grabar antes de importar 
		
		try {			
			while(true) {
				linea = dis.readUTF();
				if(linea.contains("PELICULAS")) {
					nombre = dis.readUTF();								
					unaPelicula = new Pelicula(nombre);
					Film.addPeliToFilmoteca(unaPelicula);
				}
				if(linea.contains("ACTORES")) {
					while(!(linea=dis.readUTF()).contains("DIRECTORES")) {
						nombre = linea;
						apellido = dis.readUTF();
						edad = dis.readInt();						
						unActor = new Actor(nombre,apellido,edad);
						unaPelicula.addActorToPelicula(unActor);
					}
				}
				if(linea.contains("DIRECTORES")) {
					while(!(linea=dis.readUTF()).contains("FIN")) {
						nombre = linea;
						apellido = dis.readUTF();
						edad = dis.readInt();
						unDirector = new Director(nombre,apellido,edad);
						unaPelicula.addDirectorToPelicula(unDirector);
					}
				}
			}//FIN while
			
		} catch (EOFException e) {
			System.out.println("Se produce un EOFException.");
		} catch (IOException e) {
			System.out.println("Se produce un IOException.");
		} 
		
		try {
			dis.close();
		} catch (IOException e) {
			System.out.println("Se produce un IOException.");
		}
	}//FIN leerPeliculaBinario

	public static void grabarPeliculaAleatorio(Filmoteca Film) {
		RandomAccessFile raf;
		
		if(Film.getSusPeliculas().isEmpty()) {
			System.out.println("La lista está vacía, registre o importe primero alguna película.");
		}else {
			try {
				raf = new RandomAccessFile("FicheroA.txt", "rw");
				for(Pelicula unaPelicula:Film.getSusPeliculas()) {
					raf.seek(raf.length());
					raf.writeBytes("PELÍCULA");
					raf.write('\n');
					raf.writeBytes(unaPelicula.getNombre());
					raf.write('\n');
					for(Actor unActor:unaPelicula.getSusActores()) {
						raf.writeBytes("ACTORES");
						raf.write('\n');
						raf.writeBytes(unActor.getNombre());
						raf.write('\n');
						raf.writeBytes(unActor.getApellidos());
						raf.write('\n');
						raf.writeInt(unActor.getEdad());
						
					}//FIN for actor
					for(Director unDirector:unaPelicula.getSusDirectores()) {
						raf.writeBytes("DIRECTORES");
						raf.write('\n');
						raf.writeBytes(unDirector.getNombre());
						raf.write('\n');
						raf.writeBytes(unDirector.getApellidos());
						raf.write('\n');
						raf.writeInt(unDirector.getEdad());
						
					}//FIN for director
				}//FIN for pelicula
				raf.close();
			} catch (IOException e) {
				System.out.println("Se produce un IOException.");
			}
		}//FIN if lista vacia
	}//FIN grabarPeliculaAleatorio

	public static void importarPeliculaAleatorio(Filmoteca Film) throws FileNotFoundException{
		RandomAccessFile raf;
		String linea, nombre, apellido;
		int edad;		Pelicula unaPelicula = null;
		Actor unActor = null;
		Director unDirector = null;
		
		Film.getSusPeliculas().clear();					//Limpio el array para evitar duplicidades masivas, grabar antes de importar 
				
		try {
			raf = new RandomAccessFile("FicheroA.txt", "rw");
			raf.seek(0);
			
			while(raf.getFilePointer() < raf.length()) {
				linea=raf.readLine();
				if(linea.contains("PELÍCULA")) { 					
					nombre = raf.readLine();
					unaPelicula = new Pelicula(nombre);
					Film.addPeliToFilmoteca(unaPelicula);						
				}else if(linea.contains("ACTORES")) {
					nombre = raf.readLine();
					apellido = raf.readLine();
					edad = raf.readInt();
					unActor = new Actor(nombre, apellido, edad);
					unaPelicula.addActorToPelicula(unActor);				
				}else if(linea.contains("DIRECTORES")) {
					nombre = raf.readLine();
					apellido = raf.readLine();
					edad = raf.readInt();
					unDirector = new Director(nombre, apellido, edad);
					unaPelicula.addDirectorToPelicula(unDirector);
				}				
			}//FIN while
			raf.close();
		} catch (IOException e) {
			System.out.println("Se produce un IOException.");
		}
	}//FIN importarPeliculaAleatorio
}//FIN PROGRAMA
