package modelo;

public class Consola extends Articulo {
	private String marca, modelo, color, mini_descripcion;
	private boolean edicion_especial;
	private static final String MARCAS[]= {"SONY", "MICROSOFT", "NINTENDO", "OTRO"};
	private static final String MODELOS[]= {"PS4","PS5","XBOX SERIE X", "XBOX SERIE S","SWITCH","WII U", "OTRO"};
	private static final String COLORES[]= {"NEGRO","BLANCO", "ROJO","VERDE","AZUL","AMARILLO", "OTRO"};
	
	public static boolean checkMarca(String marca) {
		for(int i=0;i<MARCAS.length;i++) {
			if(MARCAS[i].equals(marca))return true;
		}
		return false;
	}
	
	public static boolean checkModelo(String modelo) {
		for(int i=0;i<MODELOS.length;i++) {
			if(MODELOS[i].equals(modelo))return true;
		}
		return false;
	}
	
	public static boolean checkColor(String color) {
		for(int i=0;i<COLORES.length;i++) {
			if(COLORES[i].equals(color))return true;
		}
		return false;
	}
	
	public Consola(int id, int stock, double precio, String marca, String modelo, String color, String mini_descripcion, boolean edicion_especial) {
		super(id,stock,precio);
		marca=marca.toUpperCase();
		modelo=modelo.toUpperCase();
		color=color.toUpperCase(); 
		if(checkMarca(marca))this.marca=marca;
		else this.marca=MARCAS[0];
		if(checkModelo(modelo))this.modelo=modelo;
		else this.modelo=MODELOS[0];
		if(checkColor(color))this.color=color;
		else this.color=color;
		this.mini_descripcion=mini_descripcion;
		this.edicion_especial=edicion_especial;
	}
	
	/*
	public Consola(Articulo a, String marca, String modelo, String color, String mini_descripcion, boolean edicion_especial) {
		super(a.getId(),a.getStock(),a.getPrecio());
		marca=marca.toUpperCase();
		modelo=modelo.toUpperCase();
		color=color.toUpperCase(); 
		mini_descripcion=mini_descripcion.toUpperCase();
		if(checkMarca(marca))this.marca=marca;
		else this.marca=MARCAS[0];
		if(checkModelo(modelo))this.modelo=modelo;
		else this.modelo=MODELOS[0];
		if(checkColor(color))this.color=color;
		else this.color=color;
		this.mini_descripcion=mini_descripcion;
		this.edicion_especial=edicion_especial;
	}*/
	
	public String getMarca() {
		return this.marca;
	}
	
	public String getModelo() {
		return this.modelo;
	}
	
	public String getColor() {
		return this.color;
	}

	public String getMini_descripcion() {
		return mini_descripcion;
	}
	
	public boolean isEdicion_especial() {
		return edicion_especial;
	}
	
	public void setMini_descripcion(String mini_decripcion) {
		this.mini_descripcion = mini_decripcion;
	}

	public void setEdicion_especial(boolean edicion_especial) {
		this.edicion_especial = edicion_especial;
	}

	public void setMarca(String marca) {
		if(checkMarca(marca))this.marca = marca;
	}

	public void setModelo(String modelo) {
		if(checkModelo(modelo))this.modelo = modelo;
	}

	public void setColor(String color) {
		if(checkColor(color))this.color = color;
	}
	
	@Override
	public String toString() {
		String aux="";
		if(isEdicion_especial())aux="Si";
		else aux="No";
		String desc="";
		if(getMini_descripcion()!=null)desc="\nDescripción:"+getMini_descripcion();
		
		return "Consola "+super.getId()+", "+getMarca()+" "+getModelo()+ ", Color: "+getColor()+"\nPrecio: "+super.getPrecio()+" euros\nStock: "+super.getStock()+
				desc+"\nEdición especial: "+aux;
	}
}

