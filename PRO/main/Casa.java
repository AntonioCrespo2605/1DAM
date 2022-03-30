package main;
import java.util.Random;

public class Casa extends Inmueble implements Impuestable{
	private static Random r=new Random();
	
	private boolean jardin;
	private boolean piscina;
	
	public Casa() {
		super();
		this.jardin=r.nextBoolean();
		this.piscina=r.nextBoolean();
	}
	
	public Casa(Direccion direccion, int numHab, double superficie, boolean alquiler, boolean venta, double precioA, double precioV, boolean garaje, boolean jardin, boolean piscina) {
		super(direccion, numHab, superficie, alquiler, venta, precioA, precioV, garaje);
		this.jardin=jardin;
		this.piscina=piscina;
	}
	
	public Casa(Casa c) {
		this.setId(c.getId());
		this.setDireccion(c.getDireccion());
		this.setNumHab(c.getNumHab());
		this.setSuperficie(c.getSuperficie());
		this.setAlquiler(c.isAlquiler());
		this.setVenta(c.isVenta());
		this.setPrecioAlquiler(c.getPrecioAlquiler());
		this.setPrecioVenta(c.getPrecioVenta());
		this.setGaraje(c.isGaraje());
		this.jardin=c.isJardin();
		this.piscina=c.isPiscina();
	}
	
	public Casa(Piso p) {
		this.setId(p.getId());
		this.setDireccion(p.getDireccion());
		this.setNumHab(p.getNumHab());
		this.setSuperficie(p.getSuperficie());
		this.setAlquiler(p.isAlquiler());
		this.setVenta(p.isVenta());
		this.setPrecioAlquiler(p.getPrecioAlquiler());
		this.setPrecioVenta(p.getPrecioVenta());
		this.setGaraje(p.isGaraje());
		this.jardin=false;
		this.piscina=false;
	}

	public boolean isJardin() {
		return jardin;
	}

	public void setJardin(boolean jardin) {
		this.jardin = jardin;
	}

	public boolean isPiscina() {
		return piscina;
	}

	public void setPiscina(boolean piscina) {
		this.piscina = piscina;
	}

	@Override
	public String toString() {
		String toret="Casa\n"+super.toString();
		if(this.jardin)toret+=("\nJardín incluído");
		if(this.piscina)toret+=("\nPiscina incluída");
		if(this.isVenta())toret+=("\nPrecio Venta con impuestos:"+precioVentaConImpuesto()+";");
		if(this.isAlquiler())toret+=("\nPrecio Alquiler con impuestos:"+precioAlquilerConImpuesto()+";");
		return toret;
	}
	
	public double precioAlquilerConImpuesto() {
		double preciofinal=getPrecioAlquiler()+(getPrecioAlquiler()*ITP/100)+(getPrecioAlquiler()*IVA/100);
		return preciofinal;
	}
	
	public double precioVentaConImpuesto() {
		double preciofinal=getPrecioVenta()+(getPrecioVenta()*ITP/100)+(getPrecioVenta()*IVA/100);
		return preciofinal;
	}
	
}
