package examen;

import java.util.Random;

public abstract class Audiovisual {
	public static enum plataformas {NETFLIX, HBO, MOVISTAR, AMAZON, DISNEY, OTROS};
	private plataformas plataforma;
	private String titulo, director;
	private int anhoPubli;
	
	public static plataformas platRandom() {
		Random r=new Random();
		plataformas[]platasaux=plataformas.values();
		int pos=r.nextInt(platasaux.length);
		return platasaux[pos];
	}
	
	public Audiovisual(plataformas plataforma, String titulo, String director, int anhoPubli) {
		super();
		this.plataforma = plataforma;
		this.titulo = titulo;
		this.director = director;
		this.anhoPubli = anhoPubli;
	}

	public plataformas getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(plataformas plataforma) {
		this.plataforma = plataforma;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getAnhoPubli() {
		return anhoPubli;
	}

	public void setAnhoPubli(int anhoPubli) {
		this.anhoPubli = anhoPubli;
	}
	
	//Editar el toString para reaprovecharlo en las clases hijas

	@Override
	public String toString() {
		return "\nAño de publicación:"+this.anhoPubli+"\nPlataforma:"+this.plataforma;
	}
	
	public abstract int duracionTotal();
	
}
