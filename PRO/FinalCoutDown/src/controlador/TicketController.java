package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bbdd.BDConnection;
import modelo.Articulo;
import modelo.Ticket;


public class TicketController {
	private static ArrayList<Ticket>tickets;
	private static BDConnection conexion;
	
	
	public TicketController(ArticuloController controlador) throws Exception{
		conexion = new BDConnection();
		tickets = new ArrayList<Ticket>();
		guardarTicketsBD(controlador);
	}

	private void guardarTicketsBD(ArticuloController controlador) throws SQLException {
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
				a = controlador.getArticuloId(rs2.getInt(2));
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
	
}
