package examen;

import java.util.ArrayList;
import java.util.Random;

public class Serie extends Audiovisual {
	private int duracionCapitulo;
	private ArrayList<Temporada> temporadas;
	
	
	public Serie(plataformas plataforma, String titulo, String director, int anhoPubli, int duracionCapitulo,
			ArrayList<Temporada> temporadas) {
		super(plataforma, titulo, director, anhoPubli);
		this.duracionCapitulo = duracionCapitulo;
		this.temporadas = temporadas;
	}

	public int getDuracionCapitulo() {
		return duracionCapitulo;
	}

	public void setDuracionCapitulo(int duracionCapitulo) {
		this.duracionCapitulo = duracionCapitulo;
	}

	public ArrayList<Temporada> getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(ArrayList<Temporada> temporadas) {
		this.temporadas = temporadas;
	}
	
	public int numTemporadas() {
		return temporadas.size();
	}
	
	//TODO Realizar un método que devuelva el número total de capítulos de la serie
	public int numTotalCapitulos() {
		int toret=0;
		for(int i=0; i<temporadas.size(); i++) {
			for(int j=0;j<temporadas.get(i).getNumCapitulos();j++) {
				toret++;
			}
		}
		return toret;
	}
	
	// Editar el toString para que muestre la información de forma correcta:
	/*	Aprovechar la herencia para la creación correcta del toString
	 * Ejemplo:
	 * 
	 * Serie: [Titulo], creada por [creador/a]
	 * 		Año de publicación: [Año] 
	 * 		Plataforma: [Plataforma] 
	 * 		Número de temporadas: [numTemporadas]
	 * 		Número total de capítulos: [numCapítulos]
	 * 		Duración: [duracionTotal] min.
	 * 
	 * 
	 * 
	 * */
	@Override
	public String toString() {
		String toret="Serie:\n"+getTitulo()+",creada por "+getDirector()+super.toString();
		toret+="\nNúmero de temporadas:"+numTemporadas();
		toret+="\nNúmero total de capítulos:"+numTotalCapitulos();
		toret+="\nDuración:"+duracionTotal();
		return toret;
	}

	//Editar el método para que calcule la duración total de la serie en minutos
	@Override
	public int duracionTotal() {
		int toret=numTotalCapitulos()*duracionCapitulo;
		return toret;
	}
	
	/* Realizar el método que devuelva un array con los objetos Serie generados a 
	* partir de los siguientes arrays de titulos y creadores y valores aleatorios para las duracioes,
	* años de publicacion, plataformas, número de temporadas, etc. 
	* Debe usarse obligatoriamente la clase Random para los números aleatorios.
	* Los años de publicación no deben ser anteriores a 1980 ni superiores a 2020
	* Las duraciones de los capitulos no deben ser inferiores a 15 minutos ni superiores a 80
	* El número de temporadas no debe ser superior a 25 y debe ser positivo
	* 
	* Nota: el método debe devolver tantas series como elementos hay en los arrays de titulos y creadores, cada titulo se corresponde en posición con el creador de la serie.
	* */
	public static String [] titulos = {"Game of Thrones", "The Simpsons", "Brooklyn 99", "The Sopranos", "Breaking Bad", "Futurama", "The Umbrella Academy", "La casa de papel", "The Last Dance"};
	public static String [] creadores = {"David Benioff", "Matt Groening", "Dan Goor", "David Chase", "Vince Gilligan", "Matt Groening","Steve Blackman", "Alex Pina", "Michale Tollin"};

	public static Serie[] generadorSeries() {
		Random r=new Random();
		Serie[] toret=new Serie[titulos.length];
		
		for(int i=0;i<toret.length;i++) {
			ArrayList<Temporada> temps=new ArrayList<Temporada>();
			Temporada t=new Temporada(1+r.nextInt(15),1);
			temps.add(t);
			int cont=2;
			for(int j=0;j<24;j++) {
				if(r.nextBoolean()) {
					Temporada t2=new Temporada(1+r.nextInt(15),cont);
					cont++;
					temps.add(t2);
				}
			}
			
			int anho=1980+r.nextInt(41);
			int dur=15+r.nextInt(66);
			Serie s=new Serie(Audiovisual.platRandom(),titulos[i],creadores[i],anho,dur,temps);
			toret[i]=s;
		}
		
		return toret;
	}
	
	
}
