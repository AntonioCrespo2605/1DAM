package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Agencia;

class AgenciaTest {

	@Test
	void testAnhadirPisoYCuantosPisos() {
		Agencia agencia=new Agencia("nombre agencia");
		agencia.addNewAparment();
		assertEquals(agencia.howManyPisos(),11);
	}
	
	@Test
	void testAnhadirPisoYCuantosPisosError() {
		Agencia agencia=new Agencia("nombre agencia");
		agencia.addNewAparment();
		assertNotEquals(agencia.howManyPisos(),10);
	}
	
	

}
