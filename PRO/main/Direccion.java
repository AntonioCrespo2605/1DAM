package main;
import java.util.Random;

public class Direccion {
	private String tipo;
	private String nombre;
	private int num;
	private int cp;
	
	private static final String[] TIPOS= {"calle","avenida","plaza","camino","carretera","paseo"};
	private static Random r=new Random();
	
	/*this method checks that a type of address is valid*/
	private void checkTipo(String tipo) {
		tipo=tipo.toLowerCase();
		int i;
		boolean ok=false;
		for(i=0;i<TIPOS.length;i++) {
			if(tipo.equals(TIPOS[i])) {
				this.tipo=tipo;
				ok=true;
				break;
			}
		}
		
		if(!ok) {
			this.tipo=TIPOS[0];
			System.err.println("Tipo de dirección incorrecta;\nTipos disponibles:calle, avenida, plaza, camino, carretera, paseo."
					+ "\nInicializado tipo de dirección por defecto;");
		}
	}
	
	/*this method checks that a number is greater than 0*/
	private void checkNum(int num) {
		if(num<1) {
			System.err.println("Número de dirección menor a 1. Inicializado número por defecto;");
			this.num=1;
		}else {
			this.num=num;
		}
	}
	
	/*this method checks that a Postal Code is between 8033 and 99999*/
	private void checkCP(int cp) {
		if(cp<8033||cp>99999) {
			System.err.println("Código postal incorrecto (8033-99999);\nInicializado código postal por defecto;");
			this.cp=11111;
		}else this.cp=cp;
	}
	
	/**Constructor using fields**/
	public Direccion(String tipo, String nombre, int num, int cp) {
		checkTipo(tipo);
		this.nombre = nombre;
		checkNum(num);
		checkCP(cp);
	}
	
	/**this Constructor generates a random address**/
	public Direccion() {
		String[]nombresCalleRandom= {"Federico García Lorca", "Antonio Machado", "Miguel Hernández", "El Tony", "Juan Ramón Jimenez",
				"Lope de Vega", "Francisco de Goya", "Diego Velázquez", "Picasso", "Pau Cassals"};
		
		this.tipo=TIPOS[r.nextInt(6)];
		this.nombre=nombresCalleRandom[r.nextInt(10)];
		this.cp=generateRandomCP();
		this.num=r.nextInt(21);
	}
	
	/*it generates a random Postal Code*/
	private static int generateRandomCP() {
		String aux="";
		int i;
		for(i=0;i<5;i++) {
			aux+=r.nextInt(10);
		}
		return Integer.parseInt(aux);
	}

	/**getters and setters**/
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		checkTipo(tipo);
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		checkNum(num);
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		checkCP(cp);
	}

	/**to String**/
	@Override
	public String toString() {
		return "Dirección:"+ tipo + " " + nombre + " Nº" + num + " " + cp;
	}
}
