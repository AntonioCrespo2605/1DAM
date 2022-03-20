package main;

import java.util.Random;

public class Piso extends Inmueble{
	private int numPiso;
	private char letra;
	private boolean estudio;
	private static final char[] LETRAS= {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	Random r=new Random();
	
	private void checkLetra(char l) {
		boolean ok=false;
		String aux=l+"";
		aux=aux.toUpperCase();
		l=aux.charAt(0);
		int i;
		for(i=0;i<LETRAS.length;i++) {
			if(LETRAS[i]==l) {
				this.letra=l;
				ok=true;
				break;
			}
		}
		if(!ok) {
			System.err.println("La letra introducida no está disponible;\nInicializalizada letra por defecto;");
			this.letra=LETRAS[0];
		}
	}

	public Piso(Direccion direccion, int numHab, double superficie, boolean alquiler, boolean venta, double precioA, double precioV, boolean garaje, int numPiso, char letra, boolean estudio) {
		super(direccion, numHab, superficie, alquiler, venta, precioA, precioV, garaje);
		if(estudio)super.setNumHab(1);
		this.numPiso = numPiso;
		checkLetra(letra);
		this.estudio = estudio;
	}
	
	public Piso() {
		super();
		this.letra=LETRAS[r.nextInt(26)];
		this.estudio=r.nextBoolean();
		if(estudio)super.setNumHab(1);
		this.numPiso=r.nextInt(10);
	}
	
	public Piso(Piso p) {
		this.setId(p.getId());
		this.setDireccion(p.getDireccion());
		this.setNumHab(p.getNumHab());
		this.setSuperficie(p.getSuperficie());
		this.setAlquiler(p.isAlquiler());
		this.setVenta(p.isVenta());
		this.setPrecioAlquiler(p.getPrecioAlquiler());
		this.setPrecioVenta(p.getPrecioVenta());
		this.setGaraje(p.isGaraje());
		this.numPiso=p.getNumPiso();
		this.letra=p.getLetra();
		this.estudio=p.isEstudio();
	}
	
	public Piso(Casa c) {
		this.setId(c.getId());
		this.setDireccion(c.getDireccion());
		this.setNumHab(c.getNumHab());
		this.setSuperficie(c.getSuperficie());
		this.setAlquiler(c.isAlquiler());
		this.setVenta(c.isVenta());
		this.setPrecioAlquiler(c.getPrecioAlquiler());
		this.setPrecioVenta(c.getPrecioVenta());
		this.setGaraje(c.isGaraje());
		this.numPiso=0;
		this.letra=LETRAS[0];
		this.estudio=false;
	}

	public int getNumPiso() {
		return numPiso;
	}

	public void setNumPiso(int numPiso) {
		this.numPiso = numPiso;
	}

	public char getLetra() {
		return letra;
	}

	public void setLetra(char letra) {
		checkLetra(letra);
	}

	public boolean isEstudio() {
		return estudio;
	}

	public void setEstudio(boolean estudio) {
		this.estudio = estudio;
	}
	
	@Override
	public void setNumHab(int numHab) {
		if(this.estudio&&numHab!=1) {
			System.err.println("El número de habitaciones siendo un estudio sólo puede ser 1;");
			super.setNumHab(1);
		}else if(numHab==1)super.setNumHab(1);
		else {
			if(numHab<1) {
				System.err.println("Número de habitaciones erróneo;\nInicializado número por defecto;");
				super.setNumHab(1);
			}else super.setNumHab(numHab);
		}
	}
	
	@Override
	public String toString() {
		String toret;
		if(estudio)toret="Estudio ";
		else toret="Piso ";
		toret+=getNumPiso()+""+ getLetra()+"\n"+super.toString();
		
		return toret;
	}
	
}
