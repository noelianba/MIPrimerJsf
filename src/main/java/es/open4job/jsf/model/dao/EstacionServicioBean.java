package es.open4job.jsf.model.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import es.open4job.jsf.model.vo.EstacionServicio;

@ManagedBean(name="estacion")
@SessionScoped

public class EstacionServicioBean implements Serializable{
	 
	//resource injection
	@Resource(name="jdbc/myoracle")
	private DataSource ds;
 
	public EstacionServicioBean(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
 
	}
	
	
	// Listado de la estacion de servicio
	
			public ArrayList<EstacionServicio> getListadoEstacionServicio() throws Exception {
			
				Connection conn = ds.getConnection();
				ArrayList<EstacionServicio> lista = new ArrayList<EstacionServicio>();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ESTACION_SERVICIO");
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					lista.add (new EstacionServicio(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getFloat(4),rs.getFloat(5),rs.getString(6)));
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (Exception e) {
					}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (Exception e) {
					}
				}
			return lista;
			}
			
			//Devuelve la estacion de servicio por el id
			
			public  EstacionServicio getListadoPorId(int id) throws Exception {
				
				Connection conn = ds.getConnection();
				EstacionServicio idLista = null;
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ESTACION_SERVICIO WHERE id = ?");
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()){
					idLista = new EstacionServicio(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getFloat(4),rs.getFloat(5),rs.getString(6));
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (Exception e) {
					}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (Exception e) {
					}
				}
			return idLista;
			
			}
			

}
