package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import main.*;

public class AgenciaTest {
	static Agencia a;
	
	@BeforeEach
	public void inicializarAgencia() {
		a=new Agencia("nombre");
	}
	
	@Disabled
	@Test
	public void testAnhadirPiso() {
		Piso p=new Piso();
		a.anhadirInmueble(p);
		assertEquals(13, a.getInmuebles().size());
	}
	
	@Disabled
	@Test
	public void testAnhadirPisoFalse() {
		Piso p=new Piso();
		a.anhadirInmueble(p);
		assertNotEquals(12, a.getInmuebles().size());
	}
	
	@Disabled
	@Test
	public void testAnhadirCasa() {
		Casa c=new Casa();
		a.anhadirInmueble(c);
		assertEquals(13, a.getInmuebles().size());
	}
	
	@Disabled
	@Test
	public void testAnhadirCasaFalse() {
		Casa c=new Casa();
		a.anhadirInmueble(c);
		assertNotEquals(12, a.getInmuebles().size());
	}
	
	@Disabled
	@Test
	public void borrarInmueble() {
		a.eliminarInmueble(1);
		assertEquals(11, a.getInmuebles().size());
	}
	
	@Disabled
	@Test
	public void borrarInmuebleFalse() {
		a.mostrarInmuebles();
		a.eliminarInmueble(2);
		assertNotEquals(12, a.getInmuebles().size());
	}
}
