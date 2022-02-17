package pruebas;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import main.Piso;
import main.Agencia;
import main.Direccion;

class AgenciaTest {
	Agencia agencia;
	static Scanner sc;
	
	@BeforeAll
	static void startScanner() {
	sc=new Scanner(System.in);
	}
	@AfterAll
	static void closeScanner() {
		sc.close();
	}
	
	@BeforeEach
	void inicializarAgencia() {
		agencia=new Agencia("nombre agencia");
	}
	
	@Disabled
	@Test
	void testAnhadirPropiedadYCuantasPropiedades() {
		agencia.addNewAparment();
		assertEquals(agencia.howManyPisos(),11);
	}
	
	@Disabled
	@Test
	void testAnhadirPropiedadYCuantasPropiedadesError() {
		agencia.addNewAparment();
		assertNotEquals(agencia.howManyPisos(),10);
	}
	
	/**pasar a publico autoGenTenAparments()**/
//	@Disabled
//	@Test
//	void testAutogenerar10Propiedades() {
//		ArrayList<Piso> pisosAux =agencia.autoGenTenAparments();
//		assertEquals(10,pisosAux.size());
//		assertNotNull(pisosAux.get(i));
//	}
	
//	@Disabled
//	@Test
//	void testAutogenerar10PropiedadesPorDebajoFail() {
//		ArrayList<Piso> pisosAux =agencia.autoGenTenAparments();
//		assertNotEquals(9,pisosAux.size());
//	}

//	@Disabled
//	@Test
//	void testAutogenerar10PropiedadesPorArribaFail() {
//		ArrayList<Piso> pisosAux =agencia.autoGenTenAparments();
//		assertNotEquals(11,pisosAux.size());
//	}
	
	/**pasar a publico newAparment()**/
// 	@Disabled
//	@Test
//	void testCrearNuevaPropiedad() {
//		Piso pisoAux=agencia.newAparment();
//		assertNotNull(pisoAux);
//	}
	
	/**pasar a publico surfaceValid**/
//	@Disabled
//	@Test
//	void testSuperficieValida() {
//		assertTrue(agencia.surfaceValid(10));
//	}
	
//	@Disabled
//	@Test
//	void testSuperficieInvaliada() {
//		assertFalse(agencia.surfaceValid(0));
//		assertFalse(agencia.surfaceValid(-10));
//	}

	/****/
	@Disabled
	@Test
	void testBorrarPropiedad() {
		agencia.deleteAparmentWithId(3);
		assertEquals(agencia.getPisos().size(),9);
	}
	
	@Disabled
	@Test
	void testBorrarPropiedadFalse() {
		agencia.deleteAparmentWithId(3);
		assertNotEquals(agencia.getPisos().size(),10);
	}
	
	@Disabled
	@Test
	void obtenerLaPosicionDeUnId() {
		int actual=agencia.getPositionOfId(3);
		assertEquals(actual,2);
	}
	
	@Disabled
	@Test
	void obtenerLaPosicionDeUnIdFalse() {
		int actual=agencia.getPositionOfId(3);
		assertNotEquals(actual,3);
	}
	
	@Disabled
	@Test
	void testModificarDireccion() {
		System.out.println("Test de cambiar direccion:");
		Direccion actual = agencia.getPisos().get(agencia.getPositionOfId(5)).getDireccion();
		agencia.setAparmentAdressWithId(5);
		Direccion esperado=agencia.getPisos().get(agencia.getPositionOfId(5)).getDireccion();
		assertNotEquals(esperado,actual);
	}
	
	@Disabled
	@Test
	void testModificarPropiedadEntera() {
		Piso esperado=agencia.getPisos().get(agencia.getPositionOfId(5));
		System.out.println(esperado.toString());
		agencia.updateAparmentWithId(5);
		Piso actual=agencia.getPisos().get(agencia.getPositionOfId(5));
		System.out.println(actual.toString());
		assertNotEquals(esperado,actual);
	}
	
	@Disabled
	@Test
	void cambiarDisponibilidadVentaAFalseConAlquilerFalse() {
		System.out.println("En el siguiente test cambie la venta a 'n' para comprobar\nque el programa no permite hacerlo;");
		Piso pisoaux=new Piso(13,new Direccion(11),10,false,0,true,10,false);
		agencia.getPisos().add(pisoaux);
		agencia.setOnSaleApatmentWithId(13);
		assertTrue(agencia.getPisos().get(agencia.getPositionOfId(13)).getVenta());
	}
	@Disabled
	@Test
	void cambiarDisponibilidadVentaAFalseConAlquilerTrue() {
		System.out.println("En el siguiente test cambie la venta a 'n' para comprobar\nque el programa si permite hacerlo;");
		Piso pisoaux=new Piso(13,new Direccion(11),10,true,10,true,10,false);
		agencia.getPisos().add(pisoaux);
		agencia.setOnSaleApatmentWithId(13);
		assertFalse(agencia.getPisos().get(agencia.getPositionOfId(13)).getVenta());
	}
	
	@Disabled
	@Test
	void cambiarDisponibilidadVentaATrue() {
		System.out.println("En el siguiente test cambie la venta a 's';");
		Piso pisoaux=new Piso(13,new Direccion(11),10,true,10,false,10,false);
		agencia.getPisos().add(pisoaux);
		agencia.setOnSaleApatmentWithId(13);
		assertTrue(agencia.getPisos().get(agencia.getPositionOfId(13)).getVenta());
	}
	
	@Disabled
	@Test
	void cambiarDisponibilidadAlquilerAFalseConVentaFalse() {
		System.out.println("En el siguiente test cambie el alquiler a 'n' para comprobar\nque no deja cambiarlo;");
		Piso pisoaux=new Piso(13,new Direccion(11),10,true,10,false,10,false);
		agencia.getPisos().add(pisoaux);
		agencia.setOnRentApatmentWithId(13);
		assertTrue(agencia.getPisos().get(agencia.getPositionOfId(13)).getAlquiler());
	}
	
	@Disabled
	@Test
	void cambiarDisponibilidadAlquilerAFalseConVentaTrue() {
		System.out.println("En el siguiente test cambie el alquiler a 'n' para comprobar\nque si deja cambiarlo;");
		Piso pisoaux=new Piso(13,new Direccion(11),10,true,10,true,10,false);
		agencia.getPisos().add(pisoaux);
		agencia.setOnRentApatmentWithId(13);
		assertFalse(agencia.getPisos().get(agencia.getPositionOfId(13)).getAlquiler());
	}
	
	@Disabled
	@Test
	void cambiarDisponibilidadAlquilerATrue() {
		System.out.println("En el siguiente test cambie el alquiler a 's';");
		Piso pisoaux=new Piso(13,new Direccion(11),10,false,10,true,10,false);
		agencia.getPisos().add(pisoaux);
		agencia.setOnRentApatmentWithId(13);
		assertTrue(agencia.getPisos().get(agencia.getPositionOfId(13)).getAlquiler());
	}
	
	@Disabled
	@Test
	void modificarPrecioVentaPropiedad() {
		System.out.println("Escribe el esperado y al cambiar el precio de venta ponga el mismo número para comprobar que se cambia completamente(float format:0.0);");
		System.out.println("No introducir precio negativo ya que dará error debido a que no lo cambiará");
		float esperado=Float.parseFloat(sc.nextLine());
		Piso pisoaux=new Piso(13,new Direccion(11),10,true,10,false,0,false);
		agencia.getPisos().add(pisoaux);
		agencia.setOnSaleAparmentPriceWithId(13);
		float actual=agencia.getPisos().get(agencia.getPositionOfId(13)).getPrecioVenta();
		assertEquals(esperado,actual);
	}
	
	@Disabled
	@Test
	void modificarPrecioVentaPropiedadFalse() {
		System.out.println("Escribe el esperado y al cambiar el precio de venta ponga uno DISTINTO para comprobar que se cambia completamente(float format:0.0);");
		System.out.println("No introducir precio negativo ya que dará error debido a que no lo cambiará;");
		float esperado=Float.parseFloat(sc.nextLine());
		Piso pisoaux=new Piso(13,new Direccion(11),10,true,10,false,0,false);
		agencia.getPisos().add(pisoaux);
		agencia.setOnSaleAparmentPriceWithId(13);
		float actual=agencia.getPisos().get(agencia.getPositionOfId(13)).getPrecioVenta();
		assertNotEquals(esperado,actual);
	}
	
	@Disabled
	@Test
	void modificarPrecioAlquilerPropiedad() {
		System.out.println("Escribe el esperado y al cambiar el precio de alquiler ponga el mismo número para comprobar que se cambia completamente(float format:0.0);");
		System.out.println("No introducir precio negativo ya que dará error debido a que no lo cambiará;");
		float esperado=Float.parseFloat(sc.nextLine());
		Piso pisoaux=new Piso(13,new Direccion(11),10,false,0,true,10,false);
		agencia.getPisos().add(pisoaux);
		agencia.setOnRentAparmentPriceWithId(13);
		float actual=agencia.getPisos().get(agencia.getPositionOfId(13)).getPrecioAlquiler();
		assertEquals(esperado,actual);
	}
	
	@Disabled
	@Test
	void modificarSuperficie() {
		System.out.println("Escribe la superficie esperada y al cambiarla ponga el mismo número para comprobar que se cambia completamente(float format:0.0);");
		System.out.println("No introducir superficies negativas ni nulas ya que dará error debido a que no lo cambiará;");
		float esperado=Float.parseFloat(sc.nextLine());
		Piso pisoaux=new Piso(13,new Direccion(11),10,false,0,true,10,false);
		agencia.getPisos().add(pisoaux);
		agencia.setSurfaceOfAparmentWithId(13);
		float actual=agencia.getPisos().get(agencia.getPositionOfId(13)).getMetrosCuadrados();
		assertEquals(esperado,actual);
	}
	
	@Disabled
	@Test
	void filtrarSoloPropiedadesEnVenta() {
		String nombre="agenciaName";
		Piso pisoConVenta1=new Piso(1,new Direccion(1),10,false,0,true,10,false);
		Piso pisoConAlquiler1=new Piso(2,new Direccion(2),20,true,20,false,0,false);
		Piso pisoConAmbas=new Piso(3,new Direccion(3),30,true,30,true,30,false);
		ArrayList<Piso> aux=new ArrayList<Piso>();
		aux.add(pisoConVenta1);
		aux.add(pisoConAlquiler1);
		aux.add(pisoConAmbas);
		Agencia agenciaAux=new Agencia(nombre,aux);
		Agencia actual=agenciaAux.OnlyForSale();
		int numActual=actual.howManyPisos();
		
		assertEquals(2,numActual);
		assertEquals(-1,actual.getPositionOfId(2));//getPositionOfId devuelve -1 si no encuentra el id
	}
	
	@Disabled
	@Test
	void filtrarSoloPropiedadesEnAlquiler() {
		String nombre="agenciaName";
		Piso pisoConVenta1=new Piso(1,new Direccion(1),10,false,0,true,10,false);
		Piso pisoConAlquiler1=new Piso(2,new Direccion(2),20,true,20,false,0,false);
		Piso pisoConAmbas=new Piso(3,new Direccion(3),30,true,30,true,30,false);
		ArrayList<Piso> aux=new ArrayList<Piso>();
		aux.add(pisoConVenta1);
		aux.add(pisoConAlquiler1);
		aux.add(pisoConAmbas);
		Agencia agenciaAux=new Agencia(nombre,aux);
		Agencia actual=agenciaAux.OnlyForRent();
		int numActual=actual.howManyPisos();
		
		assertEquals(2,numActual);
		assertEquals(-1,actual.getPositionOfId(1));//getPositionOfId devuelve -1 si no encuentra el id
	}
	
	@Disabled
	@Test
	void filtrarSoloPropiedadesEntrePrecios() {
		String nombre="agenciaName";
		Piso piso1=new Piso(1,new Direccion(1),10,true,100,true,1000,false);//s
		Piso piso2=new Piso(2,new Direccion(2),20,true,1500,true,10,false);//s
		Piso piso3=new Piso(3,new Direccion(3),30,false,0,true,200,false);//n
		Piso piso4=new Piso(4,new Direccion(4),40,true,3000,false,0,false);//n
		Piso piso5=new Piso(5,new Direccion(5),50,true,20,true,3050,false);//n
		
		ArrayList<Piso> aux=new ArrayList<Piso>();
		aux.add(piso1);
		aux.add(piso2);
		aux.add(piso3);
		aux.add(piso4);
		aux.add(piso5);
		
		Agencia agenciaAux=new Agencia(nombre,aux);
		Agencia actual=agenciaAux.OnlyBetweenPrice(500, 2000);
		int numActual=actual.howManyPisos();
		
		assertEquals(2,numActual);
		assertEquals(-1,actual.getPositionOfId(3));//getPositionOfId devuelve -1 si no encuentra el id
		assertEquals(-1,actual.getPositionOfId(4));
		assertEquals(-1,actual.getPositionOfId(5));
	}
	
	@Disabled
	@Test
	void filtrarSoloPropiedadesEntreSuperficies() {
		String nombre="agenciaName";
		Piso piso1=new Piso(1,new Direccion(1),220,true,100,true,1000,false);//s
		Piso piso2=new Piso(2,new Direccion(2),4000,true,1500,true,10,false);//s
		Piso piso3=new Piso(3,new Direccion(3),2345,false,0,true,200,false);//s
		Piso piso4=new Piso(4,new Direccion(4),199,true,3000,false,0,false);//n
		Piso piso5=new Piso(5,new Direccion(5),5001,true,20,true,3050,false);//n
		
		ArrayList<Piso> aux=new ArrayList<Piso>();
		aux.add(piso1);
		aux.add(piso2);
		aux.add(piso3);
		aux.add(piso4);
		aux.add(piso5);
		
		Agencia agenciaAux=new Agencia(nombre,aux);
		Agencia actual=agenciaAux.OnlyBetweenSurfaces(200, 5000);
		int numActual=actual.howManyPisos();
		
		assertEquals(3,numActual);
		assertEquals(-1,actual.getPositionOfId(4));//getPositionOfId devuelve -1 si no encuentra el id
		assertEquals(-1,actual.getPositionOfId(5));
	}
	
	@Disabled
	@Test
	void filtrarSoloPisos() {
		String nombre="agenciaName";
		Piso piso1=new Piso(1,new Direccion(1),220,true,100,true,1000,true);//s
		Piso piso2=new Piso(2,new Direccion(2),4000,true,1500,true,10,false);//n
		Piso piso3=new Piso(3,new Direccion(3),2345,false,0,true,200,false);//n
		Piso piso4=new Piso(4,new Direccion(4),199,true,3000,false,0,false);//n
		
		ArrayList<Piso> aux=new ArrayList<Piso>();
		aux.add(piso1);
		aux.add(piso2);
		aux.add(piso3);
		aux.add(piso4);
		
		Agencia agenciaAux=new Agencia(nombre,aux);
		Agencia actual=agenciaAux.onlyAparments();
		int numActual=actual.howManyPisos();
		
		assertEquals(1,numActual);
		assertEquals(-1,actual.getPositionOfId(4));//getPositionOfId devuelve -1 si no encuentra el id
		assertEquals(-1,actual.getPositionOfId(2));
		assertEquals(-1,actual.getPositionOfId(3));
	}
	
	@Disabled
	@Test
	void filtrarSoloCasas() {
		String nombre="agenciaName";
		Piso piso1=new Piso(1,new Direccion(1),220,true,100,true,1000,true);//n
		Piso piso2=new Piso(2,new Direccion(2),4000,true,1500,true,10,false);//s
		Piso piso3=new Piso(3,new Direccion(3),2345,false,0,true,200,false);//s
		Piso piso4=new Piso(4,new Direccion(4),199,true,3000,false,0,false);//s
		
		ArrayList<Piso> aux=new ArrayList<Piso>();
		aux.add(piso1);
		aux.add(piso2);
		aux.add(piso3);
		aux.add(piso4);
		
		Agencia agenciaAux=new Agencia(nombre,aux);
		Agencia actual=agenciaAux.onlyHouses();
		int numActual=actual.howManyPisos();
		
		assertEquals(3,numActual);
		assertEquals(-1,actual.getPositionOfId(1));//getPositionOfId devuelve -1 si no encuentra el id
	}
}
