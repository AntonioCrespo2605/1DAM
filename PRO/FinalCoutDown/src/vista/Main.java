package vista;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		boolean stay;
		do {
			stay = true;
			try {
				login();
				ArticuloVista.gestionArticulos();
				stay = false;
			} catch (SQLException s) {
				if (s.getErrorCode() == 1045) {
					stay = true;
					System.err.println("Usuario o contraseña incorrecta. Porfavor inténtelo de nuevo;");
				} else {
					stay = false;
					System.err.println(s.getMessage());
				}
				recogerError(s.getErrorCode(), s.getMessage());
			} catch (Exception e) {
				System.err.println(e.getMessage());
				stay = false;
				recogerError(e.hashCode(),e.getMessage());
			}
		} while (stay);
	}

	private static void recogerError(int errorCode, String message) {
		try {
			File f=new File("errores.xml");
			if(!f.exists())f.createNewFile();
			BufferedReader br=new BufferedReader(new FileReader(f));
			ArrayList<String> lineas=new ArrayList<String>();
			String linea;
			while((linea=br.readLine())!=null) {
				lineas.add(linea);
			}
			br.close();
			if(lineas.size()<3) {
				lineas=new ArrayList<String>();
				lineas.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				lineas.add("<errores>");
				lineas.add("<error cod=\""+errorCode+"\">"+message+"</error>");
				lineas.add("</errores>");
			}else {
				int tamanho=lineas.size();
				lineas.remove(tamanho-1);
				lineas.add("<error cod=\""+errorCode+"\">"+message+"</error>");
				lineas.add("</errores>");
			}
			BufferedWriter bw=new BufferedWriter(new FileWriter(f));
			for(String l:lineas) {
				bw.write(l);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	}

	private static void login() {
		String user;
		String pass;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("Escribe el nombre del usuario:");
		user = sc.nextLine();
		System.out.println("Escribe la contraseña:");
		pass = sc.nextLine();
		Properties escribiendo = new Properties();
		escribiendo.setProperty("username", user);
		escribiendo.setProperty("password", pass);
		escribiendo.setProperty("database_name", "tienda_de_videojuegos");
		escribiendo.setProperty("port", "3307");
		try {
			escribiendo.store(new FileOutputStream("UserInfo.props"), "Fichero de configuración.");
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

}
