package main;

import java.util.Random;

public abstract class Inmueble {
	private static int contId=1;
	
	private static Random r=new Random();
	private int id;
	private Direccion direccion;
	private int numHab;
	private double superficie;
	private boolean alquiler;
	private boolean venta;
	private double precioAlquiler;
	private double precioVenta;
	private boolean garaje;
	
	/*this method return the ID´s count and increments it*/
	private static int asignarID() {
		int toret=contId;
		contId++;
		return toret;
	}

	private void checkNumHab(int num) {
		if(num<1) {
			System.err.println("Número de habitaciones erróneo;\nInicializado número por defecto;");
			this.numHab=1;
		}else this.numHab=num;
	}
	
	private void checkSuperficie(double s) {
		if(s<=0) {
			System.err.println("Superficie inválida;\nInicializada superficie por defecto;");
			this.superficie=20;
		}else this.superficie=s;
	}
	
	private double checkPrecio(double p) {
		if(p<0) {
			System.err.println("Precio inválido;\nInicializado precio por defecto;");
			 return 20;
		}else return p;
	}
	
	private void checkVenta(boolean v) {
		if(v)this.venta=v;
		else {
			if(this.alquiler)this.venta=v;
			else {
				this.venta=true;
				System.err.println("Se ha inicializado la venta a true ya que el valor de alquiler es false;");
			}
		}
	}
	
	private void checkAlquiler(boolean a) {
		if(a)this.alquiler=a;
		else {
			if(this.venta)this.alquiler=a;
			else {
				this.alquiler=true;
				System.err.println("Se ha inicializado el alquiler a true ya que el valor de venta es false;");
			}
		}
	}
	
	
	public Inmueble(Direccion direccion, int numHab, double superficie, boolean alquiler, boolean venta, double precioA, double precioV, boolean garaje) {
		this.id = asignarID();
		this.direccion = direccion;
		checkNumHab(numHab);
		checkSuperficie(superficie);
		this.precioAlquiler = checkPrecio(precioA);
		this.precioVenta=checkPrecio(precioV);
		this.venta=venta;
		this.alquiler=alquiler;
		checkVenta(venta);
		checkAlquiler(alquiler);
		this.garaje=garaje;
	}
	
	public Inmueble() {
		this.id=asignarID();
		this.direccion=new Direccion();
		this.numHab=r.nextInt(50)+1;
		this.superficie=r.nextInt(2000)+1;
		this.precioAlquiler=r.nextInt(3000)+1;
		this.precioVenta=r.nextInt(5000000)+1;
		this.garaje=r.nextBoolean();
		int aux=r.nextInt(3);
		this.venta=true;
		this.alquiler=true;
		if(aux==0)this.venta=false;
		else if(aux==1)this.alquiler=false;
	}

	public int getId() {
		return id;
	}
	
	protected void setId(int id) {
		this.id=id;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public int getNumHab() {
		return numHab;
	}

	public void setNumHab(int numHab) {
		checkNumHab(numHab);
	}

	public double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(double superficie) {
		checkSuperficie(superficie);
	}

	public boolean isAlquiler() {
		return alquiler;
	}

	public void setAlquiler(boolean alquiler) {
		checkAlquiler(alquiler);
	}

	public boolean isVenta() {
		return venta;
	}

	public void setVenta(boolean venta) {
		checkVenta(venta);
	}

	public double getPrecioAlquiler() {
		return precioAlquiler;
	}

	public void setPrecioAlquiler(double precioAlquiler) {
		this.precioAlquiler=(precioAlquiler);
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta=checkPrecio(precioVenta);
	}
	
	public boolean isGaraje() {
		return garaje;
	}

	public void setGaraje(boolean garaje) {
		this.garaje = garaje;
	}

	@Override
	public String toString() {
		String toret="ID:"+getId()+"\n"+getDireccion().toString()+"\nNúmero de Habitaciones:"+getNumHab()+"\n";
		if(isVenta())toret+="Precio de venta:"+getPrecioVenta()+"  ";
		if(isAlquiler())toret+="Precio de alquiler:"+getPrecioAlquiler();
		toret+="\nSuperficie:"+getSuperficie();
		toret+="\nGaraje:";
		if(isGaraje())toret+="Si";
		else toret+="No";	
		return toret;
	}
	
	public String toStringShort() {
		return"ID:"+getId();
	}
}
