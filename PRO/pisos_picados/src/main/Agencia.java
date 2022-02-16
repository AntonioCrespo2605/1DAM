package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Agencia {
	private static Scanner sc=new Scanner(System.in);
	private String nombre;
	private ArrayList<Piso> pisos;
	private static int contId=1;
	
	//getter y setter privados del contador de IDs para no repetirlas
	private void setContId() {
		contId++;
	}
	
	private int getContId() {
		return contId;
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
	
	//autogenera 10 propiedades, 5 en venta y 5 en alquiler
	private ArrayList<Piso> autoGenTenAparments() {
		ArrayList<Piso>aux=new ArrayList<Piso>();
		Piso pisoaux;
		//en alquiler
		for(int i=1;i<6;i++) {
			pisoaux = new Piso(i,(new Direccion(i)),i*10,true,(i*100),false,1,true);
			aux.add(pisoaux);
		}
		//en venta
		for(int i=6;i<11;i++) {
			pisoaux = new Piso(i,(new Direccion(i)),i*10,false,1,true,(i*100),false);
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
	
	//añade una nueva propiedad
	public void addNewAparment() {
		System.out.println("Se ha generado una propiedad con el ID"+getContId());
		Piso pisoaux=newAparment();
		pisoaux.setId(getContId());
		setContId();
		this.pisos.add(pisoaux);
		
	}
	
	//crea una nueva propiedad
	private Piso newAparment() {
		Piso pisoaux=new Piso();
		String aux;
		float aux2;
		boolean aux3;
		boolean itsPiso=true;
		boolean ventaux=false;
		boolean alquiaux=false;
		pisoaux.setId(0);
		
		do {
		aux3=false;
		System.out.println("Escriba 'piso' si es piso o 'casa' si es casa:");
		aux=sc.nextLine();
		aux=aux.toLowerCase();
		if(!(aux.equals("piso")||aux.equals("casa"))) {
			System.out.println("Tipo de propiedad desconocida. Porfavor, inténtelo de nuevo");
			aux3=true;
		}else {
			if(aux.equals("piso"))itsPiso=true;
			else if(aux.equals("casa"))itsPiso=false;
			aux3=false;
		}
		}while(aux3);
		pisoaux.setItsPiso(itsPiso);
		
		System.out.println("Direccion de la propiedad:");
		pisoaux.setDireccion(new Direccion(itsPiso));
		
		do {
		System.out.println("Superficie en metros cuadrados:");
		aux2=Float.parseFloat(sc.nextLine());
		if(surfaceValid(aux2)==false)System.out.println("Superficie negativa o nula. Porfavor, inténtelo de nuevo");
		}while(surfaceValid(aux2)==false);
		pisoaux.setMetrosCuadrados(aux2);
		
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
		
		System.out.println("Piso creado con exito;\n");
		return pisoaux;
	}
	
	//comprueba que una superficie sea válida
	private boolean surfaceValid(float superficie) {
		if(superficie<=0)return false;
		else return true;
	}
	
	//elimina una propiedad a partir de su id
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
	
	//modificar una propiedad a partir de su id
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

	//modifica la direccion de una propiedad a partir de su id
	public void setAparmentAdressWithId(int id) {
		System.out.println("Escriba la nueva dirección:");
		this.pisos.get(getPositionOfId(id)).setDireccion(new Direccion(this.pisos.get(getPositionOfId(id)).getItsPiso()));
	}
	
	//modifica la disponibilidad de venta de una propiedad
	public void setOnSaleApatmentWithId(int id) {
		String aux;
		boolean repeat=true;
		int pos=getPositionOfId(id);
		
		if(pos>=0) {
		System.out.println("Para activar la venta escriba s, para desactivarla n y para cancelar c;");
		aux=sc.nextLine();
		aux.toLowerCase();
		aux.trim(); 
		
		do {
			switch(aux) {
			case "s":
				activarVentaDelPiso(pos);
				repeat=false;
				break;
			case "n":
				desactivarVentaDelPiso(pos);
				repeat=false;
				break;
			case "c":
				repeat=false;
				break;
			default:System.out.println("Opción incorrecta. Por favor inténtelo de nuevo;");
			break;
			}
		}while(repeat);
		}
	}
	
	//modifica el tipo de propiedad (piso/casa)
	public void setType(int id) {
		int posicion=getPositionOfId(id);
		String op="";
		boolean repeat=false;
		
		do {
		repeat=false;
		System.out.println("Escriba 'casa' para que la propiedad sea una casa o 'piso' para que sea un piso:");
		op=sc.nextLine();
		switch(op) {
		case "casa":getPisos().get(posicion).setItsPiso(false);
			break;
		case "piso":getPisos().get(posicion).setItsPiso(true);
			break;
		default:repeat=true;
			break;
		}
		}while(repeat);
		
		
	}
	
	//activar la disponibilidad de venta de una propiedad
	private void activarVentaDelPiso(int pos) {
		boolean vent=this.pisos.get(pos).getVenta();
		if(vent)System.out.println("El piso seleccionado ya está en venta;");
		else {
			this.pisos.get(pos).setVenta(true);
			this.pisos.get(pos).setPrecioVenta(0);
			System.out.println("Se ha puesto el precio de venta por defecto a cero;");
		}
	}
	
	//desactivar la disponibilidad de venta de una propiedad
	private void desactivarVentaDelPiso(int pos) {
		boolean alq=this.pisos.get(pos).getAlquiler();
		if(alq==false)System.out.println("No se puede cancelar la venta de un piso si tampoco está en alquiler;");
		else {
			this.pisos.get(pos).setVenta(false);
			this.pisos.get(pos).setPrecioVenta(0);
		}
	}
	
	//modifica el precio de venta de una propiedad
	public void setOnSaleAparmentPriceWithId(int id) {
		int pos=getPositionOfId(id);
		
		if(pos>=0) {
			System.out.println("Escribe el nuevo precio de venta:");
			float precio=Float.parseFloat(sc.nextLine());
			if(precio<0)System.out.println("Precio inválido. Debe ser cero o mayor;");
			else this.pisos.get(pos).setPrecioVenta(precio);	
		}
	}
	
	//modifica la disponibilidad de alquiler de una propiedad
	public void setOnRentApatmentWithId(int id) {
		String aux;
		boolean repeat=true;
		int pos=getPositionOfId(id);
		if(pos>=0) {	
		System.out.println("Para activar el alquiler escriba s, para desactivarlo n y para cancelar c;");
		aux=sc.nextLine();
		aux.toLowerCase();
		aux.trim(); 
		
		do {
			switch(aux) {
			case "s":
				activarAlquilerDelPiso(pos);
				repeat=false;
				break;
			case "n":
				desactivarAlquilerDelPiso(pos);
				repeat=false;
				break;
			case "c":
				repeat=false;
				break;
			default:System.out.println("Opción incorrecta. Por favor inténtelo de nuevo;");
			break;
			}
		}while(repeat);
		}
	}
	
	//activar la disponibilidad de alquiler de una propiedad
	private void activarAlquilerDelPiso(int pos) {
		boolean alq=this.pisos.get(pos).getAlquiler();
		if(alq)System.out.println("El piso seleccionado ya está en alquiler;");
		else {
			this.pisos.get(pos).setAlquiler(true);
			this.pisos.get(pos).setPrecioAlquiler(0);
			System.out.println("Se ha puesto el precio de venta por defecto a cero;");
		}
	}
	
	//desactivar la disponibilidad de alquiler de una propiedad
	private void desactivarAlquilerDelPiso(int pos) {
		boolean vent=this.pisos.get(pos).getVenta();
		if(vent==false)System.out.println("No se puede cancelar el alquiler de un piso si tampoco está en venta;");
		else {
			this.pisos.get(pos).setAlquiler(false);
			this.pisos.get(pos).setPrecioAlquiler(0);
		}
	}
	
	//modifica el precio de alquiler de la propiedad
	public void setOnRentAparmentPriceWithId(int id) {
		int pos=getPositionOfId(id);
			
		if(pos>=0) {
			System.out.println("Escribe el nuevo precio de alquiler:");
			float precio=Float.parseFloat(sc.nextLine());
			if(precio<0)System.out.println("Precio inválido. Debe ser cero o mayor;");
			else this.pisos.get(pos).setPrecioAlquiler(precio);	
		}
	}
	
	//modifica la superficie de la propiedad
	public void setSurfaceOfAparmentWithId(int id) {
		int pos=getPositionOfId(id);
		
		if(pos>=0) {
		System.out.println("Escribe la nueva superficie en metros cuadrados:");
		float superficie=Float.parseFloat(sc.nextLine());
		if(superficie<=0)System.out.println("Superficie inválida. Debe ser más de 0;");
		else this.pisos.get(pos).setMetrosCuadrados(superficie);
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
		int cont=0;
		do {
		cont=0;
		for(int i=0;i<aux.getPisos().size();i++) {
			if(aux.getPisos().get(i).getVenta()==false) {
				aux.getPisos().remove(i);
				cont++;
			}
		}
		}while(cont>0);
		
		return aux;
	}
	
	//devuelve una Agencia auxiliar filtrada con solo los pisos en alquiler de la que le llega
	public Agencia OnlyForRent(){
		Agencia aux=new Agencia(this);
		int cont=0;
		
		do {
		cont=0;
		for(int i=0;i<aux.getPisos().size();i++) {
			if(aux.getPisos().get(i).getAlquiler()==false) {
				aux.getPisos().remove(i);
				cont++;
			}
		}
		}while(cont>0);
		
		return aux;
	}
	
	//devuelve una Agencia auxiliar filtrada con solo los pisos entre en rango de valores de precio
	public Agencia OnlyBetweenPrice(float min, float max){
		Agencia aux=new Agencia(this);
		float precio_min=0;
		float precio_max=0;
		int cont=0;
		
		do {
		cont=0;
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
			if((precio_min<min&&precio_max<min)||(precio_min>max&&precio_max>min)) {
				aux.getPisos().remove(i);
				cont++;
			}
		}
		}while(cont>0);
		
		return aux;
	}
	
	//devuelve una Agencia auxiliar filtrada con solo los pisos entre en rango de valores de superficie
	public Agencia OnlyBetweenSurfaces(float min, float max){
		Agencia aux=new Agencia(this);
		float aux2;
		int cont=0;
		
		do {
		cont=0;
		for(int i=0;i<aux.getPisos().size();i++) {
			aux2=aux.getPisos().get(i).getMetrosCuadrados();
			if(aux2<min||aux2>max) {
				aux.getPisos().remove(i);
				cont++;
			}
		}
		}while(cont>0);
			
		return aux;
	}
	
	//devuelve la cantidad de pisos disponibles
	public int howManyPisos() {
		int toret=getPisos().size();
		return toret;
	}
	
	//devuelve una Agencia auxiliar filtrada con solo Pisos
	public Agencia onlyAparments() {
		Agencia aux=new Agencia(this);
		int cont=0;
		
		do {
		cont=0;
		for(int i=0;i<aux.getPisos().size();i++) {
			if(aux.getPisos().get(i).getItsPiso()==false) {
				aux.getPisos().remove(i);
				cont++;
			}
		}
		}while(cont>0);
		
		return aux;
	}

	//devuelve una Agencia auxiliar filtrada con solo casas
	public Agencia onlyHouses() {
		Agencia aux=new Agencia(this);
		int cont=0;
		
		do {
		cont=0;
		for(int i=0;i<aux.getPisos().size();i++) {
			if(aux.getPisos().get(i).getItsPiso()) {
				aux.getPisos().remove(i);
				cont++;
			}
		}
		}while(cont>0);
		
		return aux;
	}


}