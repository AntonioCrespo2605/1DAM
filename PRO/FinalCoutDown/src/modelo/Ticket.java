package modelo;

import java.util.ArrayList;

public class Ticket {
	private int id;
	private String fecha;
	private double total;
	private ArrayList<Articulo>articulos;
	private ArrayList<Integer>cantidades;
	
	public Ticket(int id, String fecha, double total, ArrayList<Articulo>articulos, ArrayList<Integer>cantidad) {
		this.id=id;
		this.fecha=fecha;
		this.total=total;
		this.articulos=articulos;
		this.cantidades=cantidad;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getFecha() {
		return this.fecha;
	}
	
	public double getTotal() {
		return this.total;
	}
	
	public ArrayList<Articulo> getArticulos(){
		return this.articulos;
	}
	
	public ArrayList<Integer> getCantidades(){
		return this.cantidades;
	}
	
	public String toString() {
		Videojuego v;
		Consola c;
		String toret="Ticket:"+getId()+"\nFecha:"+getFecha()+"\nArtículos:";
		for(int i=0;i<articulos.size();i++) {
			if(articulos.get(i) instanceof Videojuego) {
				v=(Videojuego)articulos.get(i);
				toret+="\nVideojuego "+v.getId()+" "+v.getTitulo()+"\n\tuds:"+cantidades.get(i).intValue()+"\n\tprecio 1ud:"+v.getPrecio()+"€";	
			}else {
				c=(Consola)articulos.get(i);
				toret+="\nConsola "+c.getId()+" "+c.getMarca()+" "+c.getModelo()+"\n\tuds:"+cantidades.get(i).intValue()+"\n\tprecio 1ud:"+c.getPrecio()+"€";
			}
		
		}
		toret+="\nTotal:"+getTotal()+"€";
		return toret;
	}
}

