package main;

public class Espectador {
	private String nombre;
	private int edad;
	private float dinero;
	
	public Espectador(String nombre, int edad, float dinero) {
		this.nombre=nombre;
		this.edad=edad;
		this.dinero=dinero;
	}
	
	public Espectador() {
		this.nombre="Default name";
		this.edad=0;
		this.dinero=0;
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	public void setEdad(int edad) {
		this.edad=edad;
	}
	public void setDinero(float dinero) {
		this.dinero=dinero;
	}
	public String getNombre() {
		return this.nombre;
	}
	public int getEdad() {
		return this.edad;
	}
	public float getDinero() {
		return this.dinero;
	}
	public String toString() {
		return getNombre()+", "+getEdad()+" años, "+getDinero()+" euros;";
	}
	
	
}

