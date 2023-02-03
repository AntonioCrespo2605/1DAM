package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import bbdd.BDConnection;
import controlador.ArticuloController;
import modelo.Consola;
import modelo.Videojuego;


class ArticulosTest {
	private static ArticuloController controlador;
	private static BDConnection conexion;
	
	@BeforeAll
	static void inicializarController() {
		try {
			controlador=new ArticuloController();
			conexion=new BDConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Disabled
	@Test
	void testAnhadirArticulo() {
		int esperado = controlador.getArticulos().size() + 2;
		int id_aux1=controlador.nuevoId();
		int id_aux2 = 0;
		Consola c=new Consola(id_aux1, 1,1, "OTRO", "OTRO", "OTRO",null, false);
		try {
		controlador.addArticulo(c);
		id_aux2=controlador.nuevoId();
		Videojuego v=new Videojuego(id_aux2, 1, 1, "titulo", "OK", "PS4",1);
		controlador.addArticulo(v);
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		int actual=controlador.getArticulos().size();
		//comprobar que la cantidad del arrayList se ha actualizado:
		assertEquals(actual, esperado);
		
		//mostrar en el arrayList
		System.out.println("-------------------------------------------");
		System.out.println(controlador.getArticuloId(id_aux1).toString());
		System.out.println("-------------------------------------------");
		System.out.println(controlador.getArticuloId(id_aux2).toString());
		System.out.println("-------------------------------------------");
		
		//bajar datos de la bbdd y mostrarlos
		try {
			Statement s = conexion.getConexion().createStatement();
			String query="SELECT * FROM ART_DISPONIBLES WHERE ID_A="+id_aux1+" OR ID_A="+id_aux2;
			ResultSet rs=s.executeQuery(query);
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
			}
			controlador.borrarArticulo(id_aux2);
			controlador.borrarArticulo(id_aux1);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	@Disabled
	@Test
	void testAnhadirArticuloFalse() {
		int esperado = controlador.getArticulos().size() + 2;
		int id_aux1=controlador.nuevoId();
		int id_aux2 = 0;
		Consola c=new Consola(id_aux1, 1,1, "OTRO", "OTRO", "OTRO",null, false);
		try {
		controlador.addArticulo(c);
		id_aux2=controlador.nuevoId();
		Videojuego v=new Videojuego(id_aux2, 1, 1, "titulo", "OK", "PS4",1);
		controlador.addArticulo(v);
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		int actual=controlador.getArticulos().size();
		//comprobar que la cantidad del arrayList se ha actualizado:
		assertNotEquals(actual, esperado-2);
	}
	
	@Disabled
	@Test
	void eliminarArticulo() {
		int esperado = controlador.getArticulos().size() - 2;
		try {
			if(!controlador.borrarArticulo(1))System.err.println("Para probar el test debe existir un articulo con el id 1");
			if(!controlador.borrarArticulo(14))System.err.println("Para probar el test debe existir un articulo con el id 14");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		int actual=controlador.getArticulos().size();
		//se han bajado en 2 los articulos
		assertEquals(esperado, actual);
		
		//no existen ya en el arrayList
		assertNull(controlador.getArticuloId(14));
		assertNull(controlador.getArticuloId(1));
		
		//comprobación en la bd:
		try {
			Statement s = conexion.getConexion().createStatement();
			String query="SELECT * FROM ART_DISPONIBLES WHERE ID_A=1 OR ID_A=14";
			ResultSet rs=s.executeQuery(query);
			while(rs.next()) {
				System.out.println("Si NO se muestra esto funciona a la perfeccion;");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Disabled
	@Test
	void eliminarArticuloFalse() {
		int esperado = controlador.getArticulos().size();
		try {
			if(!controlador.borrarArticulo(2))System.err.println("Para probar el test debe existir un articulo con el id 2");
			if(!controlador.borrarArticulo(15))System.err.println("Para probar el test debe existir un articulo con el id 15");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		int actual=controlador.getArticulos().size();
		//se han bajado en 2 los articulos
		assertNotEquals(esperado, actual);
	}

}
