package main;

public class Cine {
	private Pelicula pelicula;
	private float precio_entrada;
	
	public Cine(Pelicula peli, float precio) {
		if(precio<0)precio=-precio;
		this.pelicula=peli;
		this.precio_entrada=precio;
	}
	public Cine() {
		this.pelicula=new Pelicula();
		this.precio_entrada=0;
	}
	public void setPeli(Pelicula peli) {
		this.pelicula=peli;
	}
	public void setPrecio(float precio) {
		if(precio<0)precio=-precio;
		this.precio_entrada=precio;
	}
	public Pelicula getPeli() {
		return this.pelicula;
	}
	public float getPrecio() {
		return this.precio_entrada;
	}
	
	public String toString() {
		return ("La pelicula "+getPeli()+" cuesta "+getPrecio()+"euros;");
	}
}

