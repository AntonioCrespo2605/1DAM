package vista;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.Scanner;

import bbdd.BDConnection;
import controlador.ArticuloController;
import controlador.T5ArticulosVendidos;
import controlador.TicketController;
import modelo.Videojuego;
import modelo.Consola;
public class ArticuloVista {
	private static ArticuloController controlador;
	private static TicketController controlador2;
	private static Scanner sc=new Scanner(System.in);
	
	public static void gestionArticulos() throws Exception {
		boolean stay=true;
		String op;
		controlador=new ArticuloController();
		
		do {
		stay=true;
		System.out.println("Elija una opci�n:"
				+ "\n1-Mostrar todos los articulos a la venta;"
				+ "\n2-Mostrar s�lo los videojuegos a la venta;"
				+ "\n3-Mostrar s�lo las consolas a la venta;"
				+ "\n4-A�adir nuevo articulo;"
				+ "\n5-Modificar un articulo;"
				+ "\n6-Eliminar un articulo;"
				+ "\n7-Mostrar tickes ordenados por fecha;"
				+ "\n8-Ver top 5 art�culos m�s vendidos;"
				+ "\n9-Salir;");
		op=sc.nextLine();
		op=op.trim();
		switch(op) {
		case"1":mostrarArticulos();
			break;
		case"2":mostrarVideojuegos();
			break;
		case"3":mostrarConsolas();
			break;
		case"4":crearArticulo();
			break;
		case"5":modificarArticulo();
			break;
		case"6":eliminarArticulo();
			break;
		case"7":controlador2=new TicketController(controlador);
				System.out.println(controlador2.mostrarTickets());
			break;
		case"8":System.out.println(T5ArticulosVendidos.mostarInfoT5Vendidos(controlador));
			break;
		case"9":
			BDConnection.endConnection();
			stay=false;
			break;
		default:
			System.err.println("Opci�n desconocida; Porfavor, int�ntelo de nuevo;");
			break;
		}
		}while(stay);
	}

	private static void mostrarConsolas() {
		if(controlador.obtenerConsolas().equals("0"))System.out.println("No hay consolas disponibles;");
		else System.out.println(controlador.obtenerConsolas());
	}

	private static void mostrarVideojuegos() {
		if(controlador.obtenerVideojuegos().equals("0"))System.out.println("no hay videojuegos disponibles;");
		else System.out.println(controlador.obtenerVideojuegos());
	}

	private static void mostrarArticulos() {
		if(controlador.obtenerArticulos().equals("0"))System.out.println("no hay art�culos disponibles;");
		else System.out.println(controlador.obtenerArticulos());
	}

	private static void crearArticulo() throws SQLException {
		String op;
		boolean stay;
		
		do {
			stay=true;
			System.out.println("Elija una opci�n:"
					+ "\n1-A�adir un nuevo videojuego;"
					+ "\n2-A�adir una nueva consola"
					+ "\n3-Cancelar:");
			op=sc.nextLine();
			op=op.trim();
			switch(op) {
			case"1":
				crearVideojuego();
				stay=false;
				break;
			case"2":
				crearConsola();
				stay=false;
				break;
			case"3":
				stay=false;
				break;
			default:System.err.println("Opci�n desconocida; Porfavor, int�ntelo de nuevo:");
				break;
			}
		}while(stay);
	}

	private static void crearConsola() throws SQLException {
		String marca, modelo, color, mini_descripcion;
		boolean edicion_especial = false;
		int stock=0;
		double precio=0;
		mini_descripcion=null;
		modelo="";
		marca="";
		color="";
		String op;
		boolean stay;
		
		System.out.println("Muy bien. A continuaci�n rellene los siguientes campos:");
		//----------------------------------------------------
		do {
			stay=false;
			System.out.println("Elija una marca:"
					+ "\n1-SONY;"
					+ "\n2-MICROSOFT;"
					+ "\n3-NINTENDO;"
					+ "\n4-OTRO;");
			op=sc.nextLine();
			switch(op){
			case"1":marca="SONY";
				break;
			case"2":marca="MICROSOFT";
				break;
			case"3":marca="NINTENDO";
				break;
			case"4":marca="OTRO";
				break;
			default:System.err.println("Opci�n desconocida.Porfavor, int�ntelo de nuevo;");
				stay=true;
				break;
			}
		}while(stay);
		//----------------------------------------------------
		do {
			stay=false;
			System.out.println("Elija un modelo:"
					+ "\n1-PS4;"
					+ "\n2-PS5;"
					+ "\n3-XBOX SERIE X;"
					+ "\n4-XBOX SERIE S;"
					+ "\n5-SWITCH;"
					+ "\n6-WII U;"
					+ "\n7-OTRO;");
			op=sc.nextLine();
			switch(op){
			case"1":modelo="PS4";
				break;
			case"2":modelo="PS5";
				break;
			case"3":modelo="XBOX SERIE X";
				break;
			case"4":modelo="XBOX SERIE S";
				break;
			case"5":modelo="SWITCH";
				break;
			case"6":modelo="WII U";
				break;
			case"7":modelo="OTRO";
				break;
			default:System.err.println("Opci�n desconocida. Porfavor, int�ntelo de nuevo");
				stay=true;
				break;
			}
		}while(stay);
		//----------------------------------------------------
		do {
			stay=false;
			System.out.println("Elija un color:"
					+ "\n1-NEGRO;"
					+ "\n2-BLANCO;"
					+ "\n3-ROJO;"
					+ "\n4-VERDE;"
					+ "\n5-AZUL;"
					+ "\n6-AMARILLO;"
					+ "\n7-OTRO;");
			op=sc.nextLine();
			switch(op){
			case"1":color="NEGRO";
				break;
			case"2":color="BLANCO";
				break;
			case"3":color="ROJO";
				break;
			case"4":color="VERDE";
				break;
			case"5":color="AZUL";
				break;
			case"6":color="AMARILLO";
				break;
			case"7":color="OTRO";
				break;
			default:System.err.println("Opci�n desconocida. Porfavor, int�ntelo de nuevo");
				stay=true;
				break;
			}
		}while(stay);
		//----------------------------------------------------
		do{
		stay=false;
		System.out.println("�Desea a�adirle una descripci�n a la consola? (s/n):");
		op=sc.nextLine();
		op=op.trim();
		op=op.toLowerCase();
		switch(op) {
		case"s":
			
			do {
			System.out.println("Escriba una descripci�n breve (max 30 caracteres):");
			mini_descripcion=sc.nextLine();
			if(mini_descripcion.length()>30)System.err.println("Solo se aceptan 30 caracteres como m�ximo. Porfavor, int�ntelo de nuevo;");
			}while(mini_descripcion.length()>30);
			
			break;
		case"n":
			break;
		default:System.err.println("Opci�n desconocida. Porfavor, int�ntelo de nuevo;");
			stay=true;
			break;
		}
		}while(stay);
		
		do {
		stay=false;
		System.out.println("�Se trata de una edici�n especial?(s/n):");
		op=sc.nextLine();
		op=op.toLowerCase();
		op=op.trim();
		switch(op) {
		case"s":edicion_especial=true;
			break;
		case"n":edicion_especial=false;
			break;
		default:System.err.println("Opci�n desconocida. Porfavor, int�ntelo de nuevo;");
			stay=true;
			break;
		}
		}while(stay);
		//----------------------------------------------------
		do {
			stay=false;
			System.out.println("�Cuantas unidades tiene en tienda?:");
			try {
				stock=Integer.parseInt(sc.nextLine());
				if(stock<0) {
					stay=true;
					System.err.println("El n�mero debe ser 0 o m�s. Porfavor, int�ntelo de nuevo;");
				}
			}catch(NumberFormatException nfe) {
				System.err.println("La cadena introducida no se corresponde con un n�mero entero. Porfavor, int�ntelo de nuevo;");
				stay=true;
			};
		}while(stay);
		
		//----------------------------------------------------
		double aux;
		
		do {
		stay=false;
		System.out.println("Esciba el precio de la consola:");
		try {
			aux=Double.parseDouble(sc.nextLine());
			BigDecimal bd = new BigDecimal(aux).setScale(2, RoundingMode.HALF_UP);
			precio = bd.doubleValue();
			if(precio>9999 || precio<0) {
				System.err.println("El precio debe ser menor a 9999 y mayor o igual a 0. Porfavor, int�ntelo de nuevo;");
				stay=true;
			}
		}catch(Exception e) {
			stay=true;
			System.err.println("Error en la introducci�n del n�mero decimal. Porfavor, int�ntelo de nuevo;");
		}
		}while(stay);
		//----------------------------------------------------
		Consola c=new Consola(controlador.nuevoId(),stock,precio,marca,modelo,color,mini_descripcion,edicion_especial);
		
		do {
		stay=false;
		System.out.println("�Est� seguro de a�adir la siguiente consola?:");
		System.out.println("------------------------------------------");
		System.out.println(c.toString());
		System.out.println("------------------------------------------");
		System.out.println("(s/n):");
		op=sc.nextLine();
		op=op.trim();
		op=op.toLowerCase();
		switch(op) {
		case"s":
			if(controlador.addArticulo(c))System.out.println("Articulo a�adido con �xito");
			else System.err.println("Ha ocurrido un error al a�adir un articulo;");
		case"n":
			break;
		default:
			stay=true;
			System.err.println("Opci�n desconocida. Porfavor, int�ntelo de nuevo;");
			break;
		}
		}while(stay);
	}

	private static void crearVideojuego() throws SQLException {
		String titulo, pegi = "OK",plataforma = "PS4", op;
		boolean stay;
		int duracion = 1, stock = 0;
		double precio = 0;
		System.out.println("Muy bien. A continuaci�n rellene los siguientes campos:");
		//----------------------------------------------------
		do {
		System.out.println("Escribe el t�tulo del juego:");
		titulo=sc.nextLine();
		if(titulo.length()<2 || titulo.length()>50)System.err.println("La longitud del t�tulo ha de ser entre 2 y 50 caracteres.\nPorfavor, int�ntelo de nuevo;");
		}while(titulo.length()<2 || titulo.length()>50);
		
		do {
			stay=false;
			System.out.println("Elija el PEGI del videojuego:"
					+ "\n1-PEGI OK;"
					+ "\n2-PEGI 3;"
					+ "\n3-PEGI 7;"
					+ "\n4-PEGI 12;"
					+ "\n5-PEGI 16;"
					+ "\n6-PEGI 18;");
			op=sc.nextLine();
			switch(op){
			case"1":pegi="OK";
				break;
			case"2":pegi="3";
				break;
			case"3":pegi="7";
				break;
			case"4":pegi="12";
				break;
			case"5":pegi="16";
				break;
			case"6":pegi="18";
				break;
			default:System.err.println("Opci�n desconocida. Porfavor, int�ntelo de nuevo");
				stay=true;
				break;
			}
		}while(stay);
		//----------------------------------------------------
		do {
			stay=false;
			System.out.println("Elija una plataforma:"
					+ "\n1-PS4;"
					+ "\n2-PS5;"
					+ "\n3-XBOX SERIE X;"
					+ "\n4-XBOX SERIE S;"
					+ "\n5-NINTENDO SWITCH;"
					+ "\n6-WII U;"
					+ "\n7-PC"
					+ "\n8-OTRA;");
			op=sc.nextLine();
			switch(op){
			case"1":plataforma="PS4";
				break;
			case"2":plataforma="PS5";
				break;
			case"3":plataforma="XBOX SERIE X";
				break;
			case"4":plataforma="XBOX SERIE S";
				break;
			case"5":plataforma="NINTENDO SWITCH";
				break;
			case"6":plataforma="WII U";
				break;
			case"7":plataforma="PC";
				break;
			case"8":plataforma="OTRA";
				break;
			default:System.err.println("Opci�n desconocida. Porfavor, int�ntelo de nuevo");
				stay=true;
				break;
			}
		}while(stay);
		//----------------------------------------------------
		do {
			stay=false;
			System.out.println("Duraci�n aproximada en horas(n�mero entero):");
			try {
				duracion=Integer.parseInt(sc.nextLine());
				if(duracion<=0 || duracion>500) {
					stay=true;
					System.err.println("La duraci�n no puede ser nula, negativa ni mayor a 500. Porfavor, int�ntelo de nuevo;");
				}
			}catch(NumberFormatException nfe) {
				System.err.println("La cadena introducida no se corresponde con un n�mero entero. Porfavor, int�ntelo de nuevo;");
				stay=true;
			};
		}while(stay);
		//----------------------------------------------------
		do {
			stay=false;
			System.out.println("�Cuantas unidades tiene en tienda?:");
			try {
				stock=Integer.parseInt(sc.nextLine());
				if(stock<0) {
					stay=true;
					System.err.println("El n�mero debe ser 0 o m�s. Porfavor, int�ntelo de nuevo;");
				}
			}catch(NumberFormatException nfe) {
				System.err.println("La cadena introducida no se corresponde con un n�mero entero. Porfavor, int�ntelo de nuevo;");
				stay=true;
			};
		}while(stay);
		//----------------------------------------------------
		double aux;
		
		do {
		stay=false;
		System.out.println("Esciba el precio del videojuego:");
		try {
			aux=Double.parseDouble(sc.nextLine());
			BigDecimal bd = new BigDecimal(aux).setScale(2, RoundingMode.HALF_UP);
			precio = bd.doubleValue();
			if(precio>9999 ||precio<0) {
				System.err.println("El precio debe ser menor a 9999 y mayor o igual a 0. Porfavor, int�ntelo de nuevo;");
				stay=true;
			}
		}catch(Exception e) {
			stay=true;
			System.err.println("Error en la introducci�n del n�mero decimal. Porfavor, int�ntelo de nuevo;");
		}
		}while(stay);
		//----------------------------------------------------
		Videojuego v = new Videojuego(controlador.nuevoId(),stock,precio,titulo,pegi,plataforma,duracion);
		do {
			stay=false;
			System.out.println("�Est� seguro de a�adir el siguiente videojuego?:");
			System.out.println("------------------------------------------");
			System.out.println(v.toString());
			System.out.println("------------------------------------------");
			System.out.println("(s/n):");
			op=sc.nextLine();
			op=op.trim();
			op=op.toLowerCase();
			switch(op) {
			case"s":
				if(controlador.addArticulo(v))System.out.println("Articulo a�adido con �xito");
				else System.err.println("Ha ocurrido un error al a�adir un art�culo;");
			case"n":
				break;
			default:
				stay=true;
				System.err.println("Opci�n desconocida. Porfavor, int�ntelo de nuevo;");
				break;
			}
			}while(stay);
	}
	
	private static void eliminarArticulo() throws SQLException {
		int id_aux;
		boolean stay;
		String op;
		
		do {
			stay=false;
			System.out.println("Elija el id a eliminar o escriba \"c\" para cancelar:");
			System.out.println("--------------------------------------------------------------------------------------------------------------");
			System.out.println(controlador.obtenerCorto());
			System.out.println("\n--------------------------------------------------------------------------------------------------------------");
			op=sc.nextLine();
			op=op.trim();
			op=op.toLowerCase();
			if(!(op.equals("c")||op.equals("cancel")||op.equals("cancelar"))) {
				try {
					id_aux=Integer.parseInt(op);
					if(controlador.existeId(id_aux)) {
						eliminarConConfirmacion(id_aux);
					}else {
						stay=true;
						System.err.println("No existe este ID. Porfavor revise la lista de IDs y vuelva a intentarlo;");
					}
				}catch(NumberFormatException nfe) {
					stay=true;
					System.err.println("La cadena introducida no se corresponde con ning�n n�mero entero;");
				}
			}
		}while(stay);
	}
	
	private static void eliminarConConfirmacion(int id) throws SQLException {
		String op;
		boolean stay;
		
		do {
			stay=false;
			System.out.println("Est� seguro de eliminar el siguiente art�culo(s/n):");
			System.out.println("------------------------------------------");
			controlador.mostarArticulo(id);
			System.out.println("------------------------------------------");
			op=sc.nextLine();
			op=op.trim();
			op=op.toLowerCase();
			switch(op) {
			case"s":
			case"si":
			case"yes":
				if(controlador.borrarArticulo(id))System.out.println("Se ha eliminado el art�culo con �xito;");
				else System.err.println("Se ha producido alg�n fallo al eliminar el articulo");
				break;
			case"n":
			case"no":
				System.out.println("Cancelado con �xito;");
				break;
			default:
				System.err.println("Opci�n desconocida. Porfavor, int�ntelo de nuevo;");
				stay=true;
				break;
			}
		}while(stay);
		
	}

	private static void modificarArticulo() throws SQLException {
		boolean stay;
		String op;
		int id_aux;
		do {
			stay=false;
			System.out.println("Elija el id a modificar o escriba \"c\" para cancelar:");
			System.out.println("------------------------------------------");
			System.out.println(controlador.obtenerCorto());
			System.out.println("\n------------------------------------------");
			op=sc.nextLine();
			op=op.trim();
			op=op.toLowerCase();
			if(!(op.equals("c")||op.equals("cancel")||op.equals("cancelar"))) {
				try {
					id_aux=Integer.parseInt(op);
					if(controlador.existeId(id_aux)) {
						if(controlador.esVideojuego(id_aux))modificarVideojuego(id_aux);
						else modificarConsola(id_aux);
					}else {
						stay=true;
						System.err.println("No existe este ID. Porfavor revise la lista de IDs y vuelva a intentarlo;");
					}
				}catch(NumberFormatException nfe) {
					stay=true;
					System.err.println("La cadena introducida no se corresponde con ning�n n�mero entero;");
				}
			}
		}while(stay);
	}

	public static void modificarConsola(int id) throws SQLException{
		boolean es, stay;
		String op;
		do {
			if(controlador.consolaIsEdicionEspecial(id))es=true;
			else es=false;
			stay=true;
			System.out.println("Estado actual de la consola "+id+":");
			System.out.println("------------------------------------------");
			System.out.println(controlador.getArticuloId(id).toString());
			System.out.println("------------------------------------------");
			System.out.println("Elije una opci�n para la consola "+id+":"
					+ "\n1-Modificar stock;"
					+ "\n2-Modificar precio;"
					+ "\n3-Modificar marca;"
					+ "\n4-Modifiar modelo;"
					+ "\n5-Modificar color;"
					+ "\n6-Modificar opciones de descripci�n");
			if(es)System.out.println("7-Cambiar a edici�n NO especial;");
			else System.out.println("7-Cambiar a edici�n especial;");
			
			System.out.println("8-Salir de modificar la consola "+id+";");
			
			op=sc.nextLine();
			op=op.trim();
			switch(op) {
			case"1":modificarStock(id);
				break;
			case"2":modificarPrecio(id);
				break;
			case"3":modificarMarcaConsola(id);
				break;
			case"4":modificarModeloConsola(id);
				break;
			case"5":modificarColorConsola(id);
				break;
			case"6":modificarDescripcionConsola(id);
				break;
			case"7":
				if(es) {
					if(controlador.modificarEdicionEspecial(id, false))System.out.println("Edici�n especial modificada con �xito");
					else System.err.println("Ha ocurrido un error al modificar la edici�n especial;");
				}else {
					if(controlador.modificarEdicionEspecial(id, true))System.out.println("Edici�n especial modificada con �xito");
					else System.err.println("Ha ocurrido un error al modificar la edici�n especial;");
				}
				break;
			case"8":stay=false;
				break;
			default:System.err.println("Opci�n desconocida. Porfavor, int�ntelo de nuevo;");
				break;
			}
			}while(stay);
	}
	
	private static void modificarDescripcionConsola(int id) throws SQLException {
		boolean desc=controlador.consolaHasDescripcion(id);
		boolean stay;
		String op;
		String descripcion="";
			do {
			stay=false;
			System.out.println("Elije una opci�n:");
			if(desc)System.out.println("1-Modificar descripci�n;");
			else System.out.println("1-A�adir descripci�n;");
			if(desc)System.out.println("2-Quitar descripci�n;");
			op=sc.nextLine();
			op=op.trim();
			switch(op) {
			case"1":
				do {
					System.out.println("Escriba una descripci�n breve (max 30 caracteres):");
					descripcion=sc.nextLine();
					if(descripcion.length()>30)System.err.println("Solo se aceptan 30 caracteres como m�ximo. Porfavor, int�ntelo de nuevo;");
				}while(descripcion.length()>30);
				if(controlador.modificarDescripcion(id, descripcion))System.out.println("Descripci�n modificada con �xito");
				else System.err.println("Ha ocurrido alg�n error al modificar la descripci�n;");
				break;
			case"2":
				if(desc) {
				if(controlador.modificarDescripcion(id, null))System.out.println("Descripci�n modificada con �xito;");
				else System.err.println("Ha ocurrido alg�n error al modificar la descripci�n;");
				}else {
					System.err.println("Opci�n incorrecta.Porfavor, int�ntelo de nuevo;");
					stay=true;
				}
				break;
			case"3":
				break;
			default:
				System.err.println("Opci�n incorrecta.Porfavor, int�ntelo de nuevo;");
				stay=true;
				break;
			}
			}while(stay);
	}

	private static void modificarColorConsola(int id) throws SQLException {
		boolean stay;
		String op, color="";
		do {
			stay=false;
			System.out.println("Elija un color:"
					+ "\n1-NEGRO;"
					+ "\n2-BLANCO;"
					+ "\n3-ROJO;"
					+ "\n4-VERDE;"
					+ "\n5-AZUL;"
					+ "\n6-AMARILLO;"
					+ "\n7-OTRO;");
			op=sc.nextLine();
			switch(op){
			case"1":color="NEGRO";
				break;
			case"2":color="BLANCO";
				break;
			case"3":color="ROJO";
				break;
			case"4":color="VERDE";
				break;
			case"5":color="AZUL";
				break;
			case"6":color="AMARILLO";
				break;
			case"7":color="OTRO";
				break;
			default:System.err.println("Opci�n desconocida. Porfavor, int�ntelo de nuevo");
				stay=true;
				break;
			}
		}while(stay);
		if(controlador.modificarColor(id, color))System.out.println("Color modificado con �xito;");
		else System.err.println("Ha ocurrido un error en la modificaci�n del color;");
	}

	private static void modificarModeloConsola(int id) throws SQLException {
		boolean stay;
		String op, modelo = "";
		do {
			stay=false;
			System.out.println("Elija un modelo:"
					+ "\n1-PS4;"
					+ "\n2-PS5;"
					+ "\n3-XBOX SERIE X;"
					+ "\n4-XBOX SERIE S;"
					+ "\n5-SWITCH;"
					+ "\n6-WII U;"
					+ "\n7-OTRO;");
			op=sc.nextLine();
			switch(op){
			case"1":modelo="PS4";
				break;
			case"2":modelo="PS5";
				break;
			case"3":modelo="XBOX SERIE X";
				break;
			case"4":modelo="XBOX SERIE S";
				break;
			case"5":modelo="SWITCH";
				break;
			case"6":modelo="WII U";
				break;
			case"7":modelo="OTRO";
				break;
			default:System.err.println("Opci�n desconocida. Porfavor, int�ntelo de nuevo");
				stay=true;
				break;
			}
		}while(stay);
		if(controlador.modificarModelo(id, modelo)) System.out.println("Modelo modificado con �xito;");
		else System.err.println("Ha ocurrido alg�n error en la modificaci�n del modelo;");
	}

	private static void modificarMarcaConsola(int id) throws SQLException {
		boolean stay;
		String op, marca = "";
		do {
			stay=false;
			System.out.println("Elija una marca:"
					+ "\n1-SONY;"
					+ "\n2-MICROSOFT;"
					+ "\n3-NINTENDO;"
					+ "\n4-OTRO;");
			op=sc.nextLine();
			switch(op){
			case"1":marca="SONY";
				break;
			case"2":marca="MICROSOFT";
				break;
			case"3":marca="NINTENDO";
				break;
			case"4":marca="OTRO";
				break;
			default:System.err.println("Opci�n desconocida.Porfavor, int�ntelo de nuevo;");
				stay=true;
				break;
			}
		}while(stay);
		if(controlador.modificarMarca(id, marca))System.out.println("Marca modificada con �xito;");
		else System.err.println("Ha ocurrido alg�n error en la modificaci�n de la Marca;");
	}

	public static void modificarVideojuego(int id) throws SQLException{
		String op;
		boolean stay;
		do {
		stay=true;
		System.out.println("Estado actual de la consola "+id+":");
		System.out.println("------------------------------------------");
		System.out.println(controlador.getArticuloId(id).toString());
		System.out.println("------------------------------------------");
		System.out.println("Elije una opci�n para el videojuego "+id+":"
				+ "\n1-Modificar stock;"
				+ "\n2-Modificar precio;"
				+ "\n3-Modificar titulo;"
				+ "\n4-Modifiar pegi;"
				+ "\n5-Modificar plataforma;"
				+ "\n6-Modificar duraci�n;"
				+ "\n7-Salir de modificar el videojuego "+id+";");
		op=sc.nextLine();
		op=op.trim();
		switch(op) {
		case"1":modificarStock(id);
			break;
		case"2":modificarPrecio(id);
			break;
		case"3":modificarTituloVideojuego(id);
			break;
		case"4":modificarPegiVideojuego(id);
			break;
		case"5":modificarPlataformaVideojuego(id);
			break;
		case"6":modificarDuracionVideojuego(id);
			break;
		case"7":stay=false;
			break;
		default:System.err.println("Opci�n desconocida. Porfavor, int�ntelo de nuevo;");
			break;
		}
		}while(stay);
	}

	private static void modificarDuracionVideojuego(int id) throws SQLException {
		boolean stay;
		int duracion;
		do {
			stay=false;
			System.out.println("Escribe la nueva duraci�n:");
			try {
				duracion=Integer.parseInt(sc.nextLine());
				if(duracion<=0 || duracion>500) {
					stay=true;
					System.err.println("La duraci�n no puede ser nula, negativa ni mayor a 500. Porfavor, int�ntelo de nuevo;");
				}else {
					if(controlador.modificarDuracion(id, duracion))System.out.println("Duraci�n modificada con �xito;");
					else System.err.println("Ha ocurrido un error en la modificaci�n de la duraci�n;");
				}
			}catch(NumberFormatException nfe) {
				System.err.println("La cadena introducida no se corresponde con un n�mero entero. Porfavor, int�ntelo de nuevo;");
				stay=true;
			};
		}while(stay);
	}

	private static void modificarPlataformaVideojuego(int id) throws SQLException {
		boolean stay;
		String op;
		String plataforma = "";
		do {
			stay=false;
			System.out.println("Elija una nueva plataforma:"
					+ "\n1-PS4;"
					+ "\n2-PS5;"
					+ "\n3-XBOX SERIE X;"
					+ "\n4-XBOX SERIE S;"
					+ "\n5-NINTENDO SWITCH;"
					+ "\n6-WII U;"
					+ "\n7-PC"
					+ "\n8-OTRA;");
			op=sc.nextLine();
			switch(op){
			case"1":plataforma="PS4";
				break;
			case"2":plataforma="PS5";
				break;
			case"3":plataforma="XBOX SERIE X";
				break;
			case"4":plataforma="XBOX SERIE S";
				break;
			case"5":plataforma="NINTENDO SWITCH";
				break;
			case"6":plataforma="WII U";
				break;
			case"7":plataforma="PC";
				break;
			case"8":plataforma="OTRA";
				break;
			default:System.err.println("Opci�n desconocida. Porfavor, int�ntelo de nuevo");
				stay=true;
				break;
			}
		}while(stay);
		if(controlador.modificarPlataforma(id, plataforma))System.out.println("Plataforma modificada con �xito");
		else System.err.println("Ha ocurrido un error en la modificaci�n de plataforma");
	}

	private static void modificarPegiVideojuego(int id) throws SQLException {
		String op;
		boolean stay;
		String pegi="";
		do {
			stay=false;
			System.out.println("Elija el PEGI del videojuego:"
					+ "\n1-PEGI OK;"
					+ "\n2-PEGI 3;"
					+ "\n3-PEGI 7;"
					+ "\n4-PEGI 12;"
					+ "\n5-PEGI 16;"
					+ "\n6-PEGI 18;");
			op=sc.nextLine();
			switch(op){
			case"1":pegi="OK";
				break;
			case"2":pegi="3";
				break;
			case"3":pegi="7";
				break;
			case"4":pegi="12";
				break;
			case"5":pegi="16";
				break;
			case"6":pegi="18";
				break;
			default:System.err.println("Opci�n desconocida. Porfavor, int�ntelo de nuevo");
				stay=true;
				break;
			}
		}while(stay);
		if(controlador.modificarPegi(id, pegi))System.out.println("PEGI modificado con �xito;");
		else System.err.println("Ha ocurrido un error en la modificaci�n del PEGI;");
	}

	private static void modificarTituloVideojuego(int id) throws SQLException {
		String titulo;
		do {
			System.out.println("Escribe el t�tulo del juego:");
			titulo=sc.nextLine();
			if(titulo.length()<2 || titulo.length()>50)System.err.println("La longitud del t�tulo ha de ser entre 2 y 50 caracteres.\nPorfavor, int�ntelo de nuevo;");
		}while(titulo.length()<2 || titulo.length()>50);
		if(controlador.modificarTitulo(id, titulo))System.out.println("T�tulo modificado con �xito");
		else System.err.println("Ha ocurrido alg�n error en la modificaci�n del t�tulo;");
	}

	private static void modificarPrecio(int id) {
		boolean stay;
		double aux;
		double precio;
		do {
			stay=false;
			System.out.println("Esciba el precio:");
			try {
				aux=Double.parseDouble(sc.nextLine());
				BigDecimal bd = new BigDecimal(aux).setScale(2, RoundingMode.HALF_UP);
				precio = bd.doubleValue();
				if(precio>9999 || precio<0) {
					System.err.println("El precio debe ser menor a 9999 y mayor o igual a 0. Porfavor, int�ntelo de nuevo;");
					stay=true;
				}else {
					if(controlador.modificarPrecio(id, precio))System.out.println("Precio modificado con �xito");
					else System.err.println("Ha ocurrido alg�n error en la modificaci�n del precio");
				}
			}catch(Exception e) {
				stay=true;
				System.err.println("Error en la introducci�n del n�mero decimal. Porfavor, int�ntelo de nuevo;");
			}
		}while(stay);
	}

	private static void modificarStock(int id) throws SQLException {
		boolean stay;
		int stock;
		do {
			stay=false;
			System.out.println("Escriba el nuevo Stock:");
			try {
				stock=Integer.parseInt(sc.nextLine());
				if(stock<0) {
					stay=true;
					System.err.println("El n�mero debe ser 0 o m�s. Porfavor, int�ntelo de nuevo;");
				}else {
					if(controlador.modificarStock(id, stock))System.out.println("Stock modificado con �xito");
					else System.err.println("Ha ocurrido alg�n error en la modificaci�n del stock");
				}
			}catch(NumberFormatException nfe) {
				System.err.println("La cadena introducida no se corresponde con un n�mero entero. Porfavor, int�ntelo de nuevo;");
				stay=true;
			}
		}while(stay);
	}

	
	
}
