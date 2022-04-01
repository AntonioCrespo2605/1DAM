package examen;

import java.util.ArrayList;

public class Main {

	static ArrayList<Audiovisual> peliculasYseries;

	public static void main(String[] args) {
		// Parte 1:
		System.out.println("Comienza la parte 1: ");
		System.out.println(eliminaEspaciosDuplicados("Esto  es una   prueba  para ver  si   funciona       el método  "));

		System.out.println("Termina la parte 1. ");

		System.out.println("=============================================================================");

		// Parte 2:
		System.out.println("Comienza la parte 2: ");
		int matriz[][] = {{45,56,44},{34,67,25},{77,11,12},{13,13,97},{32,32,86}};
		
		System.out.println(muestraMatriz(matriz));
		matriz = ordenInversoMatriz(matriz);
		System.out.println(muestraMatriz(matriz));


		System.out.println("Termina la parte 2. ");

		System.out.println("=============================================================================");
		// Parte 3:
		System.out.println("Comienza la parte 3: ");
		peliculasYseries = new ArrayList<Audiovisual>();
		Serie[] series = Serie.generadorSeries();
		Pelicula[] peliculas = Pelicula.generarPeliculas();
		
		
		for (Serie s : series) {
			peliculasYseries.add(s);
		}

		for (Pelicula p : peliculas) {
			peliculasYseries.add(p);
		}

		mostrartodo();

		System.out.println("La pelicula más larga es: " + peliculaMasLarga().toString()+"\n");
		
		System.out.println("La serie más corta es: " + serieMasCorta().toString()+"\n");

		System.out.println("Las peliculas y series del siglo XX son: " + sigloXX());

		System.out.println("Termina la parte 3. ");

	}



	// Implementar el siguiente método qué recibe una String cómo parámetro y debe
	// devolverla sin los espacios duplicados.
	private static String eliminaEspaciosDuplicados(String string) {
		string = string.replaceAll("\\s+"," ");
		return string;
	}
	
	/*Implementar el siguiente método que debe reordenar los elementos de una matriz de mayor a menor, siguiendo el siguiente orden 0,0 mayor, 0,1, el siguiente, 0,2, el siguiente,
	* y así sucesivamente hasta que el elemento N,M sea el menor de todos y el 0,0 el mayor de todos
	* */
	private static int[][] ordenInversoMatriz(int[][] matriz) {
		int total=matriz.length*matriz[0].length;
		int[]aux=new int[total];
		int cont=0;
		
		for(int i=0;i<matriz.length;i++) {
			for(int j=0;j<matriz[i].length;j++) {
				aux[cont]=matriz[i][j];
				cont++;
			}
		}
		
		int posmax;
		int max;
		int aux2;
		for(int i=0;i<aux.length;i++) {
			posmax=i;
			max=aux[posmax];
			for(int j=i;j<aux.length;j++) {
				if(aux[j]>max) {
					max=aux[j];
					aux2=aux[i];
					aux[i]=aux[j];
					aux[j]=aux2;
					posmax=j;
				}
			}
		}
		
		
		int newCont=0;
		for(int i=0;i<matriz.length;i++) {
			for(int j=0;j<matriz[i].length;j++) {
				matriz[i][j]=aux[newCont];
				newCont++;
			}
		}
		
		return matriz;
		
	}

	/*
	 * Implementar un método que muestre las matrices por pantalla de forma que se vea cada fila en una fila con todos sus elementos y separe dichos elementos por comas y los meta dentro
	 * de corchetes, y separe también las filas con saltos de línea. 
	 * 
	 * Además debe introducir un salto de línea antes y después del muestreo
	 * */
	private static String muestraMatriz(int[][] matriz) {
		String toret="\n";
		
		for(int i=0; i<matriz.length;i++) {
			toret+="{";
			for(int j=0; j<matriz[i].length;j++) {
				if(j==matriz[i].length-1)toret+=matriz[i][j];
				else toret+=matriz[i][j]+", ";
			}
			toret+="}\n";
		}
		toret+="\n";
		return toret;
	}

	// Implementar los siguientes métodos para conseguir un resultado correcto:

	private static String sigloXX() {
		String toret="";
		for(int i=0;i<peliculasYseries.size();i++) {
			int anho=peliculasYseries.get(i).getAnhoPubli();
			if(anho>1899 && anho<2000)toret+=peliculasYseries.get(i).toString()+"\n---------------------\n";
		}
		return toret;
	}

	private static Serie serieMasCorta() {
		int pos=0;
		boolean ok=false;
		for(int i=0;i<peliculasYseries.size();i++) {
			if(peliculasYseries.get(i) instanceof Serie) {
				ok=true;
				pos=i;
				break;
			}
		}
		
		for(int i=0;i<peliculasYseries.size();i++) {
			if(peliculasYseries.get(i) instanceof Serie) {
				if(peliculasYseries.get(i).duracionTotal()<peliculasYseries.get(pos).duracionTotal())pos=i;
			}
		}
		
		
		if(!ok)return null;
		else return (Serie)peliculasYseries.get(pos);
	}

	private static Pelicula peliculaMasLarga() {
		int pos=0;
		boolean ok=false;
		for(int i=0;i<peliculasYseries.size();i++) {
			if(peliculasYseries.get(i) instanceof Pelicula) {
				ok=true;
				pos=i;
				break;
			}
		}
		
		for(int i=1;i<peliculasYseries.size();i++) {
			if(peliculasYseries.get(i) instanceof Pelicula) {
				if(peliculasYseries.get(i).duracionTotal()>peliculasYseries.get(pos).duracionTotal())pos=i;
			}
		}
		if(!ok)return null;
		else return (Pelicula) peliculasYseries.get(pos);
	}

	private static void mostrartodo() {
		for(int i=0; i<peliculasYseries.size();i++) {
			System.out.println(peliculasYseries.get(i).toString());
			System.out.println("------------------------------------------------------");
		}
	}
}
