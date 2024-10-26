package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.ConexionBBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ModeloAvion;

/**
 * Clase DaoAvion.
 */
public class DaoAvion {
	
	/** EL conection. */
	private static Connection conection;
	
	/**
	 * Lista aviones.
	 *
	 * @param id the id
	 * @return the observable list
	 */
	public static ObservableList<ModeloAvion> listaAviones(int id){
		conection=ConexionBBDD.getConnection();
		String select="SELECT modelo,numero_asientos,velocidad_maxima,activado FROM aviones WHERE id_aeropuerto=?";
		ObservableList<ModeloAvion> lst=FXCollections.observableArrayList();
		try {
			PreparedStatement pstmt = conection.prepareStatement(select);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ModeloAvion avion=new ModeloAvion(rs.getString("modelo"), rs.getInt("numero_asientos"),rs.getInt("velocidad_maxima"), rs.getInt("activado")==1,id);
				lst.add(avion);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	/**
	 * Conseguir lista todos.
	 *
	 * @return the array list
	 */
	public static ArrayList<ModeloAvion> conseguirListaTodos(){
		ArrayList<ModeloAvion>lst=new ArrayList<ModeloAvion>();
		conection=ConexionBBDD.getConnection();
		String select="SELECT modelo,numero_asientos,velocidad_maxima,activado,id_aeropuerto FROM aviones";
		try {
			PreparedStatement pstmt = conection.prepareStatement(select);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ModeloAvion avion=new ModeloAvion(rs.getString("modelo"), rs.getInt("numero_asientos"),rs.getInt("velocidad_maxima"), rs.getInt("activado")==1,rs.getInt("id_aeropuerto"));
				lst.add(avion);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	/**
	 * Aniadir.
	 *
	 * @param modelo the modelo
	 * @param numAsientos the num asientos
	 * @param velMax the vel max
	 * @param activado the activado
	 * @param idAeropuerto the id aeropuerto
	 */
	public static void aniadir(String modelo,int numAsientos,int velMax, boolean activado, int idAeropuerto) {
		conection=ConexionBBDD.getConnection();
		String insert="INSERT INTO aviones (modelo,numero_asientos,velocidad_maxima,activado,id_aeropuerto) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conection.prepareStatement(insert);
			pstmt.setString(1, modelo);
			pstmt.setInt(2,numAsientos);
			pstmt.setInt(3,velMax);
			if(activado) {
				pstmt.setInt(4,1);
			}else {
				pstmt.setInt(4,0);
			}
			pstmt.setInt(5,idAeropuerto);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Borrar.
	 *
	 * @param modelo the modelo
	 * @param idAeropuerto the id aeropuerto
	 */
	public static void borrar(String modelo,int idAeropuerto) {
		conection=ConexionBBDD.getConnection();
		String delete="DELETE FROM aviones WHERE modelo=? AND id_aeropuerto=?";
		try {
			PreparedStatement pstmt = conection.prepareStatement(delete);
			pstmt.setString(1, modelo);
			pstmt.setInt(2,idAeropuerto);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Update estado.
	 *
	 * @param modelo the modelo
	 * @param idAeropuerto the id aeropuerto
	 * @param estado the estado
	 */
	public static void updateEstado(String modelo,int idAeropuerto,boolean estado) {
		conection=ConexionBBDD.getConnection();
		String update="UPDATE aviones SET activado=? WHERE modelo=? AND id_aeropuerto=?";
		try {
			PreparedStatement pstmt = conection.prepareStatement(update);
			if(estado) {
				pstmt.setInt(1,1);
			}else {
				pstmt.setInt(1,0);
			}
			pstmt.setString(2,modelo);
			pstmt.setInt(3,idAeropuerto);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
