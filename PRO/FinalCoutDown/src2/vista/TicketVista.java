package vista;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import bbdd.BDConnection;
import controlador.TicketController;
import modelo.Articulo;
import modelo.Ticket;

public class TicketVista {
	private static TicketController controlador;
	private static Scanner sc=new Scanner(System.in);
	
	public static void gestionTickets() throws Exception {
		boolean stay=true;
		String op;
		controlador=new TicketController();
		
		do {
		stay=true;
		System.out.println("Elija una opción:"
				+ "\n1-Mostrar todos los articulos a la venta;"
				+ "\n2-Mostrar todos los articulos ordenados por precio;"
				+ "\n3-Mostrar Tickets;"
				+ "\n4-Modificar un Ticket(devolución parcial);"
				+ "\n5-Eliminar un ticket(devolución total);"
				+ "\n6-Crear un Ticket(nueva venta)"
				+ "\n7-Salir;");
		op=sc.nextLine();
		op=op.trim();
		switch(op) {
		case"1":mostrarArticulos();
			break;
		case"2":mostrarArticulosPorPrecio();
			break;
		case"3":mostrarTickets();
			break;
		case"4":modificarTicket();
			break;
		case"5":eliminarTicket();
			break;
		case"6":crearTicket();
			break;
		case"7":
			BDConnection.endConnection();
			stay=false;
			break;
		default:
			System.err.println("Opción desconocida; Porfavor, inténtelo de nuevo;");
			break;
		}
		}while(stay);
	}
	
	private static void mostrarArticulosPorPrecio() {
		System.out.println(controlador.obtenerArticulosPorPrecio());
	}

	private static void mostrarArticulos() {
		System.out.println(controlador.obtenerArticulos());
	}

	private static void mostrarTickets() {
		System.out.println(controlador.mostrarTickets());
	}

	private static void eliminarTicket() throws SQLException {
		int id_aux;
		boolean stay;
		String op;
		
		do {
			stay=false;
			System.out.println("Elija el id a eliminar o escriba \"c\" para cancelar:");
			System.out.println("--------------------------------------------------------------------------------------------------------------");
			System.out.println(controlador.mostrarTicketsCorto());
			System.out.println("\n--------------------------------------------------------------------------------------------------------------");
			op=sc.nextLine();
			op=op.trim();
			op=op.toLowerCase();
			if(!(op.equals("c")||op.equals("cancel")||op.equals("cancelar"))) {
				try {
					id_aux=Integer.parseInt(op);
					if(controlador.existeIdTicket(id_aux)) {
						eliminarConConfirmacion(id_aux);
					}else {
						stay=true;
						System.err.println("No existe este ID. Porfavor revise la lista de IDs y vuelva a intentarlo;");
					}
				}catch(NumberFormatException nfe) {
					stay=true;
					System.err.println("La cadena introducida no se corresponde con ningún número entero;");
				}
			}
		}while(stay);
	}

	private static void eliminarConConfirmacion(int id) throws SQLException {
		String op;
		boolean stay;
		
		do {
			stay=false;
			System.out.println("Está seguro de eliminar el siguiente ticket(s/n):");
			System.out.println("------------------------------------------");
			System.out.println(controlador.mostrarTicket(id));
			System.out.println("------------------------------------------");
			op=sc.nextLine();
			op=op.trim();
			op=op.toLowerCase();
			switch(op) {
			case"s":
			case"si":
			case"yes":
				if(controlador.eliminarTicket(id))System.out.println("Se ha eliminado el ticket con éxito;");
				else System.err.println("Se ha producido algún fallo al eliminar el ticket");
				break;
			case"n":
			case"no":
				System.out.println("Cancelado con éxito;");
				break;
			default:
				System.err.println("Opción desconocida. Porfavor, inténtelo de nuevo;");
				stay=true;
				break;
			}
		}while(stay);
	}

	private static void modificarTicket() throws SQLException {
		int id_aux;
		boolean stay;
		String op;
		
		do {
			stay=false;
			System.out.println("Elija el id a modificar o escriba \"c\" para cancelar:");
			System.out.println("--------------------------------------------------------------------------------------------------------------");
			System.out.println(controlador.mostrarTicketsCorto());
			System.out.println("\n--------------------------------------------------------------------------------------------------------------");
			op=sc.nextLine();
			op=op.trim();
			op=op.toLowerCase();
			if(!(op.equals("c")||op.equals("cancel")||op.equals("cancelar"))) {
				try {
					id_aux=Integer.parseInt(op);
					if(controlador.existeIdTicket(id_aux)) {
						modificarTicket(id_aux);
					}else {
						stay=true;
						System.err.println("No existe este ID. Porfavor revise la lista de IDs y vuelva a intentarlo;");
					}
				}catch(NumberFormatException nfe) {
					stay=true;
					System.err.println("La cadena introducida no se corresponde con ningún número entero;");
				}
			}
		}while(stay);
	}

	private static void modificarTicket(int id_ticket) throws SQLException {
		Ticket t=controlador.getTicket(id_ticket);
		
		int id;
		boolean stay;
		String op;
		do {
			stay=false;
			System.out.println("Info del ticket:");
			System.out.println("-----------------------------------");
			System.out.println(t.toString());
			System.out.println("-----------------------------------");
			System.out.println("Escriba el id del artículo sobre el que desea hacer un cambio :");
			op=sc.nextLine();
			op=op.trim();
			op=op.toLowerCase();
			if(!(op.equals("c")||op.equals("cancel")||op.equals("cancelar"))) {
				try {
					id=Integer.parseInt(op);
					if(controlador.existeIdArticulo(id)) {
						if(!articuloDisp(t.getArticulos(), id)) {
							cambiarUds(t,id);
						}else {
							System.err.println("El ticket no contiene el id especificado; Porfavor, inténtelo de nuevo");
							stay=true;
						}
					}else {
					stay=true;
					System.err.println("No existe este ID. Porfavor revise la lista de IDs y vuelva a intentarlo;");
					}
			}catch(NumberFormatException nfe) {
				stay=true;					
				System.err.println("La cadena introducida no se corresponde con ningún número entero;");
				}
			}
		}while(stay);
	}

	@SuppressWarnings("unchecked")
	private static void cambiarUds(Ticket t, int id) throws SQLException {
		int pos=0;
		for(int i=0;i<t.getArticulos().size();i++) {
			if(t.getArticulos().get(i).getId()==id)pos=i;
		}
		
		int nueva;
		boolean stay;
		do {
		stay=false;
		try {
		int uds_act=t.getCantidades().get(pos);
		System.out.println("Escriba la nueva cantidad. Recuerde que ha de ser menor a la actual ("+uds_act+") Para cancelar escriba la misma cantidad;:");
		nueva=Integer.parseInt(sc.nextLine());
		if(nueva<0 || nueva>uds_act) {
			System.err.println("Asegúrese de que el nuevo número es menor o igual a "+uds_act+" y mayor o igual a 0;");
			stay=true;
		}else if(nueva == 0) {
			Ticket aux=new Ticket(t);
			ArrayList<Articulo>artaux=(ArrayList<Articulo>) aux.getArticulos().clone();
			artaux.remove(pos);
			aux.setArticulos(artaux);
			ArrayList<Integer>cantis=(ArrayList<Integer>) aux.getCantidades().clone();
			cantis.remove(pos);
			aux.setCantidades(cantis);
			controlador.eliminarTicket(t.getId());
			controlador.anhadirTicket(aux);
		}else {
			Ticket aux=new Ticket(t);
			ArrayList<Integer>cantis=(ArrayList<Integer>) aux.getCantidades().clone();
			cantis.set(pos, nueva);
			aux.setCantidades(cantis);
			controlador.eliminarTicket(t.getId());
			controlador.anhadirTicket(aux);
		}
		}catch(NumberFormatException nfe) {
			stay=true;
			System.err.println("La cadena introducida no se corresponde con ningún número;");
		}
		}while(stay);
	}

	private static void crearTicket() throws SQLException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String fech_aux=dtf.format(LocalDateTime.now()); 
		ArrayList<Articulo>articulos =new ArrayList<Articulo>(); 
		ArrayList<Integer>cantidades=new ArrayList<Integer>();
		String op;
		int id_aux = 0;
		boolean stay;
		boolean ejecutarResto=true;
		do {
			stay=false;
			System.out.println("Artículos disponibles:");
			System.out.println("-----------------------------------------------------");
			System.out.println(controlador.obtenerArticulosCorto());
			System.out.println("-----------------------------------------------------");
			System.out.println("Elije un artículo o c para cancelar:");
			op=sc.nextLine();
			op=op.trim();
			op=op.toLowerCase();
			if(!(op.equals("c")||op.equals("cancel")||op.equals("cancelar"))) {
				try {
					id_aux=Integer.parseInt(op);
					if(controlador.existeIdArticulo(id_aux)) {
						articulos.add(controlador.getArticuloId(id_aux));
					}else {
						stay=true;
						System.err.println("No existe este ID. Porfavor revise la lista de IDs y vuelva a intentarlo;");
					}
				}catch(NumberFormatException nfe) {
					stay=true;
					System.err.println("La cadena introducida no se corresponde con ningún número entero;");
				}
			}else ejecutarResto=false;
		}while(stay);
		
		
		if(ejecutarResto) {
		int cant;
		do {
			stay=false;
			System.out.println("Escriba la cantidad que desea introducir del artículo "+id_aux+":");
			try {
				cant=Integer.parseInt(sc.nextLine());
				if(cant<=0) {
					stay=true;
					System.err.println("El número debe ser 1 o más. Porfavor, inténtelo de nuevo;");
				}else cantidades.add(cant);
			}catch(NumberFormatException nfe) {
				System.err.println("La cadena introducida no se corresponde con un número entero. Porfavor, inténtelo de nuevo;");
				stay=true;
			}
		}while(stay);

		int aux;
		do {
			aux=articulos.size();
			stay=false;
			System.out.println("¿Desea anhadir otro artículo?(s/n):");
			op=sc.nextLine();
			op=op.toLowerCase();
			op=op.trim();
			switch(op) {
			case"s":
				articulos=otroArticulo(articulos);
				if(articulos.size()!=aux)cantidades=otraCantidad(cantidades,articulos.get(articulos.size()-1).getId());
				stay=true;
				break;
			case"n":stay=false;
				break;
			default:System.err.println("Opción desconocida. Porfavor, inténtelo de nuevo;");
				stay=true;
				break;
			}
		}while(stay);
		
		
		Ticket t=new Ticket(controlador.nuevoIdTicket(), fech_aux, controlador.calcularTotal(articulos, cantidades), articulos, cantidades);
		controlador.anhadirTicket(t);
		}
	}

	private static ArrayList<Integer> otraCantidad(ArrayList<Integer>cantidades, int id) {
		boolean stay;
		int cant;
		do {
			stay=false;
			System.out.println("Escriba la cantidad que desea introducir del artículo "+id+":");
			try {
				cant=Integer.parseInt(sc.nextLine());
				if(cant<=0) {
					stay=true;
					System.err.println("El número debe ser 1 o más. Porfavor, inténtelo de nuevo;");
				}else cantidades.add(cant);
			}catch(NumberFormatException nfe) {
				System.err.println("La cadena introducida no se corresponde con un número entero. Porfavor, inténtelo de nuevo;");
				stay=true;
			}
		}while(stay);
		return cantidades;
	}

	private static ArrayList<Articulo> otroArticulo(ArrayList<Articulo> articulos) {
		boolean stay;
		String op;
		int id_aux;
		do {
			stay=false;
			System.out.println("Artículos disponibles:");
			System.out.println("-----------------------------------------------------");
			System.out.println(controlador.obtenerArticulosCorto());
			System.out.println("-----------------------------------------------------");
			System.out.println("Elije un artículo o c para cancelar:");
			op=sc.nextLine();
			op=op.trim();
			op=op.toLowerCase();
			if(!(op.equals("c")||op.equals("cancel")||op.equals("cancelar"))) {
				try {
					id_aux=Integer.parseInt(op);
					if(controlador.existeIdArticulo(id_aux)) {
						if(articuloDisp(articulos, id_aux)) {
							articulos.add(controlador.getArticuloId(id_aux));
						}else {
							stay=true;
							System.err.println("Articulo repetido");
						}
					}else {
						stay=true;
						System.err.println("No existe este ID. Porfavor revise la lista de IDs y vuelva a intentarlo;");
					}
				}catch(NumberFormatException nfe) {
					stay=true;
					System.err.println("La cadena introducida no se corresponde con ningún número entero;");
				}
			}
		}while(stay);
		return articulos;
	}
	
	private static boolean articuloDisp(ArrayList<Articulo>articulos, int id) {
		for(Articulo a:articulos) {
			if(a.getId()==id)return false;
		}
		return true;
	}
	
}
