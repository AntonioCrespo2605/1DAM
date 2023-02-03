package bbdd;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BDConnection {
	private static Connection conexion;
	private static String USER;
	private static String PASSWD;
	private static String BDNAME;
	private static String PORT;
	private static String URL;

	public BDConnection() throws Exception {
		startConnection();
	}

	public void startConnection() throws Exception {
		Properties leyendo = new Properties();

		leyendo.load(new FileInputStream("UserInfo.props"));
		USER = leyendo.getProperty("username");
		PASSWD = leyendo.getProperty("password");
		BDNAME = leyendo.getProperty("database_name");
		PORT = leyendo.getProperty("port");

		conexion = null;

		URL = "jdbc:mysql://localhost:" + PORT + "/" + BDNAME + "?useServerPrepStmts=true";

		Class.forName("com.mysql.cj.jdbc.Driver");
		conexion = DriverManager.getConnection(URL, USER, PASSWD);
	}

	public static void endConnection() throws SQLException {
		if (conexion != null) {
			conexion.close();
		}
	}

	public Connection getConexion() {
		return BDConnection.conexion;
	}

}
