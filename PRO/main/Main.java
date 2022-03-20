package main;

import java.util.Scanner;

public class Main {
	private static Scanner sc=new Scanner(System.in);
	private static Agencia a;
	
	
	public static void main(String[] args) {
		
		System.out.println("Bienvenido al gestor de Agencias de Inmuebles v2."
				+ "\nPor favor escriba el nombre de su agencia:");
		a=new Agencia(sc.nextLine());
		System.out.println("La agencia "+a.getNombre()+" ha sido creada con éxito."
				+ "\nADVERTENCIA:Se han generado 12 inmuebles aleatorios;\n");
		//
		String op;
		boolean stay=true;
		//
		
		do {
		System.out.println("\nBienvenido a "+a.getNombre()+". Por favor elija una opción:");
		System.out.println("1-Añadir un inmueble a la agencia;"
				+ "\n2-Modificar un inmueble;"
				+ "\n3-Listar todos los inmuebles;"
				+ "\n4-Listar inmuebles con filtros;"
				+ "\n5-Eliminar un inmueble;"
				+ "\n6-Salir de la agencia.");
		op=sc.nextLine();
		op=op.trim();
		switch(op) {
			case"1":
				crearInmueble();
				break;
			case"2":modificarInmueble();
				break;
			case"3":a.mostrarInmuebles();
				break;
			case"4":
				break;
			case"5":
				break;
			case"6":
				stay=false;
				break;
			default:
				System.err.println("Opción desconocida. Por favor inténtelo de nuevo:");
				break;
		}
		}while(stay);
		System.out.println("Hasta la proxima :P");
	}
	
	private static void crearInmueble() {
		String tipo;
		boolean stay=true;
		boolean repeat=false;
		System.out.print("Inmuebles actuales: ");
		a.mostrarInmueblesShort();
		do {
		repeat=false;
		System.out.println("Escribe 'p' para crear un nuevo Piso o 'c' Para crear una nueva Casa:"
				+ "\n si desea salir sin crear nada escriba 'e'");
		tipo=sc.nextLine();
		tipo=tipo.toLowerCase();
		tipo=tipo.trim();
		switch(tipo) {
			case"casa":
			case"c":
			case"house":
				stay=true;
				tipo="c";
				break;
			case"piso":
			case"p":
			case"flat":
				tipo="p";
				stay=true;
				break;
			case"e":
			case"exit":
				stay=false;
				break;
			default:
				repeat=true;
				System.err.println("No existe ese tipo de inmueble. Por favor inténtelo de nuevo;");
				break;
		}
		}while(repeat);
		
		if(stay) {
			
			String toread;
			double toread2;
			int toread3;
			boolean estudio=false;
			boolean toread4=false;
			
			Inmueble aux;
			if(tipo.equals("c"))aux=new Casa();
			else aux=new Piso();
			
			System.out.println("Al nuevo inmueble se le ha asignado el ID "+aux.getId());
			Direccion dir=new Direccion();
			
			System.out.println("Empezaremos creando la dirección;");
			do {
			System.out.println("Escriba el tipo de Dirección:"
					+ "\nTipos:calle, avenida, plaza, camino, carretera o paseo:");
			toread=sc.nextLine();
			toread=toread.toLowerCase();
			if(!comprobarTipoDireccion(toread))System.err.println("Tipo de dirección incorrecto. Porfavor, inténtelo de nuevo;");
			}while(!comprobarTipoDireccion(toread));
			dir.setTipo(toread);
			
			System.out.println("Escriba el nombre de la Dirección:");
			toread=sc.nextLine();
			dir.setNombre(toread);
			
			do {
			System.out.println("Escriba el número de la Dirección:"
					+ "\n(En caso de ser un piso número del portal, no de la planta):");
			toread3=Integer.parseInt(sc.nextLine());
			if(!numMay0(toread3))System.err.println("El número debe ser mayor o igual a 1. Porfavor, inténtelo de nuevo");
			}while(!numMay0(toread3));
			dir.setNum(toread3);
			
			do {
			System.out.println("Escriba el Código postal (entre 8033 y 99999):");
			toread3=Integer.parseInt(sc.nextLine());
			if(toread3<8033 || toread3>99999)System.err.println("Código postal incorrecto. Porfavor, inténtelo de nuevo;");
			}while(toread3<8033 || toread3>99999);
			dir.setCp(toread3);
			
			System.out.println("Muy bien. A continuación especifique los siguientes atributos del inmueble:");
			if(aux instanceof Piso) {
				System.out.println("Escriba el número de planta del Piso:");
				toread3=Integer.parseInt(sc.nextLine());
				((Piso) aux).setNumPiso(toread3);
				
				do {
				System.out.println("Escriba la letra del piso(si escribe varias se tomará la primera):");
				toread=sc.nextLine();
				toread=toread.toUpperCase();
				toread=toread.trim();
				if(!combrobarLetraPiso(toread))System.err.println("Letra incorrecta. Por favor inténtelo de nuevo(a-z):");
				}while(!combrobarLetraPiso(toread));
				((Piso)aux).setLetra(toread.charAt(0));
				
				do {
				System.out.println("¿El piso se trata de un estudio?(s/n):");
				toread=sc.nextLine();
				toread.toLowerCase();
				toread=toread.trim();
				switch(toread) {
				case"s":
				case"si":
				case"yes":
					toread="s";
					estudio=true;
					break;
				case"n":
				case"no":
					toread="n";
					estudio=false;
					break;
				default:
					System.err.println("Opción incorrecta inténtelo de nuevo;");
					break;
				}
				}while(!toread.equals("s")&&!toread.equals("n"));
				((Piso)aux).setEstudio(estudio);
				
			}else if(aux instanceof Casa) {
				estudio=false;
				do {
				System.out.println("¿La casa cuenta con piscina?(s/n):");
				toread=sc.nextLine();
				toread.toLowerCase();
				toread=toread.trim();
				switch(toread) {
				case"s":
				case"si":
				case"yes":
					toread="s";
					toread4=true;
					break;
				case"n":
				case"no":
					toread="n";
					toread4=false;
					break;
				default:
					System.err.println("Opción incorrecta inténtelo de nuevo;");
					break;
				}
				}while(!toread.equals("s")&&!toread.equals("n"));
				((Casa)aux).setPiscina(toread4);
				
				do {
				System.out.println("¿La casa cuenta con jardín?(s/n):");
				toread=sc.nextLine();
				toread.toLowerCase();
				toread=toread.trim();
				switch(toread) {
				case"s":
				case"si":
				case"yes":
					toread="s";
					toread4=true;
					break;
				case"n":
				case"no":
					toread="n";
					toread4=false;
					break;
				default:
					System.err.println("Opción incorrecta inténtelo de nuevo;");
					break;
				}
				}while(!toread.equals("s")&&!toread.equals("n"));
				((Casa)aux).setJardin(toread4);
			}
			
			if(estudio)aux.setNumHab(1);
			else {
				do {
				System.out.println("Escriba el número de habitaciones:");
				toread3=Integer.parseInt(sc.nextLine());
				if(!numMay0(toread3))System.err.println("El número debe ser mayor o igual a 1. Porfavor, inténtelo de nuevo;");
				}while(!numMay0(toread3));
				aux.setNumHab(toread3);
			}
			
			do {
			System.out.println("Escriba la superficie del inmueble:");
			toread2=Double.parseDouble(sc.nextLine());
			if(toread2<=0)System.err.println("La superficie debe ser mayor a 0. Porfavor inténtelo de nuevo;");
			}while(toread2<=0);
			aux.setSuperficie(toread2);
			
			do {
			System.out.println("¿El inmueble tiene garaje?(s/n):");
			toread=sc.nextLine();
			toread.toLowerCase();
			toread=toread.trim();
			switch(toread) {
			case"s":
			case"si":
			case"yes":
				toread="s";
				toread4=true;
				break;
			case"n":
			case"no":
				toread="n";
				toread4=false;
				break;
			default:
				System.err.println("Opción incorrecta inténtelo de nuevo;");
				break;
			}
			}while(!toread.equals("s")&&!toread.equals("n"));
			aux.setGaraje(toread4);
			
			boolean alquiler=false;
			boolean venta=false;
			
			do{
			do {
			System.out.println("¿El inmueble está a la venta?(s/n):");
			toread=sc.nextLine();
			toread.toLowerCase();
			toread=toread.trim();
			switch(toread) {
			case"s":
			case"si":
			case"yes":
				toread="s";
				venta=true;
				break;
			case"n":
			case"no":
				toread="n";
				venta=false;
				break;
			default:
				System.err.println("Opción incorrecta inténtelo de nuevo;");
				break;
			}
			}while(!toread.equals("s")&&!toread.equals("n"));
			
			do {
			System.out.println("¿El inmueble está a la venta?(s/n):");
			toread=sc.nextLine();
			toread.toLowerCase();
			toread=toread.trim();
			switch(toread) {
			case"s":
			case"si":
			case"yes":
				toread="s";
				alquiler=true;
				break;
			case"n":
			case"no":
				toread="n";
				alquiler=false;
				break;
			default:
				System.err.println("Opción incorrecta inténtelo de nuevo;");
				break;
			}
			}while(!toread.equals("s")&&!toread.equals("n"));
			
			if(!alquiler&&!venta)System.err.println("El inmueble debe estar a la venta y/o en alquiler. Porfavor inténtelo de nuevo;");
			}while(!alquiler&&!venta);
			aux.setAlquiler(alquiler);
			aux.setVenta(venta);
			
			if(!alquiler)aux.setPrecioAlquiler(0);
			if(!venta)aux.setPrecioVenta(0);
			
			if(venta) {
				do {
				System.out.println("Escriba el precio de venta:");
				toread2=Double.parseDouble(sc.nextLine());
				if(toread2<0)System.err.println("El precio no puede ser negativo. Porfavor, inténtelo de nuevo;");
				}while(toread2<0);
				aux.setPrecioVenta(toread2);
			}
			if(alquiler) {
				do {
				System.out.println("Escriba el precio de alquiler:");
				toread2=Double.parseDouble(sc.nextLine());
				if(toread2<0)System.err.println("El precio no puede ser negativo. Porfavor, inténtelo de nuevo;");
				}while(toread2<0);
				aux.setPrecioAlquiler(toread2);
			}
			
			if(aux instanceof Casa)a.anhadirInmueble((Casa)aux);
			else if(aux instanceof Piso)a.anhadirInmueble((Piso)aux);
			System.out.println("El inmueble ha sido creado con éxito;");
			System.out.print("Inmuebles actuales: ");
			a.mostrarInmueblesShort();
		}else System.out.println("No se ha añadido ningún piso");	
	}
	
	private static boolean comprobarTipoDireccion(String tipo) {
		final String[] TIPOS= {"calle","avenida","plaza","camino","carretera","paseo"};
		int i;
		for(i=0;i<TIPOS.length;i++) {
			if(tipo.equals(TIPOS[i]))return true;
		}
		return false;
	}
	
	private static boolean numMay0(int num) {
		if(num<1)return false;
		else return true;
	}
	
	private static boolean combrobarLetraPiso(String letra) {
		letra=letra.toUpperCase();
		letra=letra.trim();
		char aux=letra.charAt(0);
		final char[] LETRAS= {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		
		int i;
		for(i=0;i<LETRAS.length;i++) {
			if(LETRAS[i]==aux)return true; 	
		}
		return false;
	}
	
	private static void modificarInmueble() {
		String id;
		boolean repeat=false;
		System.out.println("Inmuebles disponibles:");
		a.mostrarInmueblesShort();
		
		repeat=false;
		System.out.println("\nEscriba el ID del piso a modificar o 'c' para cancelar");
		id=sc.nextLine();
		id=id.toLowerCase();
		id=id.trim();
		do {
		switch(id) {
		case"c":
			System.out.println("No se ha modificado ningún piso;");
			break;
		default:
			if(a.obtenerPos(Integer.parseInt(id))==-1) {
				System.out.println("No se ha encontrado ningún inmueble con ese ID. Porfavor, inténtelo de nuevo");
				repeat=true;
			}else modificarPos(a.obtenerPos(Integer.parseInt(id)));
			break;
		}
		}while(repeat);
	}
	
	private static void modificarPos(int pos) {
		boolean casa=false;
		boolean estudio=false;
		boolean venta=true;
		boolean alquiler=true;
		boolean repeat=true;
		String op="";
		
		repeat=true;
		if(a.getInmuebles().get(pos).isAlquiler())alquiler=true;
		else alquiler=false;
		if(a.getInmuebles().get(pos).isVenta())venta=true;
		else venta=false;
		if(a.getInmuebles().get(pos) instanceof Piso)casa=false;
		else if(a.getInmuebles().get(pos) instanceof Casa)casa=true;
		if(a.getInmuebles().get(pos) instanceof Piso) {
			Piso aux=new Piso((Piso)a.getInmuebles().get(pos));
			if(aux.isEstudio())estudio=true;
			else estudio=false;
		}else estudio=false;
		System.out.println("Elija una opción:"
				+ "\nm-Abrir manual de este modo de uso:"
				+ "\nc-No realizar más cambios en el inmueble "+a.getInmuebles().get(pos).getId()+":"
				+ "\n1-Modificar la dirección:"
				+ "\n2-Modificar la superficie:"
				+ "\n3-Modificar la disponibilidad de garaje:");
		if(!estudio)System.out.println("4-Modificar el número de habitaciones:");
		if(!venta||alquiler)System.out.println("5-Modificar la disponiblilidad de venta:");
		if(!alquiler||venta)System.out.println("6-Modificar la disponibilidad de alquiler:");
		if(venta)System.out.println("7-Modificar precio de venta:");
		if(alquiler)System.out.println("8-Modificar precio de alquiler:");
		if(casa) System.out.println("9-Modificar disponibilidad de piscina:"
				+ "\n10-Modificar disponibilidad de jardín:");
		else System.out.println("11-Modificar la letra del Piso:"
				+ "\n12-Modificar el tipo de piso(estudio/no estudio):"
				+ "\n13-Modificar el número de planta:"
				+ "\n14-Cambiar su tipo(casa/piso)(deberá rellenar los campos adicionales también:"
				+ "\nEscriba su opción a continuación:)");
		op=sc.nextLine();
		op=op.toLowerCase();
		op=op.trim();
		switch(op) {
		case"m":man();
			break;
		case"c":repeat=false;
			break;
		case"1":modificarDireccion(pos);
			break;
		case"2":modificarSuperficie(pos);
			break;
		case"3":modificarGarage(pos);
			break;
		case"4":
			if(estudio)error();
			else modificarNumHab(pos);
			break;
		case"5":
			if(venta&&!alquiler)error();
			else modificarVenta(pos);
			break;
		case"6":
			if(alquiler&&!venta)error();
			else modificarAlquiler(pos);
			break;
		case"7":
			if(!venta)error();
			else modificarPVenta(pos);
			break;
		case"8":
			if(!alquiler)error();
			else modificarPAlquiler(pos);
			break;
		case"9":
			if(!casa)error();
			else modificarPiscina(pos);
			break;
		case"10":
			if(!casa)error();
			else modificarJardin(pos); 
			break;
		case"11":
			if(casa)error();
			else modificarLetra(pos);
			break;
		case"12":
			if(casa)error();
			else modificarEstudio(pos);
			break;
		case"13":
			if(casa)error();
			else modificarPlanta(pos);
			break;
		default:error();
			break;
		}
		
		
	}
	
	private static void error() {
		System.err.println("Opción incorrecta. En caso de duda consulte el manual(m)."
				+ "\nPorfavor, inténtelo de nuevo:");
	}
	private static void man() {
		System.out.println("------------------------------------------------------------------------------------------------------------------");
		System.out.println("El modo de modificación oculta las modificaciones que no son posibles y no permite acceder a ellas."
				+ "\nOcultará y no dejará cambiar los siguientes casos:"
				+ "\n	Si es un estudio no será posible cambiar el número de habitaciones."
				+ "\n	Si está a la venta y no en alquiler no será posible cancelar la venta."
				+ "\n	Si está en alquiler y no en venta no será posible cancelar el alquiler."
				+ "\n	Si no está a la venta no será posible cambiar el precio de venta."
				+ "\n	Si no está en alquiler no será posible cambiar el precio de alquiler."
				+ "\n	Si no es una casa no será posible cambiar ni el jardín ni la piscina."
				+ "\n	Si no es un piso no será posible cambiar ni la letra ni el tipo(estudio) ni el número de planta."
				+ "\n\nUn error puede deberse a intentar acceder a una opción sin las condiciones necesarias o simplemente una opción desconocida.");
		System.out.println("------------------------------------------------------------------------------------------------------------------");
	}
	
}
