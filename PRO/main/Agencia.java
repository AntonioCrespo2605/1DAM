package main;

import java.util.ArrayList;
import java.util.Random;

public class Agencia {
	Random r=new Random();
	private ArrayList<Inmueble> inmuebles;
	private String nombre;
	
	public Agencia(String nombre) {
		this.nombre=nombre;
		this.inmuebles=autogenerar12inmuebles();
	}
	
	public Agencia(String nombre, ArrayList<Inmueble> inmuebles) {
		this.nombre=nombre;
		this.inmuebles=inmuebles;
	}
	
	private ArrayList<Inmueble> autogenerar12inmuebles() {
		ArrayList<Inmueble>toret=new ArrayList<Inmueble>();
		int i;
		for(i=0;i<5;i++) {
			if(r.nextBoolean()) {
				Piso aux=new Piso();
				aux.setVenta(true);
				aux.setAlquiler(false);
				toret.add(aux);
			}else {
				Casa aux2=new Casa();
				aux2.setVenta(true);
				aux2.setAlquiler(false);
				toret.add(aux2);
			} 
		}
		for(i=0;i<5;i++) {
			if(r.nextBoolean()) {
				Piso aux=new Piso();
				aux.setAlquiler(true);
				aux.setVenta(false);
				toret.add(aux);
			}else {
				Casa aux2=new Casa();
				aux2.setAlquiler(true);
				aux2.setVenta(false);
				toret.add(aux2);
			} 
		}
		Piso aux=new Piso();
		aux.setAlquiler(true);
		aux.setVenta(true);
		Casa aux2=new Casa();
		aux2.setAlquiler(true);
		aux2.setVenta(true);
		toret.add(aux);
		toret.add(aux2);
		return toret;
	}

	public ArrayList<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public void setInmuebles(ArrayList<Inmueble> inmuebles) {
		this.inmuebles = inmuebles;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void anhadirInmueble(Piso p) {
		this.inmuebles.add(p);
	}
	
	public void anhadirInmueble(Casa c) {
		this.inmuebles.add(c);
	}
	
	public void mostrarInmuebles() {
		int i;
		for(i=0;i<getInmuebles().size();i++) {
			System.out.println("-------------------------------------");
			System.out.println(getInmuebles().get(i).toString());
		}
		System.out.println("-------------------------------------");
	}
	
	public void mostrarInmueblesShort() {
		int i;
		for(i=0;i<getInmuebles().size();i++) {
			System.out.print(getInmuebles().get(i).toStringShort()+"/");
		}
		System.out.println();
	}
	
	public int obtenerPos(int id) {
		int i;
		for(i=0;i<getInmuebles().size();i++) {
			if(id==getInmuebles().get(i).getId())return i;
		}
		return -1;
	}
	
	public void eliminarInmueble(int id) {
		int pos=obtenerPos(id);
		if(pos!=-1) {
			this.inmuebles.remove(pos);
			System.out.println("Borrado con éxito el inmueble "+id+";");
		} 
		else System.err.println("No se ha encontrado ningún inmueble con el ID "+id+" en esta agencia;");
	}
	
}
