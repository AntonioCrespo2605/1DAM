package modelo;

public abstract class Articulo {
	private int id,stock;
	double precio;
	
	public Articulo(int id, int stock, double precio) {
		this.id=id;
		this.stock=stock;
		this.precio=precio;
	}
	
	public Articulo() {
		super();
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getStock() {
		return this.stock;
	}
	
	public double getPrecio() {
		return this.precio;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public void setStock(int stock) {
		this.stock=stock;
	}
	
	public void setPrecio(double precio) {
		this.precio=precio;
	}
	
	@Override
	public String toString() {
		return "Artículo:"+getId()+", Stock:"+getStock()+", Precio:"+getPrecio();
	}
}


