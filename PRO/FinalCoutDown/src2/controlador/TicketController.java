package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bbdd.BDConnection;
import modelo.Articulo;
import modelo.Consola;
import modelo.Ticket;
import modelo.Videojuego;

public class TicketController {
	private static ArrayList<Ticket> tickets;
	private static BDConnection conexion;
	private static ArrayList<Articulo>todosArticulos;
	private static ArrayList<Articulo>articulosPorPrecio;
	
	public TicketController() throws Exception {
		conexion = new BDConnection();
		tickets = new ArrayList<Ticket>();
		todosArticulos = new ArrayList<Articulo>();
		articulosPorPrecio= new ArrayList<Articulo>();
		guardarArticulosBD();
		guardarTicketsBD();
	}
	
	private void guardarArticulosBD() throws SQLException {
		Statement s = conexion.getConexion().createStatement();
		Statement s2= conexion.getConexion().createStatement();
		String query = "SELECT * FROM ART_DISPONIBLES";
		String query2= "SELECT * FROM ARTICULOS_ORDENADOS_PRECIO";
		ResultSet rs = s.executeQuery(query);
		ResultSet rs2 = s2.executeQuery(query2); 
		String aux;
		Videojuego v;
		Consola c;
		while (rs.next()) {
			rs2.next();
			aux = rs.getString(4);
			if (isNumeric(aux)) {
				v = new Videojuego(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(5), rs.getString(6), rs.getString(8), rs.getInt(7));
				todosArticulos.add(v);
			} else {
				c = new Consola(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getBoolean(14));
				todosArticulos.add(c);
			}
			
			aux = rs2.getString(4);
			
			if (isNumeric(aux)) {
				v = new Videojuego(rs2.getInt(1), rs2.getInt(2), rs2.getDouble(3), rs2.getString(5), rs2.getString(6), rs2.getString(8), rs2.getInt(7));
				articulosPorPrecio.add(v);
			} else {
				c = new Consola(rs2.getInt(1), rs2.getInt(2), rs2.getInt(3), rs2.getString(10), rs2.getString(11), rs2.getString(12), rs2.getString(13), rs2.getBoolean(14));
				articulosPorPrecio.add(c);
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
	
	private void guardarTicketsBD() throws SQLException {
		Statement s = conexion.getConexion().createStatement();
		Statement s2 = conexion.getConexion().createStatement();
		String query="SELECT * FROM RESUMEN_TICKETS";
		ResultSet rs=s.executeQuery(query);
		int id_ticket;
		String fecha_ticket;
		double total;
		ArrayList<Integer>cantidades;
		ArrayList<Articulo>articulos;
		int aux;
		Articulo a;
		Ticket t;
		
		while(rs.next()) {
			id_ticket=rs.getInt(1);
			total=rs.getDouble(2);
			fecha_ticket=rs.getString(3);
			String query2="SELECT * FROM RESUMEN_TICKETS_2 WHERE TICKET="+id_ticket;
			ResultSet rs2=s2.executeQuery(query2);
			cantidades=new ArrayList<Integer>();
			articulos=new ArrayList<Articulo>();
			while(rs2.next()) {
				a =getArticuloId(rs2.getInt(2));
				articulos.add(a);
				aux=rs2.getInt(4);
				cantidades.add(aux);
			}
			
			t=new Ticket(id_ticket, fecha_ticket, total, articulos, cantidades);
			tickets.add(t);
			
			rs2.close();
		}
		rs.close();
	}
	
	public Articulo getArticuloId(int id) {
		for (Articulo articulo : todosArticulos) {
			if (articulo.getId() == id)
				return articulo;
		}
		return null;
	}

	public String mostrarTickets() {
		String toret="";
		if(tickets.size()==0)return "0";
		else toret="------------------------------------------";
		
		for(Ticket ticket:tickets) {
			toret+="\n"+ticket.toString();
			toret+="\n------------------------------------------";
		}
		return toret;
	}
	
	public String mostrarTicket(int id) {
		String toret="ticket no encontrado";
		for(Ticket ticket:tickets) {
			if(ticket.getId()==id)toret=ticket.toString();
		}
		return toret;
	}

	public String mostrarTicketsCorto() {
		String toret="";
		if(tickets.size()==0)return "0";
		int cont=1;
		for(Ticket ticket:tickets) {
			toret+=ticket.toStringShort()+"\t";
			if(cont%3==0)toret+="\n";
			cont++;
		}
		return toret;
	}
	
	public String obtenerArticulos() {
		String toret;
		Videojuego v;
		Consola c;
		if (todosArticulos.size() == 0)return "0";
		else toret = "------------------------------------------";
		for (Articulo articulo : todosArticulos) {
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
	
	public String obtenerArticulosPorPrecio() {
		String toret;
		Videojuego v;
		Consola c;
		if (articulosPorPrecio.size() == 0)return "0";
		else toret = "------------------------------------------";
		for (Articulo articulo : articulosPorPrecio) {
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

	public int nuevoIdTicket() {
		int cont = 1;
		boolean aux;
		while (true) {
			aux = true;
			for (int i = 0; i <tickets.size(); i++) {
				if (cont == tickets.get(i).getId()) {
					aux = false;
					break;
				}
			}
			if (aux)
				return cont;
			cont++;
		}
	}

	public boolean existeIdTicket(int id) {
		for(Ticket t: tickets) {
			if(t.getId()==id)return true;
		}
		return false;
	}

	public boolean eliminarTicket(int id) throws SQLException {
		boolean borrado = false;
		for (int i = 0; i < tickets.size(); i++) {
			if (tickets.get(i).getId() == id) {
				Statement s = conexion.getConexion().createStatement();
				s.executeUpdate("DELETE FROM TICKET WHERE ID =" + id);
				tickets.remove(i);
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

	public boolean anhadirTicket(Ticket t) throws SQLException {
		PreparedStatement ps = conexion.getConexion().prepareStatement("INSERT INTO TICKET(ID, FECHA_HORA) VALUES (?, ?)");
		ps.setInt(1, t.getId());
		ps.setString(2, t.getFecha());
		ps.executeUpdate();
		int vueltas = t.getCantidades().size();
		for(int i=0;i<vueltas; i++) {
			anhadirTicketArticulo(t.getId(),t.getArticulos().get(i).getId() ,t.getCantidades().get(i));
		}
		Statement s2 = conexion.getConexion().createStatement();
		s2.executeUpdate("CALL COMPRUEBA_TICKETS_VACIOS()");
		s2.close();
		Statement s3 = conexion.getConexion().createStatement();
		s3.executeUpdate("CALL CREAR_RESUMENES_VENTAS()");
		s3.close();
		tickets.add(t);
		return true;
	}

	private void anhadirTicketArticulo(int id, int art, int cant) throws SQLException {
		PreparedStatement ps = conexion.getConexion().prepareStatement("INSERT INTO TICKET_ARTICULO(ID_TICKET, ID_ARTICULO, CANT) VALUES (?, ?, ?)");
		ps.setInt(1, id);
		ps.setInt(2, art);
		ps.setInt(3, cant);
		ps.executeUpdate();
	}
	
	public String obtenerArticulosCorto() {
		Videojuego v;
		Consola c;
		String toret = "";
		if (todosArticulos.size() == 0)
			return "0";
		int cont = 0;
		for (Articulo articulo : todosArticulos) {
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
	
	public boolean existeIdArticulo(int id) {
		for (int i = 0; i < todosArticulos.size(); i++) {
			if (todosArticulos.get(i).getId() == id)return true;
		}
		return false;
	}
	
	public double calcularTotal(ArrayList<Articulo>arts, ArrayList<Integer>cants) {
		double tot=0;
		for(int i=0;i<arts.size();i++) {
			tot+=arts.get(i).getPrecio()*cants.get(i);
		}
		return tot;
	}

	public Ticket getTicket(int id) {
		for(Ticket t:tickets) {
			if(t.getId()==id)return t;
		}
		return null;
	}
}
