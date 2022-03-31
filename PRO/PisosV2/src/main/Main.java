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
			case"4":conFiltros();
				break;
			case"5":eliminarInmueble();
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
				if(!comprobarLetraPiso(toread))System.err.println("Letra incorrecta. Por favor inténtelo de nuevo(a-z):");
				}while(!comprobarLetraPiso(toread));
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
	
	private static boolean comprobarLetraPiso(String letra) {
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
		
		do {
		repeat=false;
		System.out.println("\nEscriba el ID del piso a modificar o 'c' para cancelar");
		id=sc.nextLine();
		id=id.toLowerCase();
		id=id.trim();
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
		do {
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
				+ "\n13-Modificar el número de planta:");
		System.out.println("\n14-Modificar el tipo(casa/piso)");
		
		op=sc.nextLine();
		op=op.toLowerCase();
		op=op.trim();
		switch(op) {
		case"m":
		case"man":
			man();
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
		case"14":
			modificarTipo(pos);
			break;
		default:error();
			break;
		}
	}while(repeat);
		
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
	
	private static void modificarSuperficie(int pos) {
		String sup;
		double s=1;
		boolean ok=true;
		
		do {
		System.out.println("Escribe la nueva superficie o 'c' para cancelar:");
		sup=sc.nextLine();
		sup=sup.trim();
		sup=sup.toLowerCase();
		if(sup.equals("c"))ok=true;
		else {
			s=Double.parseDouble(sup);
			if(s<=0) {
				System.err.println("Superficie inválida. Por favor inténtelo de nuevo;");
				ok=false;
			}else ok=true;
		}
		}while(!ok);
		
		if(!sup.equals("c"))a.getInmuebles().get(pos).setSuperficie(s);
	}
	
	private static void modificarGarage(int pos) {
		boolean g=a.getInmuebles().get(pos).isGaraje();
		String op;
		boolean ok=false;
		boolean newg=false;
		
		do {
		ok=false;
		if(g) System.out.println("Escriba 'n' para cambiar la disponibilidad de garage a no o escriba 's' para dejarla a si");
		else System.out.println("Escriba 'n' para dejar la disponibilidad de garage a no o escriba 's'para cambiarla a si ");
		op=sc.nextLine();
		op=op.toLowerCase();
		op=op.trim();
		
		if(op.equals("s")||op.equals("si")) {
			newg=true;
			ok=true;
		}else if(op.equals("n")||op.equals("no")) {
			newg=false;
			ok=true;
		}else System.err.println("opción desconocida. Porfavor, inténtelo de nuevo;");
		}while(!ok);
		
		a.getInmuebles().get(pos).setGaraje(newg);
	}
	
	private static void modificarDireccion(int pos) {
		Direccion dir=new Direccion();
		String aux;
		int aux2;
		
		do {
		System.out.println("Escribe el tipo de dirección (calle,avenida,plaza,camino,carretera,paseo:)");
		aux=sc.nextLine();
		aux=aux.toLowerCase();
		aux=aux.trim();
		if(!comprobarTipoDireccion(aux))System.err.println("Tipo incorrecto. Porfavor, inténtelo de nuevo;");
		}while(!comprobarTipoDireccion(aux));
		dir.setTipo(aux);
		
		System.out.println("Escribe el nombre de la dirección:");
		dir.setNombre(sc.nextLine());
		
		do {
		System.out.println("Escriba el número de la Dirección:");
		aux2=Integer.parseInt(sc.nextLine());
		if(!numMay0(aux2))System.err.println("El número debe ser mayor o igual a 1. Porfavor, inténtelo de nuevo");
		}while(!numMay0(aux2));
		dir.setNum(aux2);
		
		do {
		System.out.println("Escriba el Código postal (entre 8033 y 99999):");
		aux2=Integer.parseInt(sc.nextLine());
		if(aux2<8033 || aux2>99999)System.err.println("Código postal incorrecto. Porfavor, inténtelo de nuevo;");
		}while(aux2<8033 || aux2>99999);
		dir.setCp(aux2);
		
		a.getInmuebles().get(pos).setDireccion(dir);
		System.out.println("Dirección cambiada con éxito");
	}
	
	private static void modificarNumHab(int pos) {
		String aux;
		int num=1;
		boolean ok;
		do {
		ok=false;
		System.out.println("Escribe el nuevo número de habitaciones o 'c' para cancelar:");
		aux=sc.nextLine();
		aux=aux.toLowerCase();
		aux=aux.trim();
		if(aux.equals("c"))ok=true;
		else {
			num=Integer.parseInt(aux);
			if(num<1)System.err.println("El número de habitaciones ha de ser 1 o más. Porfavor, inténtelo de nuevo;");
			else ok=true;
		}
		}while(!ok);
		
		if(!aux.equals("c"))a.getInmuebles().get(pos).setNumHab(num);
	}

	private static void modificarVenta(int pos) {
		boolean v=a.getInmuebles().get(pos).isVenta();
		String aux;
		boolean newv=true;
		boolean ok;
		double aux2;
		
		do {
		ok=true;
		if(v)System.out.println("Escriba 's' para dejar la disponibilidad de venta a si o 'n' para cambiarla a no:");
		else System.out.println("Escriba 's' para cambiar la disponibilidad de venta a si o 'n' para dejarla en no:");
		aux=sc.nextLine();
		aux=aux.toLowerCase();
		aux=aux.trim();
		switch(aux) {
		case"s":
		case"si":
			newv=true;
			break;
		case"no":
		case"n":
			newv=false;
			break;
		default:
			ok=false;
			System.err.println("Opción incorrecta. Porfavor, inténtelo de nuevo;");
		}
		}while(!ok);
		a.getInmuebles().get(pos).setVenta(newv);
		if(newv && !v) {
			do {
			System.out.println("Escribe el precio de venta:");
			aux2=Double.parseDouble(sc.nextLine());
			if(aux2<0)System.err.println("Precio incorrecto. Porfavor, inténtelo de nuevo:");
			}while(aux2<0);
			a.getInmuebles().get(pos).setPrecioVenta(aux2);
		}else if(!newv && v) {
			a.getInmuebles().get(pos).setPrecioVenta(0);
		}
	}
	
	private static void modificarAlquiler(int pos) {
		boolean al=a.getInmuebles().get(pos).isAlquiler();
		String aux;
		boolean newa=true;
		boolean ok;
		double aux2;
		
		do {
		ok=true;
		if(al)System.out.println("Escriba 's' para dejar la disponibilidad de alquiler a si o 'n' para cambiarla a no:");
		else System.out.println("Escriba 's' para cambiar la disponibilidad de alquiler a si o 'n' para dejarla en no:");
		aux=sc.nextLine();
		aux=aux.toLowerCase();
		aux=aux.trim();
		switch(aux) {
		case"s":
		case"si":
			newa=true;
			break;
		case"no":
		case"n":
			newa=false;
			break;
		default:
			ok=false;
			System.err.println("Opción incorrecta. Porfavor, inténtelo de nuevo;");
		}
		}while(!ok);
		a.getInmuebles().get(pos).setAlquiler(newa);
		if(newa && !al) {
			do {
			System.out.println("Escribe el precio de alquiler:");
			aux2=Double.parseDouble(sc.nextLine());
			if(aux2<0)System.err.println("Precio incorrecto. Porfavor, inténtelo de nuevo:");
			}while(aux2<0);
			a.getInmuebles().get(pos).setPrecioAlquiler(aux2);
		}else if(!newa && al) {
			a.getInmuebles().get(pos).setPrecioAlquiler(0);
		}
	}
	
	private static void modificarPAlquiler(int pos) {
		String aux;
		boolean ok;
		
		do {
		ok=true;
		System.out.println("Escribe el nuevo precio de alquiler o 'c' para cancelar:");
		aux=sc.nextLine();
		aux=aux.trim();
		aux=aux.toLowerCase();
		switch(aux) {
		case"c":
			break;
		default:
			if(Integer.parseInt(aux)<0) {
				ok=false;
				System.err.println("El precio no puede ser negativo. Porfavor, inténtelo de nuevo;");
			}else a.getInmuebles().get(pos).setPrecioAlquiler(Integer.parseInt(aux));
			break;
		}
		}while(!ok);
	}
	
	private static void modificarPVenta(int pos) {
		String aux;
		boolean ok;
		
		do {
		ok=true;
		System.out.println("Escribe el nuevo precio de venta o 'c' para cancelar:");
		aux=sc.nextLine();
		aux=aux.trim();
		aux=aux.toLowerCase();
		switch(aux) {
		case"c":
			break;
		default:
			if(Integer.parseInt(aux)<0) {
				ok=false;
				System.err.println("El precio no puede ser negativo. Porfavor, inténtelo de nuevo;");
			}else a.getInmuebles().get(pos).setPrecioVenta(Integer.parseInt(aux));
			break;
		}
		}while(!ok);
	}
	
	private static void modificarPiscina(int pos) {
		Casa c= new Casa((Casa) a.getInmuebles().get(pos));
		boolean pis=c.isPiscina();
		String op;
		boolean ok=true;
		
		do {
		ok=true;
		if(pis)System.out.println("Escribe 's' para dejar la disponibilidad de piscina o 'n' para quitarla:");
		else System.out.println("Escribe 's' para cambiar la disponibilidad de piscina a si o 'n' para dejarla en no:");
		op=sc.nextLine();
		op=op.toLowerCase();
		op=op.trim();
		
		switch(op) {
		case"s":
		case"si":
			c.setPiscina(true);
			break;
		case"no":
		case"n":
			c.setPiscina(false);
			break;
		default:
			System.err.println("Opción desconocida. Porfavor, inténtelo de nuevo.");
			ok=false;
			break;
		}
		}while(!ok);
		a.cambiarInmueble(pos, c);
	}
	
	private static void modificarJardin(int pos) {
		Casa c= new Casa((Casa) a.getInmuebles().get(pos));
		boolean jar=c.isJardin();
		String op;
		boolean ok=true;
		
		do {
		ok=true;
		if(jar)System.out.println("Escribe 's' para dejar la disponibilidad de jardín o 'n' para quitarla:");
		else System.out.println("Escribe 's' para cambiar la disponibilidad de jardín a si o 'n' para dejarla en no:");
		op=sc.nextLine();
		op=op.toLowerCase();
		op=op.trim();
		
		switch(op) {
		case"s":
		case"si":
			c.setJardin(true);
			break;
		case"no":
		case"n":
			c.setJardin(false);
			break;
		default:
			System.err.println("Opción desconocida. Porfavor, inténtelo de nuevo.");
			ok=false;
			break;
		}
		}while(!ok);
		a.cambiarInmueble(pos, c);
	}
	
	private static void modificarLetra(int pos) {
		Piso p=new Piso((Piso)a.getInmuebles().get(pos));
		String aux;
		
		do {
		System.out.println("La letra actual es "+p.getLetra()+". Escriba la nueva letra [A-Z](solo se tomará la primera):");
		aux=sc.nextLine();
		aux=aux.trim();
		aux=aux.toUpperCase();
		if(!comprobarLetraPiso(aux))System.err.println("Letra incorrecta. Porfavor, inténtelo de nuevo");
		}while(!comprobarLetraPiso(aux));
		
		p.setLetra(aux.charAt(0));
		a.cambiarInmueble(pos, p);
	}

	private static void modificarEstudio(int pos) {
		Piso p=new Piso((Piso)a.getInmuebles().get(pos));
		boolean es=p.isEstudio();
		boolean newes=false;
		String op;
		boolean ok;
		
		do {
		ok=true;
		if(es)System.out.println("Escriba 's' para dejar el piso como un estudio o 'n' para definirlo como piso estándar:");
		else  System.out.println("Escriba 's' para cambiar el piso a estudio o 'n' para dejarlo en piso estándar:");
		op=sc.nextLine();
		op=op.trim();
		op=op.toLowerCase();
		switch(op) {
		case"s":
		case"si":
		case"yes":
			newes=true;
			break;
		case"n":
		case"no":
			newes=false;
			break;
		default:
			System.err.println("Opción incorrecta. Porfavor, inténtelo de nuevo;");
			ok=false;
			break;
		}
		}while(!ok);
		
		if(!es && newes) {
			p.setNumHab(1);
			p.setEstudio(newes);
			a.cambiarInmueble(pos, p);
		}else if(es && !newes) {
			p.setEstudio(newes);
			a.cambiarInmueble(pos, p);
		}
	}

	private static void modificarPlanta(int pos) {
		Piso p=new Piso((Piso)a.getInmuebles().get(pos));
		String op;
		
		System.out.println("La planta actual es "+p.getNumPiso()+". Escriba la nueva planta o 'c' para cancelar:");
		op=sc.nextLine();
		op=op.trim();
		op=op.toLowerCase();
		if(!op.equals("c")) {
			p.setNumPiso(Integer.parseInt(op));
			a.cambiarInmueble(pos, p);
		}
	}

	private static void modificarTipo(int pos) {
		boolean casa=true;
		if(a.getInmuebles().get(pos) instanceof Piso) casa=false;
		else casa=true;
		boolean newcasa=true;
		boolean ok=true;
		String op;
		
		do {
		ok=true;
		if(casa)System.out.println("Escribe 'c' para dejar el inmueble como casa o 'p' para cambiarlo de casa a piso:");
		else System.out.println("Escribe 'c' para cambiar de piso a casa o 'p' para dejar el inmueble como piso:");
		op=sc.nextLine();
		op=op.trim();
		op=op.toLowerCase();
		
		switch(op) {
		case"c":
		case"casa":
			newcasa=true;
			break;
		case"p":
		case"piso":
			newcasa=false;
			break;
		default:
			ok=false;
			System.err.println("Opción desconocida. Porfavor, inténtelo de nuevo.");
			break;
		}
		}while(!ok);
		
		if(casa && !newcasa) {
			String aux;
			Casa c1=new Casa((Casa)a.getInmuebles().get(pos));
			Piso p1=new Piso(c1);
			System.out.println("A continuación rellene los siguientes campos para completar el piso:");
			System.out.println("Escriba la planta:");
			p1.setNumPiso(Integer.parseInt(sc.nextLine()));
			
			do {
			System.out.println("Escriba la letra(se tomará la primera):");
			aux=sc.nextLine();
			aux=aux.toUpperCase();
			aux=aux.trim();
			if(!comprobarLetraPiso(aux))System.err.println("Letra desconocida. Porfavor, inténtelo de nuevo.");
			}while(!comprobarLetraPiso(aux));
			p1.setLetra(aux.charAt(0));
			
			do {
			ok=true;
			System.out.println("¿Es un estudio(s/n)?");
			aux=sc.nextLine();
			aux=aux.toLowerCase();
			aux=aux.trim();
			switch(aux) {
			case"s":
			case"si":
				p1.setNumHab(1);
				p1.setEstudio(true);
				break;
			case"n":
			case"no":
				p1.setEstudio(false);
				break;
			default:
				System.err.println("Opción desconocida. Porfavor, inténtelo de nuevo.");
				ok=false;
				break;
			}
			a.cambiarInmueble(pos, p1);
			System.out.println("Se ha cambiado de casa a piso con éxito.");
			}while(!ok);
		
		}else if(!casa && newcasa) {
			String aux;
			Piso p2=new Piso((Piso)a.getInmuebles().get(pos));
			Casa c2=new Casa(p2);
			System.out.println("A continuación rellene los siguientes campos para completar la casa.");
			
			do {
				ok=true;
				System.out.println("¿Cuenta con piscina(s/n)?");
				aux=sc.nextLine();
				aux=aux.trim();
				aux=aux.toLowerCase();
				switch(aux) {
				case"si":
				case"s":
					c2.setPiscina(true);
					break;
				case"no":
				case"n":
					c2.setPiscina(false);
					break;
				default:
					ok=false;
					System.err.println("Opción desconocida. Porfavor, inténtelo de nuevo;");
				}
			}while(!ok);
			
			do {
				ok=true;
				System.out.println("¿Cuenta con jardín(s/n)?");
				aux=sc.nextLine();
				aux=aux.trim();
				aux=aux.toLowerCase();
				switch(aux) {
				case"si":
				case"s":
					c2.setJardin(true);
					break;
				case"no":
				case"n":
					c2.setJardin(false);
					break;
				default:
					ok=false;
					System.err.println("Opción desconocida. Porfavor, inténtelo de nuevo;");
				}
			}while(!ok);
			
			a.cambiarInmueble(pos, c2);
			System.out.println("Se ha cambiado de piso a casa con éxito;");
		}
	}

	private static void eliminarInmueble() {
		String op;
		boolean ok;
		
		do {
		ok=true;
		System.out.println("Inmuebles disponibles:");
		a.mostrarInmueblesShort();
		System.out.println("Escribe el id del inmueble que desea borrar o 'c' para cancelar:");
		op=sc.nextLine();
		op=op.toLowerCase();
		op=op.trim();
		switch(op) {
		case"c":
			break;
		default:
			int aux=Integer.parseInt(op);
			a.eliminarInmueble(aux);
			if(aux==-1) {
				ok=false;
				System.err.println("Porfavor, inténtelo de nuevo;");
			}else 
			break;
		}
		}while(!ok);
		
	}

	private static boolean soloPisos=false;
	private static boolean soloCasas=false;
	private static boolean soloConAlquiler=false;
	private static boolean soloConVenta=false;
	private static boolean entrePrecios=false;
	private static boolean entreSuperficies=false;
	private static double precioMin=1;
	private static double precioMax=1;
	private static double superficieMax=1;
	private static double superficieMin=1;
	
	private static void conFiltros() {
		boolean repetir=true;
		do {
		repetir=true;
		System.out.print("Filtros aplicados:");
		if(!(soloPisos||soloCasas||soloConAlquiler||soloConVenta||entrePrecios||entreSuperficies)) {
			System.out.print("Ningún filtro aplicado.");
		}else {
			if(soloPisos)System.out.print("Solo pisos/");
			if(soloCasas)System.out.print("Solo casas/");
			if(soloConAlquiler)System.out.print("Solo disponibles en alquiler/");
			if(soloConVenta)System.out.print("Solo disponibles a la venta/");
			if(entrePrecios)System.out.print("Solo entre "+precioMin+" y "+precioMax+" euros/");
			if(entreSuperficies)System.out.print("Solo entre "+superficieMin+" y "+superficieMax+" metros cuadrados/");
		}
		String op;
		System.out.println("\nElija una opción:"
				+ "\n1-Solo mostrar casas"
				+ "/2-Solo mostrar pisos;"
				+ "\n3-Mostrar casas y pisos(sin filtro de tipo);"
				+ "\n4-Solo disponibles en alquiler"
				+ "/5-Solo disponibles a la venta;"
				+ "\n6-Mostrar a la venta o alquiler(sin filtro de adquisición);"
				+ "\n7-En un rango de precios;"
				+ "\n8-Sin rango de precios(sin filtro de precios);"
				+ "\n9-En un rango de superficie;"
				+ "\n10-Sin rango de superficie(sin filtro de superficie);"
				+ "\n11-Quitar todos los filtros;"
				+ "\n12-MOSTRAR CON LOS FILTROS;"
				+ "\n0-SALIR DE LA BÚSQUEDA CON FILTROS;");
		op=sc.nextLine();
		op=op.trim();
		switch(op) {
		case"1":
			soloCasas=true;
			soloPisos=false;
			break;
		case"2":
			soloPisos=true;
			soloCasas=false;
			break;
		case"3":
			soloPisos=false;
			soloCasas=false;
			break;
		case"4":
			soloConAlquiler=true;
			break;
		case"5":
			soloConVenta=true;
			break;
		case"6":
			soloConAlquiler=false;
			soloConVenta=false;
			break;
		case"7":rangoPrecios();
			break;
		case"8":
			entrePrecios=false;
			break;
		case"9":rangoSuperficies();
			break;
		case"10":
			entreSuperficies=false;
			break;
		case"11":
			soloCasas=false;
			soloPisos=false;
			soloConAlquiler=false;
			soloConVenta=false;
			entrePrecios=false;
			entreSuperficies=false;
			break;
		case"0":repetir=false;
			break;
		case"12":mostrarConFiltros();
			break;
		default:System.err.println("Opción incorrecta. Porfavor, inténtelo de nuevo;");
			break;
		}
		}while(repetir);
		
	}
	
	private static void rangoPrecios() {
		double p1=0;
		double p2=0;
		System.out.println("Escriba los precios minimo y máximo en cualquier orden;");
		
		do {
		System.out.println("Escriba el primer precio:");
		p1=Double.parseDouble(sc.nextLine());
		if(p1<0)System.err.println("Precio incorrecto. Porfavor, inténtelo de nuevo;");
		}while(p1<0);
		
		do {
		System.out.println("Escriba el segundo precio:");
		p2=Double.parseDouble(sc.nextLine());
		if(p2<0)System.err.println("Precio incorrecto. Porfavor, inténtelo de nuevo;");
		}while(p2<0);
		
		if(p1>p2) {
			precioMin=p2;
			precioMax=p1;
		}else {
			precioMin=p1;
			precioMax=p2;
		}
		
		entrePrecios=true;
	}
	
	private static void rangoSuperficies() {
		double s1=0;
		double s2=0;
		System.out.println("Escriba las superficies minima y máxima en cualquier orden;");
		
		do {
		System.out.println("Escriba la primera superficie:");
		s1=Double.parseDouble(sc.nextLine());
		if(s1<=0)System.err.println("Superficie incorrecta. Porfavor, inténtelo de nuevo;");
		}while(s1<=0);
		
		do {
		System.out.println("Escriba la segunda superficie:");
		s2=Integer.parseInt(sc.nextLine());
		if(s2<=0)System.err.println("Superficie incorrecta. Porfavor, inténtelo de nuevo;");
		}while(s2<=0);
		
		if(s1>s2) {
			superficieMin=s2;
			superficieMax=s1;
		}else {
			superficieMin=s1;
			superficieMax=s2;
		}
		
		entreSuperficies=true;
	}
	
	private static void mostrarConFiltros() {
		Agencia nueva=new Agencia(a);
		if(soloPisos)nueva=nueva.soloPisos();
		if(soloCasas)nueva=nueva.soloCasas();
		if(soloConAlquiler)nueva=nueva.soloAlquiler();
		if(soloConVenta)nueva=nueva.soloVenta();
		if(entrePrecios)nueva=nueva.inmueblesEntrePrecios(precioMin, precioMax);
		if(entreSuperficies)nueva=nueva.inmueblesEntreSuperficies(superficieMin, superficieMax);
		nueva.mostrarInmuebles();
	}
	
}
	


	
	


