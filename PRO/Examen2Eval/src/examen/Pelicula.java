package examen;

import java.util.Random;

public class Pelicula extends Audiovisual {
	private int duracion;
	private boolean animacion;
	
	public Pelicula(plataformas plataforma, String titulo, String director, int anhoPubli, int duracion,
			boolean animacion) {
		super(plataforma, titulo, director, anhoPubli);
		this.duracion = duracion;
		this.animacion = animacion;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public boolean isAnimacion() {
		return animacion;
	}

	public void setAnimacion(boolean animacion) {
		this.animacion = animacion;
	}

	// Editar el toString para que muestre la informaci�n de forma correcta:
	/* Aprovechar la herencia para la creaci�n correcta del toString
	 * Ejemplo:
	 * 
	 * Pelicula: [Titulo], dirigida por [director/a]
	 * 		A�o de publicaci�n: [A�o] 
	 * 		Plataforma: [Plataforma] 
	 * 		Duraci�n: [duracion] min.
	 * 		<Pelicula de animaci�n.> (S�lo si es de animaci�n se indica)
	 * 
	 * 
	 * 
	 * */
	
	@Override
	public String toString() {
		String toret="Pel�cula:\n"+getTitulo()+", dirigida por "+getDirector()+super.toString();
		toret+="\nDuraci�n:"+duracionTotal();
		if(isAnimacion())toret+="\nPelicula de animaci�n";
		return toret;
	}

	// editar este m�todo para que devuelva la duraci�n total de la pel�cula
	@Override
	public int duracionTotal() {
		return duracion;
	}
	
	/* Realizar el m�todo que devuelva un array con los objetos Pelicula generados a 
	* partir de los siguientes arrays de titulos y directores y valores aleatorios para las duracioes,
	* a�os de publicacion, plataformas, etc. 
	* Debe usarse obligatoriamente la clase Random para los n�meros aleatorios.
	* Los a�os de publicaci�n no deben ser anteriores a 1980 ni superiores a 2020
	* La duracion no debe ser inferior a 80 ni superior a 180
	* Solo las peliculas de animaci�n deben llevar a true ese elemento
	* 
	* Nota: el m�todo debe devolver tantas peliculas como elementos hay en los arrays de titulos y directores, cada titulo se corresponde en posici�n con el director de la peli.
	* */
	
	public static String [] titulos = {"Captain Fantastic", "Coach Carter", "8 Mille", "Shutter Island", "Space Jam", "Le Cinqui�me �l�ment","Pulp Fiction", "Forest Gump", "Shrek"};
	public static String [] directores = {"Matt Ross", "Thomas Carter", "Curtis Hanson", "Martin Scorsese", "Joe Putka", "Luc Besson","Quentin Tarantino", "Robert Zemeckis", "Vicky Jenson"};

	public static Pelicula[] generarPeliculas() {
		Random r=new Random();
		Pelicula[] toret=new Pelicula[titulos.length];
		
		for(int i=0;i<toret.length;i++) {
			int anho=1980+r.nextInt(41);
			int dur=80+r.nextInt(101);
			boolean ani;
			if(i==4||i==8)ani=true;
			else ani=false;
			Pelicula p=new Pelicula(Audiovisual.platRandom(),titulos[i],directores[i],anho,dur,ani);
			toret[i]=p;
		}
		
		return toret;
	}
}
