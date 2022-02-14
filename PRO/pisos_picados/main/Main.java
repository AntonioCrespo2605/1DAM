package main;

import java.util.Scanner;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static Agencia agencia;
	private static boolean rent_filter = false;
	private static boolean sale_filter = false;
	private static boolean shorter = false;
	private static float pricemin = -1;
	private static float pricemax = -1;
	private static int surfacemin = -1;
	private static int surfacemax = -1;

	public static void main(String[] args) {
		String option = "";
		boolean repeat = true;
		System.out.println("Escribe el nombre de la agencia:");
		String name = sc.nextLine();
		agencia = new Agencia(name);

		do {
			repeat = true;
			System.out.println("Bienvenido a "+agencia.getNombre()+";\n");
			System.out.println("Elija una opción:");
			System.out.println("1-Añadir un piso a la agencia;" + "\n2-Modificar una propiedad a partir de su ID;"
					+ "\n3-Borrar una propiedad a partir de su ID;" + "\n4-Aplicar filtros de búsqueda;"
					+ "\n5-Buscar con los filtros seleccionados;" + "\n6-Modificar el nombre de la agencia;"
					+ "\n7-Salir de la agencia;"+"\n8-Mostrar datos de la agencia;");
			option = sc.nextLine();
			option = option.trim();

			switch (option) {

			case "1":
				agencia.addNewAparment();
				break;
			case "2":
				updateAparment();
				break;
			case "3":
				deleteAparment();
				break;
			case "4":
				aplyFilters();
				break;
			case "5":
				showAgencia();
				break;
			case "6":
				updateAgencyName();
				break;
			case "7":
				repeat = false;
				break;
			case "8":showDatos(name);
				break;
			default:
				System.out.println("Opción incorrecta. Porfavor inténtelo de nuevo;");
				break;
			}
		} while (repeat);
		System.out.println("La empresa ha quebrado. Fin del programa :)");
	}

	public static void showDatos(String name) {
		System.out.println("-----------------------------------------");
		System.out.println("Nombre de la agencia: "+name);
		System.out.println("Propiedades disponibles:"+agencia.howManyPisos());
		System.out.println("-----------------------------------------");
	}

	public static void showAgencia() {
		Agencia aux = new Agencia(agencia);
		if (rent_filter)
			aux = aux.OnlyForRent();
		if (sale_filter)
			aux = aux.OnlyForSale();
		if (pricemin >= 0 && pricemax >= 0)
			aux.OnlyBetweenPrice(pricemin, pricemax);
		if (surfacemin >= 1 && surfacemax >= 1)
			aux.OnlyBetweenSurfaces(surfacemin, surfacemax);
		if (shorter)
			aux.showAllAparmentsShort();
		else
			aux.showAllAparments();
	}

	public static void updateAparment() {
		int idaux = -1;
		String op;
		boolean todok = false;

		do {
			System.out.println("Escriba el ID de la propiedad a modificar;\nPara cancelar escriba c;");
			op = sc.nextLine();
			op.trim();

			if (!op.equals("c"))
				idaux = Integer.parseInt(op);
			else
				todok = true;

			if (!op.equals("c")) {
				if (agencia.getPositionOfId(idaux) == -1) {// getPositionofId(id) devuelve -1 si no se encuentra
					System.out.println("No se ha encontrado la propiedad en la lista. Porfavor inténtelo de nuevo;");
					todok = false;
				} else
					todok = true;
			}
		} while (!todok);

		if (!op.equals("c")) {
			do {
				todok = true;
				System.out.println("Elija una opcion :\n1-Modificar todos los atributos de la propiedad;"
						+ "\n2-Salir del modo de modificación;" + "\n3-Modificar la dirección de la propiedad;"
						+ "\n4-Modificar la disponibilidad de la propiedad a la venta;"
						+ "\n5-Modificar el precio de venta de la propiedad;"
						+ "\n6-Modificar la disponibilidad del piso en alquiler;"
						+ "\n7-Modificar el precio de alquiler de la propiedad;" + "\n8-Modificar la superficie de la propiedad;");
				op = sc.nextLine();
				op.trim();
				switch (op) {
				case "1":
					agencia.updateAparmentWithId(idaux);
					break;
				case "2":
					break;
				case "3":
					agencia.setAparmentAdressWithId(idaux);
					break;
				case "4":
					agencia.setOnSaleApatmentWithId(idaux);
					break;
				case "5":
					agencia.setOnSaleAparmentPriceWithId(idaux);
					break;
				case "6":
					agencia.setOnRentApatmentWithId(idaux);
					break;
				case "7":
					agencia.setOnRentAparmentPriceWithId(idaux);
					break;
				case "8":
					agencia.setSurfaceOfAparmentWithId(idaux);
					break;
				default:
					todok = false;
					System.out.println("opción inválida. Porfavor inténtelo de nuevo;");
					break;
				}
			} while (todok == false);

		}
		System.out.println("Fin del modo de modificacion de propiedades;");
	}

	public static void deleteAparment() {
		String option;
		int idaux;
		System.out.println("Introduzca el ID de la propiedad que quiere BORRAR o pulse c para CANCELAR:");
		option = sc.nextLine();

		if (!option.equals("c")) {
			idaux = Integer.parseInt(option);
			if (agencia.getPositionOfId(idaux) == -1)
				System.out.println("Piso no encontrado;");
			else
				agencia.deleteAparmentWithId(idaux);

		}
	}

	public static void aplyFilters() {
		String option = "";
		boolean repeat = true;
		do {
			repeat = true;
			System.out.println("Elija una opción:" + "\n1-Filtros de disponibilidad(solo venta/solo alquiler/todo);"
					+ "\n2-Filtro de rangos de metros cuadrados(mínimo y máximo);"
					+ "\n3-Filtro de rangos de precios(mínimo y máximo);" + "\n4-Filtro de listado (corto/largo);"
					+ "\n5-No aplicar más filtros;" + "\n6-Reestablecer filtro por defecto;");
			option = sc.nextLine();
			option = option.trim();
			switch (option) {
			case "1":
				filtro1();
				break;
			case "2":
				filtro3();
				break;
			case "3":
				filtro2();
				break;
			case "4":
				filtro4();
				break;
			case "5":
				repeat = false;
				break;
			case "6":
				defaultFiltro();
				break;
			default:
				System.out.println("Opción desconocida. Por favor inténtelo de nuevo;");
				break;
			}
		} while (repeat);

	}

	public static void filtro1() {
		String option = "";
		boolean repeat;
		do {
			repeat = false;
			System.out.println("Elija una opcion:" + "\n1-Solo mostrar pisos a la venta:"
					+ "\n2-Solo mostrar pisos en alquiler:" + "\n3-Mostrar todos");
			option = sc.nextLine();
			option.trim();
			switch (option) {
			case "1":
				sale_filter = true;
				rent_filter = false;
				break;
			case "2":
				sale_filter = false;
				rent_filter = true;
				break;
			case "3":
				sale_filter = false;
				rent_filter = false;
				break;
			default:
				System.out.println("Opción desconocida. Por favor inténtelo de nuevo;");
				repeat = true;
				break;
			}
		} while (repeat);

	}

	public static void filtro2() {
		float precioaux;
		do {
			System.out.println("Precio mínimo de la propiedad:");
			precioaux = Float.parseFloat(sc.nextLine());
			if (precioaux < 0)
				System.out.println("No puede ser precio negativo. Porfavor, inténtelo de nuevo;");
		} while (precioaux < 0);
		pricemin = precioaux;
		do {
			System.out.println("Precio máximo de la propiedad:");
			precioaux = Float.parseFloat(sc.nextLine());
			if (precioaux <= pricemin) {
				System.out.println("El precio máximo debe ser mayor al mínimo(" + pricemin + ");");
				precioaux = -1;
			}
			if (precioaux < 0)
				System.out.println("No puede ser precio negativo. Porfavor, inténtelo de nuevo;");
		} while (precioaux < 0);
		pricemax = precioaux;
	}

	public static void filtro3() {
		int superficieaux;
		do {
			System.out.println("Superficie mínima de la propiedad en metros cuadrados:");
			superficieaux = Integer.parseInt(sc.nextLine());
			if (superficieaux <= 0)
				System.out.println("La superficie debe ser 1 o más. Porfavor, inténtelo de nuevo;");
		} while (superficieaux < 0);
		surfacemin = superficieaux;
		do {
			System.out.println("Superficie máxima de la propiedad:");
			superficieaux = Integer.parseInt(sc.nextLine());
			if (superficieaux <= surfacemin) {
				System.out.println("La superficie máxima debe ser mayor a la mínima(" + surfacemin + ");");
				superficieaux = -1;
			}
			if (superficieaux <= 0)
				System.out.println("La superficie debe ser 1 o más. Porfavor, inténtelo de nuevo;");
		} while (superficieaux <= 0);
		surfacemax = superficieaux;
	}

	public static void filtro4() {
		String option = "";
		boolean repeat;
		do {
			repeat = false;
			System.out.println("Elija una opcion:" + "\n1-Mostrar en listado largo:" + "\n2-Mostrar en listado corto:");

			option = sc.nextLine();
			option.trim();
			switch (option) {
			case "1":
				shorter = false;
				break;
			case "2":
				shorter = true;
				break;
			default:
				System.out.println("Opción desconocida. Por favor inténtelo de nuevo;");
				repeat = true;
				break;
			}
		} while (repeat);
	}

	public static void defaultFiltro() {
		sale_filter = false;
		rent_filter = false;
		shorter = false;
		pricemin = -1;
		pricemax = -1;
		surfacemin = -1;
		surfacemax = -1;
	}
	
	public static void updateAgencyName() {
		System.out.println("Escriba el nuevo nombre de la agencia:");
		agencia.setNombre(sc.nextLine());
	}

}