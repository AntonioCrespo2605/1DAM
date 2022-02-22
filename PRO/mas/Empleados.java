package main;
import java.util.Scanner;

public class Empleados {
	public static int numEmpleados = 0;
	private static Scanner sc = new Scanner(System.in);
	
	private Empleado[] empleados;
	
	public Empleado leerEmpleado() {
		Empleado empleado = new Empleado();
		String aux=sc.nextLine();
		System.out.println("Escribe el nif:");
		empleado.setNif(sc.nextLine());
		
		System.out.println("Escribe el nombre:");
		empleado.setNombre(sc.nextLine());
		
		System.out.println("Escribe el sueldo base:");
		empleado.setSueldoBase(sc.nextFloat());
		
		System.out.println("Escribe el número de horas extra:");
		empleado.setHorasExtra(sc.nextInt());
		
		System.out.println("Escribe el tipo de IRPF:");
		empleado.setTipoIrpf(sc.nextInt());
		
		String op="";
		
		do {
		aux=sc.nextLine();
		System.out.println("Casado?:(s/n)");
		op=sc.nextLine();
		if(op.equals("s"))empleado.setCasado(true);
		else if(op.equals("n"))empleado.setCasado(false);
		}while(!(op.equals("s")||op.equals("n")));
		
		System.out.println("Escribe el número de hijos:");
		empleado.setNumHijos(sc.nextInt());
		
		return empleado;
		
	}
	
	public Empleado masCobra() {
		Empleado toret=getEmpleados()[0];
		for(int i=1;i<getEmpleados().length;i++) {
			if(getEmpleados()[i].sueldoTotal()>toret.sueldoTotal())toret=getEmpleados()[i];
		}
		return toret;
	}
	
	public Empleado menosCobra() {
		Empleado toret=getEmpleados()[0];
		for(int i=1;i<getEmpleados().length;i++) {
			if(getEmpleados()[i].sueldoTotal()<toret.sueldoTotal())toret=getEmpleados()[i];
		}
		return toret;
	}
	public Empleado masCobraPorHoraExtra() {
		Empleado toret=getEmpleados()[0];
		for(int i=1;i<getEmpleados().length;i++) {
			if(getEmpleados()[i].sueldoExtra()>toret.sueldoExtra())toret=getEmpleados()[i];
		}
		return toret;
	}
	
	public Empleado[] getEmpleados() {
		return empleados;
	}

	public void setEmpleados(Empleado[] empleados) {
		this.empleados = empleados;
	}
	
	public String toString() {
		String toret="";
		
		for(int i=0;i<getEmpleados().length;i++) {
			toret=toret+("Datos del empleado "+i+":\n"+getEmpleados()[i].toString()+"\n");
		}
		
		return  toret;
	}

	public void ordenaEmpleados() {
		int posMin=0;
		Empleado empAux=new Empleado();
		
		for(int i=0;i<getEmpleados().length;i++) {

			for(int j=i;j<getEmpleados().length;j++) {
				if(i==j) {
					empAux=getEmpleados()[j];
					posMin=j;
				}else {
					if(getEmpleados()[j].getSueldoBase()<empAux.getSueldoBase()) {
						empAux=getEmpleados()[j];
						posMin=j;
					}
				}
			}
			empAux=getEmpleados()[i];
			getEmpleados()[i]=getEmpleados()[posMin];
			getEmpleados()[posMin]=empAux;
		}
	}
	public Empleados() {
		System.out.println("Cuantos empleados quieres comprobar:");
		int entrada = sc.nextInt();
		if(entrada > 0 && entrada <= 20) {
			numEmpleados = entrada;
		}
		empleados = new Empleado[numEmpleados];
		
		for(int i=0;i<empleados.length;i++) {
			System.out.println("Empleado numero "+i+":");
			empleados[i]=leerEmpleado();
		}
	}
	
}
