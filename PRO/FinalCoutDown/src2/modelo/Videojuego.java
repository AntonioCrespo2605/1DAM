package modelo;

public class Videojuego extends Articulo {
	private String titulo, pegi, plataforma;
	private int duracion;
	private static final String[] PEGIS = {"OK","3","7","12","16","18"};
	private static final String[] PLATAFORMAS = {"PS4","PS5","XBOX SERIE X","XBOX SERIE S","NINTENDO SWITCH","PC","WII U","OTRA"};
	
	private static boolean checkPegi(String pegi) {
		for(int i=0;i<PEGIS.length;i++) {
			if(PEGIS[i].equals(pegi))return true;
		}
		return false;
	}
	
	private static boolean checkPlataforma(String plataforma) {
		for(int i=0;i<PLATAFORMAS.length;i++) {
			if(PLATAFORMAS[i].equals(plataforma))return true;
		}
		return false;
	}
	
	public Videojuego(int id, int stock, double precio, String titulo, String pegi, String plataforma, int duracion) {
		super(id, stock, precio);
		pegi=pegi.toUpperCase();
		plataforma=plataforma.toUpperCase();
		this.titulo=titulo;
		if(checkPegi(pegi))this.pegi=pegi;
		else this.pegi=PEGIS[0];
		if(checkPlataforma(plataforma))this.plataforma=plataforma;
		else this.plataforma=PLATAFORMAS[0];
		if(duracion>=1&&duracion<=500)this.duracion=duracion;
		else this.duracion=1;
	}
	/*
	public Videojuego(Articulo a, String titulo, String pegi, String plataforma, int duracion) {
		super(a.getId(), a.getStock(), a.getPrecio());
		pegi=pegi.toUpperCase();
		plataforma=plataforma.toUpperCase();
		this.titulo=titulo;
		if(checkPegi(pegi))this.pegi=pegi;
		else this.pegi=PEGIS[0];
		if(checkPlataforma(plataforma))this.plataforma=plataforma;
		else this.plataforma=PLATAFORMAS[0];
		if(duracion>=1)this.duracion=duracion;
		else this.duracion=1;
	}*/
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public String getPegi() {
		return this.pegi;
	}
	
	public String getPlataforma() {
		return this.plataforma;
	}
	
	public int getDuracion() {
		return this.duracion;
	}
	
	public void setDuracion(int duracion) {
		this.duracion=duracion;
	}
	
	public void setTitulo(String titulo) {
		this.titulo=titulo;
	}
	
	public void setPegi(String pegi) {
		pegi=pegi.toUpperCase();
		if(checkPegi(pegi))this.pegi=pegi;
	}
	
	public void setPlataforma(String plataforma) {
		pegi=pegi.toUpperCase();
		if(checkPlataforma(plataforma))this.plataforma=plataforma;
	}
	
	public String toString() {
		return "Videojuego "+super.getId()+", Título:"+getTitulo()+"\nPrecio: "
				+super.getPrecio()+" euros\nStock: "+super.getStock()+"\nPEGI "+getPegi()+"\nPlataforma: "+getPlataforma();
	}
}

