package main;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Direccion {
	private static Scanner sc=new Scanner(System.in);
	private String tipoCalle, calle ,ciudad, provincia;
	private int cP, planta;
	char letra;
	
	private static final String[] provincias = {"alava","albacete","alicante","almeria","asturias","avila","badajoz","barcelona","burgos",
			"caceres","cadiz","cantabria","castellon","ciudad real","cordoba","a coruña","cuenca","gerona","granada","guadalajara","guipuzcoa",
			"huelva","huesca","islas baleares","jaen","leon","lerida","lugo","madrid","malaga","murcia","navarra","ourense","palencia",
			"las palmas","pontevedra","la rioja","salamanca","segovia","sevilla","soria","tarragona","tenerife","teruel","toledo","valencia",
			"valladolid","vizcaya","zamora","zaragoza"};
	private static final List<String> listaProvincias = Arrays.asList(provincias);
	private static final char[]letras= {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'}; 
	//constructor por defecto
	public Direccion(String a) {
		this.tipoCalle="";
		this.calle="";
		this.letra='A';
		this.planta=0;
		this.ciudad="";
		this.provincia="";
		this.cP=11111;
	}
	//constructor para el bucle i
	public Direccion(int i) {
		this.tipoCalle="tipoCalle"+i;
		this.calle="calle"+i;
		this.letra=letras[i];
		this.planta=i;
		this.ciudad="ciudad"+i;
		this.provincia=provincias[i];
		this.cP=11111;
	}

	//constructor mediante consola
	public Direccion(boolean itsPiso) {
		boolean repeat=false;
		int aux;
		String aux2;
		char aux3;
		
		System.out.println("Tipo de calle:");
		this.tipoCalle=sc.nextLine();
		System.out.println("Nombre de la calle: ");
		this.calle=sc.nextLine();
		
		if(itsPiso) {
		do {
			repeat=false;
			System.out.println("Letra del piso (Si escribe varias se cogerá la primera:): ");
			aux2=sc.nextLine();
			aux2=aux2.toUpperCase();
			aux3=aux2.charAt(0);
			if(!correctLetra(aux3)) {
				System.out.println("Letra incorrecta.Por favor, inténtelo de nuevo;");
				repeat=true;
			}
		}while(repeat);
		this.letra=aux3;
		
		System.out.println("Número de planta: ");
		this.planta=Integer.parseInt(sc.nextLine());
		}else {
			this.letra='A';
			this.planta=1;
		}
		
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
	private boolean correctLetra(char letra) {
		for(int i=0;i<letras.length;i++) {
			if(letra==letras[i])return true;
		}
		return false;
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

	public char getLetra() {
		return letra;
	}

	public void setNum(char letra) {
		this.letra = letra;
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

	public String toString(boolean itsPiso) {
		String toret= getTipoCalle()+" "+getCalle();
		if(itsPiso)toret+=" planta:"+getPlanta()+" letra:"+getLetra();
		toret+="\n"+getCiudad()+" "+getProvincia()+" "+getcP();
		return toret;
	}
	
}


