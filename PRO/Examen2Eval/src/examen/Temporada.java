package examen;

public class Temporada {
	private int numCapitulos, numTemporada;

	public Temporada(int numCapitulos, int numTemporada) {
		super();
		this.numCapitulos = numCapitulos;
		this.numTemporada = numTemporada;
	}

	public int getNumCapitulos() {
		return numCapitulos;
	}

	public void setNumCapitulos(int numCapitulos) {
		this.numCapitulos = numCapitulos;
	}

	public int getNumTemporada() {
		return numTemporada;
	}

	public void setNumTemporada(int numTemporada) {
		this.numTemporada = numTemporada;
	}

	@Override
	public String toString() {
		return "Temporada [numCapitulos=" + numCapitulos + ", numTemporada=" + numTemporada + "]";
	}
	
	
}
