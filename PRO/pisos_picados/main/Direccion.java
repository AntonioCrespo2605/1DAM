package main;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Direccion {
	private static Scanner sc=new Scanner(System.in);
	private String tipoCalle, calle ,ciudad, provincia;
	private int num, cP, planta;
	private static final String[] provincias = {"alava","albacete","alicante","almeria","asturias","avila","badajoz","barcelona","burgos",
			"caceres","cadiz","cantabria","castellon","ciudad real","cordoba","a coruña","cuenca","gerona","granada","guadalajara","guipuzcoa",
			"huelva","huesca","islas baleares","jaen","leon","lerida","lugo","madrid","malaga","murcia","navarra","ourense","palencia",
			"las palmas","pontevedra","la rioja","salamanca","segovia","sevilla","soria","tarragona","tenerife","teruel","toledo","valencia",
			"valladolid","vizcaya","zamora","zaragoza"};
	private static final List<String> listaProvincias = Arrays.asList(provincias);
	
	//constructor por defecto
	public Direccion(String a) {
		this.tipoCalle="";
		this.calle="";
		this.num=1;
		this.planta=0;
		this.ciudad="";
		this.provincia="";
		this.cP=11111;
	}
	//constructor para el bucle i
	public Direccion(int i) {
		this.tipoCalle="tipoCalle"+i;
		this.calle="calle"+i;
		this.num=i;
		this.planta=i;
		this.ciudad="ciudad"+i;
		this.provincia=provincias[i];
		this.cP=11111;
	}

	//constructor mediante consola
	public Direccion() {
		boolean repeat=false;
		int aux;
		String aux2;
		
		System.out.println("Tipo de calle:");
		this.tipoCalle=sc.nextLine();
		System.out.println("Nombre de la calle: ");
		this.calle=sc.nextLine();
		
		do {
		System.out.println("Número del piso: ");
		aux=Integer.parseInt(sc.nextLine());
		if(aux<=0) {
			System.out.println("El número del piso debe ser 1 o más. Porfavor inténtelo de nuevo;");
			repeat=true;
		}else repeat=false;
		}while(repeat);
		this.num=aux;
		
		System.out.println("Número de planta: ");
		this.planta=Integer.parseInt(sc.nextLine());
		System.out.println("Ciudad: ");
		this.ciudad=sc.nextLine();
		
		do {
		System.out.println("Provincia:");
		aux2=sc.nextLine();
		aux2=aux2.trim();
		aux2=aux2.toLowerCase();
		if(correctProvincia(aux2))repeat=false;
		else repeat=true;
		}while(repeat);
		
		do {
		System.out.println("Código postal:");
		aux=Integer.parseInt(sc.nextLine());
		if(aux<8033||aux>99999) {
			repeat=true;
			System.out.println("El código postal debe estar entre 8033 y 99999. Porfavor, inténtelo de nuevo;");
		}
		else repeat=false;
		}while(repeat);
	}
	
	private boolean correctProvincia(String pro) {
		if(listaProvincias.contains(pro))return true;
		else {
			System.out.println("No se ha encontrado la provincia. Por favor, vuelva a intentarlo (Sin tildes);");
			System.out.println("Provincias disponibles:");
			showProvincias();
			return false;
			}
	}
	
	private void showProvincias() {
		System.out.println("---------------------------------------------");
		for(int i=0;i<provincias.length;i++) {
			if(i==provincias.length-1)System.out.print(provincias[i]+".");
			else System.out.print(provincias[i]+",");
			if(i%5==0)System.out.println();
		}
		System.out.println();
		System.out.println("---------------------------------------------");
	}
	
	
	public String getTipoCalle() {
		return tipoCalle;
	}

	public void setTipoCalle(String tipoCalle) {
		this.tipoCalle = tipoCalle;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getcP() {
		return cP;
	}

	public void setcP(int cP) {
		this.cP = cP;
	}

	public int getPlanta() {
		return planta;
	}

	public void setPlanta(int planta) {
		this.planta = planta;
	}

	public String toString() {
		return getTipoCalle()+" "+getCalle()+" planta:"+getPlanta()+" num:"+getNum()+"\n"+getCiudad()+" "+getProvincia()+" "+getcP();
	}
	
}

