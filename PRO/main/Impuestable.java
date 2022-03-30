package main;

public interface Impuestable {
	public double precioAlquilerConImpuesto();
	public double precioVentaConImpuesto();
	public static final int IVA=10;
	public static final int ITP=8;
}
