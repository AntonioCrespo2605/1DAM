package main;

import java.util.Scanner;

public class Simulacion {
	
	static Scanner sc=new Scanner(System.in);
	private Espectador [][]sala = new Espectador [8][9];
	private boolean [][]ocupacion=new boolean[8][9];
	private Pelicula peli=new Pelicula();
	private final char[]LETRAS= {'A','B','C','D','E','F','G','H','I'};
	private Cine cine=new Cine();
	
	public Simulacion() {
		this.ocupacion=setOcupacionToFalse();
		System.out.println("Datos de la peli:");
		this.peli=buildPeli();
		this.cine=buildCine();
		boolean other=true;
		String yesno;
		boolean tryother=true;
		do {
			if(full()) {
				other=false;
				System.out.println("Se ha llenado la sala;");
				break;
			}else {
				System.out.println("Desea introducir un nuevo espectador?");
				yesno=sc.nextLine();
				yesno=yesno.trim();
				yesno=yesno.toLowerCase();
				if(!(yesno.equals("s")||yesno.equals("n")))System.out.println("Opción incorrecta, porfavor inténtelo de nuevo;");
				else if(yesno.equals("s")) {
					
					Espectador aux=buildEspectador();
					if(aux.getDinero()>=this.cine.getPrecio()&&aux.getEdad()>=this.cine.getPeli().getEdadMin()) {
					do {
					for(int i=0;i<this.ocupacion.length;i++) {
						for(int j=0;j<this.ocupacion[i].length;j++) {
							if(this.ocupacion[i][j]==false) {
								if(genNumRandom()==26) {
									this.ocupacion[i][j]=true;
									this.sala[i][j]=aux;
									tryother=false;
									System.out.println("Al espectador "+aux.getNombre()+" se le ha asignado el asiento "+place(i,j));
								}
							}
						}
					}
					}while(tryother);
					
					
					}else {
						if(aux.getDinero()<this.cine.getPrecio())System.out.println("La persona no tiene saldo suficiente;");
						if(aux.getEdad()>=this.cine.getPeli().getEdadMin())System.out.println("La persona no tiene la edad mínima requerida;");
					}
					
				}else if(yesno.equals("n")) {
					other=false;
					break;
				}
			}
		}while(other);
		
		
	}
	
	private int genNumRandom(){
	       int num=(int)Math.random()*(72+1);
	       return num;
	   }
	
	public boolean full() {
		for(int i=0;i<this.ocupacion.length;i++) {
			for(int j=0;j<this.ocupacion[i].length;j++) {
				if(this.ocupacion[i][j]==false)return false;
			}
		}
		return true;
	}
	
	public Pelicula getPelicula() {
		return this.peli;
	}
	
	public Cine buildCine() {
		Cine toret=new Cine();
		System.out.println("Precio de la peli (se tomará el valor absoluto):");
		toret.setPrecio(Float.parseFloat(sc.nextLine()));
		toret.setPeli(getPelicula());
		return toret;
	}
	
	public void setCine(Pelicula p, float precio) {
		this.cine.setPeli(p);
		this.cine.setPrecio(precio);
	}
	
	public void setPlace(Espectador e,int i, int j) {
		this.sala[i][j]=e;
	}
	
	private String place(int i, int j) {
		return (""+(8-i)+LETRAS[j]);
	}
	
	private boolean[][] setOcupacionToFalse() {
		boolean[][]toret=new boolean[8][9];
		for(int i=0;i<toret.length;i++) {
			for(int j=0;j<toret[i].length;j++) {
				toret[i][j]=false;
			}
		}
		return toret;
	}
	
	private Pelicula buildPeli() {
		Pelicula toret=new Pelicula();
		System.out.println("Título de la peli: ");
		toret.setTitulo(sc.nextLine());
		System.out.println("Duración de la peli '"+toret.getTitulo()+"' en minutos(si se introduce un valor negativo se tomará como positivo): ");
		toret.setDuracion(Integer.parseInt(sc.nextLine()));
		System.out.println("Edad mínima para ver la peli '"+toret.getTitulo()+"'(si se introduce un valor negativo se tomará como positivo): ");
		toret.setEdadMin(Integer.parseInt(sc.nextLine()));
		System.out.println("Director de la peli'"+toret.getTitulo()+"';");
		toret.setDirector(sc.nextLine());
		return toret;
	}
	
	private Espectador buildEspectador() {
		Espectador toret=new Espectador();
		System.out.println("Nombre:");
		toret.setNombre(sc.nextLine());
		System.out.println("Edad(se tomará el valor absoluto)");
		int edad=Integer.parseInt(sc.nextLine());
		if(edad<0)edad=-edad;
		toret.setEdad(edad);
		System.out.println("Dinero");
		toret.setDinero(Float.parseFloat(sc.nextLine()));
		return toret;
	}
	
	public void whoHasThePlace(int num, char letra) {
		int i;
		int j=0;
		boolean check=true;
		String aux=letra+"";
		aux=aux.toLowerCase();
		
		if(num<1||num>8) {
			System.out.println("No existe el número (Escriba el número entre 1 y 8)");
			check=false;
		}
		if(!(aux.equals("a")||aux.equals("b")||aux.equals("c")||aux.equals("d")||aux.equals("e")||aux.equals("f")||aux.equals("g")||aux.equals("h")||aux.equals("i"))) {
			System.out.println("No exixte la letra (Entre a-i)");
			check=false;
		}
		if(check) {
			aux=aux.toUpperCase();
			letra=aux.charAt(0);
			i=8-num;
			for(int x=0;x<9;x++) {
				if(this.LETRAS[x]==letra) {
					j=x;
					break;
				}
			}
			if(this.ocupacion[i][j]) {
			System.out.println("El sitio "+num+letra+" es:"+this.sala[i][j].getNombre());
			}else System.out.println("Sitio vacío");
		}
	}
}
