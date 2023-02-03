package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

import bbdd.BDConnection;
import modelo.Articulo;
import modelo.Consola;
import modelo.Videojuego;

public class ArticuloController {
	private static ArrayList<Articulo> articulos;
	private static BDConnection conexion;

	// el controlador crea la conexion y recupera los articulos en el arrayList
	public ArticuloController() throws Exception {
		conexion = new BDConnection();
		articulos = new ArrayList<Articulo>();

		guardarArticulosBD();
	}

	// rellena el arrayList de los articulos de la BD
	private static void guardarArticulosBD() throws SQLException {
		Statement s = conexion.getConexion().createStatement();
		String query = "SELECT * FROM ART_DISPONIBLES";
		ResultSet rs = s.executeQuery(query);
		String aux;
		Videojuego v;
		Consola c;
		while (rs.next()) {
			aux = rs.getString(4);
			if (isNumeric(aux)) {
				v = new Videojuego(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(5), rs.getString(6), rs.getString(8), rs.getInt(7));
				articulos.add(v);
			} else {
				c = new Consola(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getBoolean(14));
				articulos.add(c);
			}
		}
		rs.close();
	}

	private static boolean isNumeric(String cadena) {
		if (cadena == null)return false;
		char aux;
		for (int i = 0; i < cadena.length(); i++) {
			aux = cadena.charAt(i);
			if (!Character.isDigit(aux))return false;
		}
		return true;
	}

	// añadir un articulo a la lista
	public boolean addArticulo(Articulo a) throws SQLException {
		Videojuego v;
		Consola c;
		PreparedStatement ps = conexion.getConexion().prepareStatement("INSERT INTO ARTICULO(ID_A, STOCK, PRECIO) VALUES (?, ?, ?)");
		ps.setInt(1, a.getId());
		ps.setInt(2, a.getStock());
		ps.setDouble(3, a.getPrecio());
		ps.executeUpdate();
		if (a instanceof Videojuego) {
			PreparedStatement ps2 = conexion.getConexion().prepareStatement("INSERT INTO VIDEOJUEGO (ID_V,TITULO,PEGI,PLATAFORMA,DURACION_APROX) VALUES (?, ?, ?, ?, ?)");
			v = (Videojuego) a;
			ps2.setInt(1, v.getId());
			ps2.setString(2, v.getTitulo());
			ps2.setString(3, v.getPegi());
			ps2.setString(4, v.getPlataforma());
			ps2.setInt(5, v.getDuracion());
			ps2.executeUpdate();
			ps2.close();
		} else if (a instanceof Consola) {
			PreparedStatement ps3 = conexion.getConexion().prepareStatement("INSERT INTO CONSOLA (ID_C,MARCA,MODELO,COLOR,MINI_DESCRIPCION,EDICION_ESPECIAL) VALUES (?, ?, ?, ?, ?, ?)");
			c = (Consola) a;
			ps3.setInt(1, c.getId());
			ps3.setString(2, c.getMarca());
			ps3.setString(3, c.getModelo());
			ps3.setString(4, c.getColor());
			ps3.setString(5, c.getMini_descripcion());
			ps3.setBoolean(6, c.isEdicion_especial());
			ps3.executeUpdate();
			ps3.close();
		}
		ps.close();
		articulos.add(a);
		return true;
	}

	public boolean existeId(int id) {
		for (int i = 0; i < articulos.size(); i++) {
			if (articulos.get(i).getId() == id)return true;
		}
		return false;
	}

	public boolean esVideojuego(int id) {
		for (Articulo articulo : articulos) {
			if (articulo instanceof Videojuego && articulo.getId() == id)
				return true;
		}
		return false;
	}

	public String obtenerArticulos() {
		String toret;
		Videojuego v;
		Consola c;
		if (articulos.size() == 0)return "0";
		else toret = "------------------------------------------";
		for (Articulo articulo : articulos) {
			if (articulo instanceof Videojuego) {
				v = (Videojuego) articulo;
				toret += "\n" + v.toString();
				toret += "\n------------------------------------------";
			} else if (articulo instanceof Consola) {
				c = (Consola) articulo;
				toret += "\n" + c.toString();
				toret += "\n------------------------------------------";
			}
		}
		return toret;
	}

	public String obtenerVideojuegos() {
		String toret = "";
		Videojuego v;
		int cont = 0;
		for (Articulo articulo : articulos) {
			if (articulo instanceof Videojuego) {
				if (cont == 0)toret = "------------------------------------------";
				cont++;
				v = (Videojuego) articulo;
				toret += "\n" + v.toString();
				toret += "\n------------------------------------------";
			}
		}
		if (cont == 0)return "0";
		return toret;
	}

	public void mostarArticulo(int id) {
		for (int i = 0; i < articulos.size(); i++) {
			if (articulos.get(i).getId() == id) {
				System.out.println(articulos.get(i).toString());
			}
		}
	}

	public String obtenerConsolas() {
		Consola c;
		int cont = 0;
		String toret = "";
		for (Articulo articulo : articulos) {
			if (articulo instanceof Consola) {
				if (cont == 0)toret = "------------------------------------------";
				cont++;
				c = (Consola) articulo;
				toret += "\n" + c.toString();
				toret += "\n------------------------------------------";
			}
		}
		if (cont == 0)return "0";
		return toret;
	}

	public String obtenerCorto() {
		Videojuego v;
		Consola c;
		String toret = "";
		if (articulos.size() == 0)
			return "0";
		int cont = 0;
		for (Articulo articulo : articulos) {
			if (articulo instanceof Videojuego) {
				v = (Videojuego) articulo;
				toret += v.getId() + "-" + v.getTitulo() + "\t";
			} else if (articulo instanceof Consola) {
				c = (Consola) articulo;
				toret += c.getId() + "-" + c.getMarca() + " " + c.getModelo() + "\t";
			}
			cont++;
			if (cont % 3 == 0)toret += "\n";
		}
		return toret;
	}

	public boolean borrarArticulo(int id) throws SQLException {
		boolean borrado = false;
		for (int i = 0; i < articulos.size(); i++) {
			if (articulos.get(i).getId() == id) {
				Statement s = conexion.getConexion().createStatement();
				s.executeUpdate("DELETE FROM ARTICULO WHERE ID_A =" + id);
				articulos.remove(i);
				s.close();
				Statement s2 = conexion.getConexion().createStatement();
				s2.executeUpdate("CALL COMPRUEBA_TICKETS_VACIOS()");
				s2.close();
				Statement s3 = conexion.getConexion().createStatement();
				s3.executeUpdate("CALL CREAR_RESUMENES_VENTAS()");
				s3.close();
				borrado = true;
				break;
			}
		}
		return borrado;
	}

	public boolean modificarStock(int id, int stock) throws SQLException {
		for (int i = 0; i < articulos.size(); i++) {
			if (articulos.get(i).getId() == id) {
				articulos.get(i).setStock(stock);
				Statement s = conexion.getConexion().createStatement();
				s.executeUpdate("UPDATE ARTICULO SET STOCK=" + stock + " WHERE ID_A =" + id);
				s.close();
				return true;
			}
		}
		return false;
	}

	public boolean modificarPrecio(int id, double precio) throws SQLException {
		for (int i = 0; i < articulos.size(); i++) {
			if (articulos.get(i).getId() == id) {
				articulos.get(i).setPrecio(precio);
				Statement s = conexion.getConexion().createStatement();
				s.executeUpdate("UPDATE ARTICULO SET PRECIO=" + precio + " WHERE ID_A =" + id);
				s.close();
				Statement s2 = conexion.getConexion().createStatement();
				s2.executeUpdate("CALL CREAR_RESUMENES_VENTAS()");
				s2.close();
				return true;
			}
		}
		return false;
	}

	public boolean modificarTitulo(int id, String titulo) throws SQLException {
		titulo = titulo.toUpperCase();
		Videojuego v;
		for (int i = 0; i < articulos.size(); i++) {
			if (articulos.get(i).getId() == id) {
				if (articulos.get(i) instanceof Videojuego) {
					Statement s = conexion.getConexion().createStatement();
					s.executeUpdate("UPDATE VIDEOJUEGO SET TITULO=\"" + titulo + "\" WHERE ID_V =" + id);
					v = (Videojuego) articulos.get(i);
					v.setTitulo(titulo);
					articulos.set(i, v);
					s.close();
					return true;
				}
			}
		}
		return false;
	}

	public boolean modificarPegi(int id, String pegi) throws SQLException {
		pegi = pegi.toUpperCase();
		Videojuego v;
		for (int i = 0; i < articulos.size(); i++) {
			if (articulos.get(i).getId() == id) {
				if (articulos.get(i) instanceof Videojuego) {
					Statement s = conexion.getConexion().createStatement();
					s.executeUpdate("UPDATE VIDEOJUEGO SET PEGI=\"" + pegi + "\" WHERE ID_V =" + id);
					v = (Videojuego) articulos.get(i);
					v.setTitulo(pegi);
					articulos.set(i, v);
					s.close();
					return true;
				}
			}
		}
		return false;
	}

	public boolean modificarPlataforma(int id, String plataforma) throws SQLException {
		plataforma = plataforma.toUpperCase();
		Videojuego v;
		for (int i = 0; i < articulos.size(); i++) {
			if (articulos.get(i).getId() == id) {
				if (articulos.get(i) instanceof Videojuego) {
					Statement s = conexion.getConexion().createStatement();
					s.executeUpdate("UPDATE VIDEOJUEGO SET PLATAFORMA=\"" + plataforma + "\" WHERE ID_V =" + id);
					v = (Videojuego) articulos.get(i);
					v.setPlataforma(plataforma);
					articulos.set(i, v);
					s.close();
					return true;
				}
			}
		}
		return false;
	}

	public boolean modificarDuracion(int id, int duracion) throws SQLException {
		Videojuego v;
		for (int i = 0; i < articulos.size(); i++) {
			if (articulos.get(i).getId() == id) {
				if (articulos.get(i) instanceof Videojuego) {
					Statement s = conexion.getConexion().createStatement();
					s.executeUpdate("UPDATE VIDEOJUEGO SET DURACION_APROX=" + duracion + " WHERE ID_V =" + id);
					v = (Videojuego) articulos.get(i);
					v.setDuracion(duracion);
					articulos.set(i, v);
					s.close();
					return true;
				}
			}
		}
		return false;
	}

	public boolean modificarModelo(int id, String modelo) throws SQLException {
		modelo = modelo.toUpperCase();
		Consola c;
		for (int i = 0; i < articulos.size(); i++) {
			if (articulos.get(i).getId() == id) {
				if (articulos.get(i) instanceof Consola) {
					Statement s = conexion.getConexion().createStatement();
					s.executeUpdate("UPDATE CONSOLA SET MODELO=\"" + modelo + "\" WHERE ID_C =" + id);
					c = (Consola) articulos.get(i);
					c.setModelo(modelo);
					articulos.set(i, c);
					s.close();
					return true;
				}
			}
		}
		return false;
	}

	public boolean modificarMarca(int id, String marca) throws SQLException {
		marca = marca.toUpperCase();
		Consola c;
		for (int i = 0; i < articulos.size(); i++) {
			if (articulos.get(i).getId() == id) {
				if (articulos.get(i) instanceof Consola) {
					Statement s = conexion.getConexion().createStatement();
					s.executeUpdate("UPDATE CONSOLA SET MARCA=\"" + marca + "\" WHERE ID_C =" + id);
					c = (Consola) articulos.get(i);
					c.setModelo(marca);
					articulos.set(i, c);
					s.close();
					return true;
				}
			}
		}
		return false;
	}

	public boolean modificarColor(int id, String color) throws SQLException {
		color = color.toUpperCase();
		Consola c;
		for (int i = 0; i < articulos.size(); i++) {
			if (articulos.get(i).getId() == id) {
				if (articulos.get(i) instanceof Consola) {
					Statement s = conexion.getConexion().createStatement();
					s.executeUpdate("UPDATE CONSOLA SET COLOR=\"" + color + "\" WHERE ID_C =" + id);
					c = (Consola) articulos.get(i);
					c.setModelo(color);
					articulos.set(i, c);
					s.close();
					return true;
				}
			}
		}
		return false;
	}

	public boolean modificarDescripcion(int id, String descripcion) throws SQLException {
		Consola c;
		for (int i = 0; i < articulos.size(); i++) {
			if (articulos.get(i).getId() == id) {
				if (articulos.get(i) instanceof Consola) {
					Statement s = conexion.getConexion().createStatement();
					if (descripcion == null)s.executeUpdate("UPDATE CONSOLA SET MINI_DESCRIPCION=null WHERE ID_C =" + id);
					else s.executeUpdate("UPDATE CONSOLA SET MINI_DESCRIPCION=\"" + descripcion + "\" WHERE ID_C =" + id);
					c = (Consola) articulos.get(i);
					c.setMini_descripcion(descripcion);
					articulos.set(i, c);
					s.close();
					return true;
				}
			}
		}
		return false;
	}

	public boolean modificarEdicionEspecial(int id, boolean ed) throws SQLException {
		Consola c;
		for (int i = 0; i < articulos.size(); i++) {
			if (articulos.get(i).getId() == id) {
				if (articulos.get(i) instanceof Consola) {
					Statement s = conexion.getConexion().createStatement();
					s.executeUpdate("UPDATE CONSOLA SET EDICION_ESPECIAL=" + ed + " WHERE ID_C =" + id);
					c = (Consola) articulos.get(i);
					c.setEdicion_especial(ed);
					articulos.set(i, c);
					s.close();
					return true;
				}
			}
		}
		return false;
	}

	public int nuevoId() {
		int cont = 1;
		boolean aux;
		while (true) {
			aux = true;
			for (int i = 0; i < articulos.size(); i++) {
				if (cont == articulos.get(i).getId()) {
					aux = false;
					break;
				}
			}
			if (aux)
				return cont;
			cont++;
		}
	}

	public boolean consolaHasDescripcion(int id) {
		Consola c;
		for (int i = 0; i < articulos.size(); i++) {
			if (articulos.get(i).getId() == id) {
				if (articulos.get(i) instanceof Consola) {
					c = (Consola) articulos.get(i);
					if (c.getMini_descripcion() != null)
						return true;
					else
						return false;
				}
			}
		}
		return false;
	}

	public boolean consolaIsEdicionEspecial(int id) {
		Consola c;
		for (int i = 0; i < articulos.size(); i++) {
			if (articulos.get(i).getId() == id) {
				if (articulos.get(i) instanceof Consola) {
					c = (Consola) articulos.get(i);
					if (c.isEdicion_especial())
						return true;
					else
						return false;
				}
			}
		}
		return false;
	}

	public ArrayList<Articulo> getArticulos() {
		return ArticuloController.articulos;
	}

	public Articulo getArticuloId(int id) {
		for (Articulo articulo : articulos) {
			if (articulo.getId() == id)
				return articulo;
		}
		return null;
	}

}
