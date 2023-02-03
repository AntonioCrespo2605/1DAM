package controlador;

import java.sql.ResultSet;
import java.sql.Statement;
import bbdd.BDConnection;


public class T5ArticulosVendidos {
	private static BDConnection conexion;
	
	public static String mostarInfoT5Vendidos(ArticuloController controlador) throws Exception {
		String toret="";
		conexion = new BDConnection();
		Statement s = conexion.getConexion().createStatement();
		String query="SELECT * FROM CINCO_ART_VENDIDOS";
		ResultSet rs=s.executeQuery(query);
		int id;
		toret="------------------------------------------";
		while(rs.next()) {
			id=rs.getInt(1);
			toret+="\n"+controlador.getArticuloId(id).toString();
			toret+="\nTotal vendido:" +rs.getInt(4);
			toret+="\n------------------------------------------";
		}
		return toret;
	}
}
