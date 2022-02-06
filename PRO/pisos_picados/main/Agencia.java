package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Agencia {
	private static Scanner sc=new Scanner(System.in);
	private String nombre;
	private ArrayList<Piso> pisos;
	private int contId=1;
	
	//getter y setter privados del contador de IDs para no repetirlas
	private void setContId() {
		this.contId++;
	}
	private int getContId() {
		return this.contId;
	}
	
	
	//constructor al que le llega un nombre y autogenera 10 pisos
	public Agencia(String nombre) {
		this.nombre=nombre;
		this.pisos=autoGenTenAparments();
	}
	
	//constructor por defecto;
	public Agencia() {
		this.nombre="";
		this.pisos.add(new Piso());
	}
	
	//constructor copia
	public Agencia(Agencia agencia) {
		this.pisos=agencia.pisos;
		this.nombre=agencia.nombre;
	}
	
	//autogenera 10 pisos, 5 en venta y 5 en alquiler
	private ArrayList<Piso> autoGenTenAparments() {
		ArrayList<Piso>aux=new ArrayList<Piso>();
		Piso pisoaux;
		//en alquiler
		for(int i=1;i<6;i++) {
			pisoaux = new Piso(i,("name "+i),("direction "+i),i*10,true,(i*100),false,1);
			aux.add(pisoaux);
		}
		//en venta
		for(int i=5;i<11;i++) {
			pisoaux = new Piso(i,("name "+i),("direction "+i),i*10,false,1,true,(i*100));
			aux.add(pisoaux);
		}
		
		contId=11;
		return aux;
	}
	
	//getters y setters
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	public String getNombre() {
		return this.nombre;
	}
	private ArrayList<Piso> getPisos() {
		return this.pisos;
	}
	//añade un nuevo piso
	public void addNewAparment() {
		System.out.println("Se ha generado un piso con el ID"+getContId());
		Piso pisoaux=newAparment();
		pisoaux.setId(getContId());
		setContId();
		this.pisos.add(pisoaux);
		
	}
	
	//crea un nuevo apartamento
	private Piso newAparment() {
		Piso pisoaux=new Piso();
		String aux;
		int aux1=0;
		float aux2;
		boolean ventaux=false;
		boolean alquiaux=false;
		pisoaux.setId(0);
		System.out.println("Nombre del piso:");
		pisoaux.setNombre(sc.nextLine());
		System.out.println("Direccion del piso:");
		pisoaux.setDireccion(sc.nextLine());
		
		do {
		System.out.println("Superficie en metros cuadrados:");
		aux1=Integer.parseInt(sc.nextLine());
		if(surfaceValid(aux1)==false)System.out.println("Superficie negativa o nula. Porfavor, inténtelo de nuevo");
		}while(surfaceValid(aux1)==false);
		pisoaux.setMetrosCuadrados(aux1);
		
		do {
		do{
		System.out.println("Disponibilidad para venta(s/n):");
		aux=sc.nextLine();
		aux=aux.trim();
		aux=aux.toLowerCase();
		if(aux.equals("s"))ventaux=true;
		else if(aux.equals("n"))ventaux=false;
		else System.out.println("Opción desconocida. Porfavor, inténtelo de nuevo;");
		}while(!(aux.equals("s")||aux.equals("n")));
		
		do{
		System.out.println("Disponibilidad para alquiler(s/n):");
		aux=sc.nextLine();
		aux=aux.trim();
		aux=aux.toLowerCase();
		if(aux.equals("s"))alquiaux=true;
		else if(aux.equals("n"))alquiaux=false;
		else System.out.println("Opción desconocida. Porfavor, inténtelo de nuevo;");
		}while(!(aux.equals("s")||aux.equals("n")));
		
		if(alquiaux==false&&ventaux==false)System.out.println("El piso debe estar disponible para venta y/o alquiler. Porfavor inténtelo de nuevo;");
		}while(alquiaux==false&&ventaux==false);
		pisoaux.setAlquiler2(alquiaux);
		pisoaux.setVenta2(ventaux);
		
		if(pisoaux.getVenta()) {
			do {
			System.out.println("Precio de venta:");
			aux2=Float.parseFloat(sc.nextLine());
			if(aux2<0)System.out.println("El precio de venta no puede ser negativo. Porfavor, inténtelo de nuevo;");
			}while(aux2<0);
			pisoaux.setPrecioVenta(aux2);
		}else pisoaux.setPrecioVenta(0);
		
		if(pisoaux.getAlquiler()) {
			do {
			System.out.println("Precio de alquiler:");
			aux2=Float.parseFloat(sc.nextLine());
			if(aux2<0)System.out.println("El precio de alquiler no puede ser negativo. Porfavor, inténtelo de nuevo;");
			}while(aux2<0);
			pisoaux.setPrecioAlquiler(aux2);
		}else pisoaux.setPrecioAlquiler(0);
		
		return pisoaux;
	}
	
	//comprueba que una superficie sea válida
	private boolean surfaceValid(int superficie) {
		if(superficie<=0)return false;
		else return true;
	}
	
	//elimina un piso a partir de su id
	public void deleteAparmentWithId(int id) {
		int cont=0;
		for(int i=0;i<getPisos().size();i++) {
			if(getPisos().get(i).getId()==id) {
				this.pisos.remove(i);
				cont++;
			}
		}
		if(cont==0)System.out.println("No se han encontrado pisos con este ID;");
	}
	
	//devuelve la posicion del arrayList que contiene el id introducido. Si no se encuentra el id devuelve -1
	public int getPositionOfId(int id) {
		int position=0;
		int cont=0;
		
		for(int i=0;i<getPisos().size();i++) {
			if(getPisos().get(i).getId()==id) {
				cont++;
				position=i;
				break;
			}
		}
		
		if(cont==0)return -1;
		else return position;
	}
	
	//modificar un piso a partir de su id
	public void updateAparmentWithId(int id) {
		int position=0;
		int cont=0;
		
		for(int i=0;i<getPisos().size();i++) {
			if(getPisos().get(i).getId()==id) {
				cont++;
				position=i;
				break;
			}
		}
		
		if(cont==0)System.out.println("No se han encontrado pisos con este ID;");
		else {
			Piso pisoaux=newAparment();
			pisoaux.setId(getPisos().get(position).getId());
		}
	}
	
	//lista los apartamentos
	public void showAllAparments() {
		int cont=0;
		for(int i=0;i<getPisos().size();i++) {
			System.out.println("--------------------------------------------");
			System.out.println(getPisos().get(i).toString());
			cont++;
			}
		if(cont==0)System.out.println("No se han encontrado pisos en esta agencia :( ;");
		else System.out.println("--------------------------------------------");
		}
	
	//lista los pisos de forma corta
	public void showAllAparmentsShort() {
		int cont=0;
		for(int i=0;i<getPisos().size();i++) {
			System.out.println("-------------------------------------------------------------------------------------------");
			System.out.println(getPisos().get(i).toStringShort());
			cont++;
			}
			
			if(cont==0)System.out.println("No se han encontrado pisos en esta agencia :( ;");
			else System.out.println("-------------------------------------------------------------------------------------------");
		}
		
		
	//devuelve una Agencia auxiliar filtrada con solo los pisos a la venta de la que le llega
	public Agencia OnlyForSale(){
		Agencia aux=new Agencia(this);
		
		for(int i=0;i<aux.getPisos().size();i++) {
			if(aux.getPisos().get(i).getVenta()==false)aux.getPisos().remove(i);
		}
		
		return aux;
	}
	
	//devuelve una Agencia auxiliar filtrada con solo los pisos en alquiler de la que le llega
	public Agencia OnlyForRent(){
		Agencia aux=new Agencia(this);
		
		for(int i=0;i<aux.getPisos().size();i++) {
			if(aux.getPisos().get(i).getAlquiler()==false)aux.getPisos().remove(i);
		}
		
		return aux;
	}
	
	//devuelve una Agencia auxiliar filtrada con solo los pisos entre en rango de valores de precio
	public Agencia OnlyBetweenPrice(float min, float max){
		Agencia aux=new Agencia(this);
		float precio_min=0;
		float precio_max=0;
		
		for(int i=0;i<aux.getPisos().size();i++) {
			if(aux.getPisos().get(i).getAlquiler()&&aux.getPisos().get(i).getVenta()) {
				precio_min=aux.getPisos().get(i).getPrecioAlquiler();
				precio_max=aux.getPisos().get(i).getPrecioAlquiler();
				if(aux.getPisos().get(i).getPrecioVenta()<precio_min)precio_min=aux.getPisos().get(i).getPrecioVenta();
				if(aux.getPisos().get(i).getPrecioVenta()>precio_max)precio_max=aux.getPisos().get(i).getPrecioVenta();
			}else if(aux.getPisos().get(i).getAlquiler()) {
				precio_min=aux.getPisos().get(i).getPrecioAlquiler();
				precio_max=precio_min;
			}else {
				precio_min=aux.getPisos().get(i).getPrecioVenta();
				precio_max=precio_min;
			}
			if((precio_min<min&&precio_max<min)||(precio_min>max&&precio_max>min))aux.getPisos().remove(i);
		}
		
		return aux;
	}
	
	//devuelve una Agencia auxiliar filtrada con solo los pisos entre en rango de valores de superficie
	public Agencia OnlyBetweenSurfaces(float min, float max){
		Agencia aux=new Agencia(this);
		int aux2;
		for(int i=0;i<aux.getPisos().size();i++) {
			aux2=aux.getPisos().get(i).getMetrosCuadrados();
			if(aux2<min||aux2>max)aux.getPisos().remove(i);
		}
			
		return aux;
	}
	
	
	
		
}
