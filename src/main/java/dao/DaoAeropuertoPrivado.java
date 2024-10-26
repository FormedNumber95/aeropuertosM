package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConexionBBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ModeloAeropuertoPrivado;

/**
 * Clase DaoAeropuertoPrivado.
 */
public class DaoAeropuertoPrivado {

	/** EL conection. */
	private static Connection conection;
	
	/**
	 * Cargar lista aeropuertos privados.
	 *
	 * @return the observable list
	 */
	public static ObservableList<ModeloAeropuertoPrivado> cargarListaAeropuertosPrivados(){
		ObservableList<ModeloAeropuertoPrivado>lst=FXCollections.observableArrayList();
		try {
			conection=ConexionBBDD.getConnection();
			String select="SELECT id,numero_socios,nombre,anio_inauguracion,capacidad,id_direccion,imagen FROM aeropuertos_privados,aeropuertos WHERE id=id_aeropuerto";
			PreparedStatement pstmt = conection.prepareStatement(select);
			ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	ModeloAeropuertoPrivado modelo=new ModeloAeropuertoPrivado(rs.getString("nombre"),rs.getInt("anio_inauguracion"),rs.getInt("capacidad"), DaoDireccion.crearModeloDireccionPorID(rs.getInt("id_direccion")), rs.getBlob("imagen"),rs.getInt("numero_socios"));
            	modelo.setId(rs.getInt("id"));
            	lst.add(modelo);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	/**
	 * Aniadir.
	 *
	 * @param idAeropuerto the id aeropuerto
	 * @param numSocios the num socios
	 */
	public static void aniadir(int idAeropuerto,int numSocios) {
		conection=ConexionBBDD.getConnection();
		String insert="INSERT INTO aeropuertos_privados VALUES (?,?)";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(insert);
			pstmt.setInt(1,idAeropuerto);
			pstmt.setInt(2,numSocios);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Modificar por ID.
	 *
	 * @param id the id
	 * @param numSocios the num socios
	 */
	public static void modificarPorID(int id,int numSocios) {
		conection=ConexionBBDD.getConnection();
		String update="UPDATE aeropuertos_privados SET numero_socios=? WHERE id_aeropuerto=?";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(update);
			pstmt.setInt(1, numSocios);
			pstmt.setInt(2,id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Eliminar.
	 *
	 * @param id the id
	 */
	public static void eliminar(int id) {
		conection=ConexionBBDD.getConnection();
		String delete="DELETE FROM aeropuertos_privados WHERE id_aeropuerto=?";
		try {
			PreparedStatement pstmt=conection.prepareStatement(delete);
			pstmt.setInt(1,id);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
