package pruebas;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import main.Piso;
import main.Agencia;
import main.Direccion;

class AgenciaTest {
	Agencia agencia;
	
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
	void cambiarDisponibilidadDeVentaAFalseConAlquilerFalse() {
		System.out.println("En el siguiente test cambie la venta a 'n' para comprobar\nque el programa no permite hacerlo;");
		Piso pisoaux=new Piso(13,new Direccion(11),10,false,0,true,10,false);
		agencia.getPisos().add(pisoaux);
		agencia.setOnSaleApatmentWithId(13);
		assertTrue(agencia.getPisos().get(agencia.getPositionOfId(13)).getVenta());
	}
	@Disabled
	@Test
	void cambiarDisponibilidadDeVentaAFalseConAlquilerTrue() {
		System.out.println("En el siguiente test cambie la venta a 'n' para comprobar\nque el programa si permite hacerlo;");
		Piso pisoaux=new Piso(13,new Direccion(11),10,true,10,true,10,false);
		agencia.getPisos().add(pisoaux);
		agencia.setOnSaleApatmentWithId(13);
		assertFalse(agencia.getPisos().get(agencia.getPositionOfId(13)).getVenta());
	}
	
	@Disabled
	@Test
	void cambiarDisponibilidadDeVentaATrue() {
		System.out.println("En el siguiente test cambie la venta a 's';");
		Piso pisoaux=new Piso(13,new Direccion(11),10,true,10,false,10,false);
		agencia.getPisos().add(pisoaux);
		agencia.setOnSaleApatmentWithId(13);
		assertTrue(agencia.getPisos().get(agencia.getPositionOfId(13)).getVenta());
	}
	
	@Disabled
	@Test
	void cambiarDisponibilidadDeAlquilerAFalseConVentaFalse() {
		System.out.println("En el siguiente test cambie el alquiler a 'n' para comprobar\nque no deja cambiarlo;");
		Piso pisoaux=new Piso(13,new Direccion(11),10,true,10,false,10,false);
		agencia.getPisos().add(pisoaux);
		agencia.setOnRentApatmentWithId(13);
		assertTrue(agencia.getPisos().get(agencia.getPositionOfId(13)).getAlquiler());
	}
	
	@Disabled
	@Test
	void cambiarDisponibilidadDeAlquilerAFalseConVentaTrue() {
		System.out.println("En el siguiente test cambie el alquiler a 'n' para comprobar\nque si deja cambiarlo;");
		Piso pisoaux=new Piso(13,new Direccion(11),10,true,10,true,10,false);
		agencia.getPisos().add(pisoaux);
		agencia.setOnRentApatmentWithId(13);
		assertFalse(agencia.getPisos().get(agencia.getPositionOfId(13)).getAlquiler());
	}
	
	@Disabled
	@Test
	void cambiarDisponibilidadDeAlquilerATrue() {
		System.out.println("En el siguiente test cambie el alquiler a 's';");
		Piso pisoaux=new Piso(13,new Direccion(11),10,false,10,true,10,false);
		agencia.getPisos().add(pisoaux);
		agencia.setOnRentApatmentWithId(13);
		assertTrue(agencia.getPisos().get(agencia.getPositionOfId(13)).getAlquiler());
	}
	
	@Test
	void modificarElPrecioDeUnaAgencia() {
		
		
	}
	
	
	
	
}
