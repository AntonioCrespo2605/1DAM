package main;

public class Pelicula {
	private String titulo;
	private int duracion;
	private int edad_min;
	private String director;
	
	public Pelicula(String titulo, int duracion, int edad_min, String director) {
		if(duracion<0) duracion=-duracion;
		if(edad_min<0)edad_min=-edad_min;
		this.titulo=titulo;
		this.duracion=duracion;
		this.edad_min=edad_min;
		this.director=director;
	}
	public Pelicula() {
		this.titulo="Default title";
		this.duracion=0;
		this.edad_min=0;
		this.director="Default director";
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	public int getDuracion() {
		return this.duracion;
	}
	public int getEdadMin() {
		return this.edad_min;
	}
	public String getDirector() {
		return this.director;
	}
	public void setTitulo(String titulo) {
		this.titulo=titulo;
	}
	public void setDuracion(int duracion) {
		if(duracion<0) duracion=-duracion;
		this.duracion=duracion;
	}
	public void setDirector(String director) {
		this.director=director;
	}
	public void setEdadMin(int edad_min) {
		if(edad_min<0)edad_min=-edad_min;
		this.edad_min=edad_min;
	}
	
	public String toString() {
		return ("Título:"+getTitulo()+";\nDuración:"+getDuracion()+"min;\nEdad mínima:"+getEdadMin()+";\nDirector:"+getDirector()+";");
	}
		
}
