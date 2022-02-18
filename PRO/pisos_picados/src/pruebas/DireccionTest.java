package pruebas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import main.Direccion;

public class DireccionTest {
	
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
//	@Tag("provincia")
//	void testProvinciaCorrecta() {
//		assertTrue(dir.correctProvincia("ponTeveDra"));
//	}
//	@Test
//	@Tag("provincia")
//	void testProvinciaIncorrecta() {
//		assertFalse(dir.correctProvincia("ponTeBeDra"));
//	}
	
}
