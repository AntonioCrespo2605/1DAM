package main;

public class Piso {
	private boolean alquiler;
	private float precio_alquiler;
	private boolean venta;
	private float precio_venta;
	private int metros_cuadrados;
	private Direccion direccion;
	private int id;
	
	public Piso() {
		this.id=-1;
		this.direccion=new Direccion("");
		this.metros_cuadrados=1;
		this.alquiler=false;
		this.precio_alquiler=0;
		this.venta=false;
		this.precio_venta=0;
	}
	
	private void setToDefault() {
		this.id=-1;
		this.direccion=new Direccion("");
		this.metros_cuadrados=1;
		this.alquiler=false;
		this.precio_alquiler=0;
		this.venta=false;
		this.precio_venta=0;
	}
	
	public Piso(int id,Direccion direccion,int metros_cuadrados,boolean alquiler,float precio_alquiler,boolean venta, float precio_venta) {
		boolean ok=true;
		int cont_fails=0;
		
		if(alquiler==false&&venta==false) {
			System.out.println("Los pisos deben estar en venta y/o en alquiler;");
			ok=false;
			cont_fails++;
		}
		if(alquiler&&precio_alquiler<0) {
			System.out.println("El precio de alquiler debe ser positivo;");
			ok=false;
			cont_fails++;
		}
		if(venta&&precio_venta<0) {
			System.out.println("El precio de venta debe ser positivo;");
			ok=false;
			cont_fails++;
		}
		if(metros_cuadrados<=0) {
			System.out.println("Los metros cuadrados deben ser 1 o más;");
			ok=false;
			cont_fails++;
		}
		
		if(ok) {
			if(alquiler) this.precio_alquiler=precio_alquiler;
			else this.precio_alquiler=-1;
			
			
			if(venta) this.precio_venta=precio_venta;
			else this.precio_venta=-1;
			
			this.alquiler=alquiler;
			this.venta=venta;
			this.id=id;
			this.direccion=direccion;
			this.metros_cuadrados=metros_cuadrados;
			
		}else {
			setToDefault();
			if(cont_fails==1)System.out.println("Se ha detectado 1 error;");
			else System.out.println("Se han detectado "+cont_fails+" errores;");
			System.out.println("Los valores se han inicializado por defecto;");
		}
		
	}
	
	/*getters*/
	public int getId() {
		return this.id;
	}
	public Direccion getDireccion() {
		return this.direccion;
	}
	public int getMetrosCuadrados() {
		return this.metros_cuadrados;
	}
	public boolean getAlquiler() {
		return this.alquiler;
	}
	public boolean getVenta() {
		return this.venta;
	}
	public float getPrecioAlquiler() {
		return this.precio_alquiler;
	}
	public float getPrecioVenta() {
		return this.precio_venta;
	}
	
	/*setters*/
	
	public void setId(int id) {
		this.id=id;
	}
	
	public void setDireccion(Direccion direccion) {
		this.direccion=direccion;
	}
	public void setMetrosCuadrados(int metros) {
		if(metros<=0)System.out.println("No se puede modificar la superficie con metros cuadrados negativos o nulos;");
		else {
			this.metros_cuadrados=metros;
		}
	}
	public void setAlquiler(boolean alquiler) {
		if(alquiler) this.alquiler=alquiler;
			else {
			if(this.venta) {
				this.alquiler=alquiler;
				this.precio_alquiler=-1;
			}else {
				System.out.println("Error, el piso debe estar en venta para eliminar el alquiler;");
			}
		}
	}
	
	public void setVenta(boolean venta) {
		if(venta) this.venta=venta;
			else {
			if(this.alquiler) {
				this.venta=venta;
				this.precio_venta=-1;
			}else {
				System.out.println("Error, el piso debe estar en alquiler para eliminar la venta;");
			}
		}
	}
	
	public void setPrecioVenta(float precio_venta) {
		if(precio_venta>=0) {
			this.precio_venta=precio_venta;
		}else System.out.println("Error, el precio de venta no puede ser negativo;");
	}
	
	public void setPrecioAlquiler(float precio_alquiler) {
		if(precio_alquiler>=0) {
			this.precio_alquiler=precio_alquiler;
		}else System.out.println("Error, el precio de alquiler no puede ser negativo;");
	}
	
	//info completa del piso
	public String toString() {
		String toret="";
		String aux="";
		if(getId()<0) toret="Error en la lectura del piso;";
		else {
			if(getVenta()) {
				aux+=";\nVenta: Si";
				aux+=";\nPrecio de Venta:"+getPrecioVenta();
			}else aux+=";\nVenta: No";
			
			if(getAlquiler()) {
				aux+=";\nAlquiler: Si";
				aux+=";\nPrecio de Alquiler:"+getPrecioAlquiler();
			}else aux+=";\nAlquiler: No";
			
			toret+="ID:"+getId()+";\nDireccion:\n"+getDireccion().toString()+";\nSuperficie en metros cuadrados:"+getMetrosCuadrados()+aux;
		}
		return toret;
	}
	
	//proporciona solo el id la superficie y los precios si los tiene
	public String toStringShort() {
		String toret="";
		String aux="";
		if(getId()<0)System.out.println("Error en la lectura del piso;");
		else {
			
			if(getVenta()) aux+=", Precio de Venta:"+getPrecioVenta();
			else aux+=", Venta: No";
			
			if(getAlquiler())aux+=", Precio de Alquiler:"+getPrecioAlquiler();
			else aux+=", Alquiler: No";
			
			toret="Id:"+getId()+", Superficie:"+getMetrosCuadrados()+aux;
		}
		return toret;
	}

	//para que no de problemas al generar alquileres y ventas en los nuevos pisos
	public void setAlquiler2(boolean alquiaux) {
		this.alquiler=alquiaux;
	}

	public void setVenta2(boolean ventaux) {
		this.venta=ventaux;
		
	}
	
	
	
}

