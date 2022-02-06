package main;

import java.util.Scanner;

public class Main {
	private static Scanner sc=new Scanner(System.in);
	private static boolean rent_filter=false;
	private static boolean sale_filter=false;
	private static boolean shorter=false;
	private static Agencia agencia;
	
	public static void main(String[] args) {
		System.out.println("Escribe el nombre de la agencia:");
		String name=sc.nextLine();
		agencia=new Agencia(name);
		
		
	}

	private static void Anhadirpiso() {
		agencia.addNewAparment();
	}
	
	private static void soloAlquiler() {
		rent_filter=true;
		sale_filter=false;
	}
	
	private static void soloVenta() {
		sale_filter=true;
		rent_filter=false;
	}
	
	private static void ventaYAlquiler() {
		rent_filter=false;
		sale_filter=false;
	}
	



}
