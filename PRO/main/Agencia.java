package main;

import java.util.ArrayList;
import java.util.Random;

public class Agencia {
	private static Random r=new Random();
	private ArrayList<Inmueble> inmuebles;
	private String nombre;
	
	public Agencia(String nombre) {
		this.nombre=nombre;
		this.inmuebles=autogenerar12inmuebles();
	}
	
	public Agencia(Agencia agencia) {
		this.inmuebles=agencia.getInmuebles();
		this.nombre="agenciaAuxiliar";
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
		@SuppressWarnings("unchecked")
		ArrayList<Inmueble> toret= (ArrayList<Inmueble>) inmuebles.clone();
		return toret;
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
		} 
		else System.err.println("No se ha encontrado ningún inmueble con el ID "+id+" en esta agencia;");
	}
	
	public void cambiarInmueble(int pos, Inmueble i) {
		this.inmuebles.set(pos, i);
	}
	
	public Agencia inmueblesEntrePrecios(double p1, double p2){
		double pmax;
		double pmin;
		if(p1>p2) {
			pmax=p1;
			pmin=p2;
		}else {
			pmax=p2;
			pmin=p1;
		}
		
		Agencia aux=new Agencia(this);
		int id;
		boolean v;
		boolean a;
		double precioa;
		double preciov;
		int i=0;
		boolean ok;
		int cont;
		
		do {
		cont=0;
		for(i=0;i<aux.getInmuebles().size();i++){
			ok=true;
			id=aux.getInmuebles().get(i).getId();
			precioa=aux.getInmuebles().get(i).getPrecioAlquiler();
			preciov=aux.getInmuebles().get(i).getPrecioVenta();
			a=aux.getInmuebles().get(i).isAlquiler();
			v=aux.getInmuebles().get(i).isVenta();
			
			if(v && !a) {
				if(preciov>pmax||preciov<pmin)ok=false;
			}else if(!v && a) {
				if(precioa>pmax||precioa<pmin)ok=false;
			}else {
				if((preciov>pmax||preciov<pmin)&&(precioa>pmax||precioa<pmin))ok=false;
			}
			if(!ok) {
				aux.eliminarInmueble(id);
				cont++;
			}
		}
		}while(cont!=0);
		return aux;
	}

	public Agencia inmueblesEntreSuperficies(double s1, double s2) {
		double smax;
		double smin;
		if(s1>s2) {
			smax=s1;
			smin=s2;
		}else{
			smax=s2;
			smin=s1;
		}
		
		Agencia aux=new Agencia(this);
		int i=0;
		int id;
		double sup;
		int cont;
		
		do {
		i=0;
		cont=0;
		for(i=0;i<aux.getInmuebles().size();i++) {
			id=aux.getInmuebles().get(i).getId();
			sup=aux.getInmuebles().get(i).getSuperficie();
			if(sup>smax||sup<smin) {
				aux.eliminarInmueble(id);
				cont++;
			}
		}
		}while(cont!=0);
		return aux;
	}

	public Agencia soloPisos() {
		Agencia aux=new Agencia(this);
		int i=0;
		int id;
		int cont;
		
		do {
		i=0;
		cont=0;
		for(i=0;i<aux.getInmuebles().size();i++) {
			id=aux.getInmuebles().get(i).getId();
			if(aux.getInmuebles().get(i) instanceof Casa) {
				aux.eliminarInmueble(id);
				cont++;
			}
		}
		}while(cont!=0);
		return aux;
	}

	public Agencia soloCasas() {
			Agencia aux=new Agencia(this);
			int i=0;
			int id;
			int cont;
			
			do {
			i=0;
			cont=0;
			for(i=0;i<aux.getInmuebles().size();i++) {
				id=aux.getInmuebles().get(i).getId();
				if(aux.getInmuebles().get(i) instanceof Piso) {
					aux.eliminarInmueble(id);
					cont++;
				}
			}
			}while(cont!=0);
			return aux;
	}

	public Agencia soloAlquiler() {
		Agencia aux=new Agencia(this);
		int i=0;
		int id;
		int cont;
		
		do {
		i=0;
		cont=0;
		for(i=0;i<aux.getInmuebles().size();i++) {
			id=aux.getInmuebles().get(i).getId();
			if(!(aux.getInmuebles().get(i).isAlquiler())) {
				aux.eliminarInmueble(id);
				cont++;
			}
		}
		}while(cont!=0);
		return aux;
	}

	public Agencia soloVenta() {
		Agencia aux=new Agencia(this);
		int i=0;
		int id;
		int cont;
		
		do {
		i=0;
		cont=0;
		for(i=0;i<aux.getInmuebles().size();i++) {
			id=aux.getInmuebles().get(i).getId();
			if(!(aux.getInmuebles().get(i).isVenta())) {
				aux.eliminarInmueble(id);
				cont++;
			}
		}
		}while(cont!=0);
		return aux;
	}

}
