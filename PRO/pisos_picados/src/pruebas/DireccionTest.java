package pruebas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Direccion;

class DireccionTest {
	
	Direccion dir;
	
	@BeforeEach
	void inicializarPiso() {
		dir=new Direccion(1);
		dir.setTipoCalle("avenida");
		dir.setCalle("malos aires");
		dir.setCiudad("vigo");
		dir.setcP(36693);
		dir.setLetra('a');
		dir.setPlanta(3);
		dir.setProvincia("pontevedra");
	}
	
	/*cambiar a public correctProvincia*/
//	@Test
//	void testProvinciaCorrecta() {
//		assertTrue(dir.correctProvincia("ponTeveDra"));
//	}
//	@Test
//	void testProvinciaIncorrecta() {
//		assertFalse(dir.correctProvincia("ponTeBeDra"));
//	}
	
	@Test
	void testToStringProvincia() {
		assertEquals("avenida malos aires planta:3 letra:a\nvigo pontevedra 36693",dir.toString(true));
	}
	
	@Test
	void testToStringProvinciaFalse() {
		assertNotEquals("cualquier cosa para ver que va mal",dir.toString(true));
	}
	
	
}
