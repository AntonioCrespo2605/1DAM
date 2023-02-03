package generatePropsFile;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class GeneratePropsFile {
	public static void main(String[] args) {
		Properties escribiendo = new Properties();
		escribiendo.setProperty("username", "empleado");
		escribiendo.setProperty("password", "1234");
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
