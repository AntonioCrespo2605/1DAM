package pruebas;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
@RunWith(Suite.class)
@SuiteClasses({AgenciaTest.class, DireccionTest.class})
@IncludeTags({"anhadir","generar","valores_validos","borrar", "obtener_pos","modificar","filtrar","provincia"})
public class AllTests {
	
}
