package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConexionBBDD;
import model.ModeloDireccion;

/**
 * Clase DaoDireccion.
 */
public class DaoDireccion {

	/** EL conection. */
	private static Connection conection;
	
	/**
	 * Crear modelo direccion por ID.
	 *
	 * @param id the id
	 * @return the modelo direccion
	 */
	public static ModeloDireccion crearModeloDireccionPorID(int id) {
		conection=ConexionBBDD.getConnection();
		String select="SELECT pais,ciudad,calle,numero FROM direcciones WHERE id=?";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(select);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new ModeloDireccion(rs.getString("pais"),rs.getString("ciudad"),rs.getString("calle"),rs.getInt("numero"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Conseguir ID.
	 *
	 * @param pais the pais
	 * @param ciudad the ciudad
	 * @param calle the calle
	 * @param numero the numero
	 * @return the integer
	 */
	public static Integer conseguirID(String pais, String ciudad,String calle,int numero) {
		conection=ConexionBBDD.getConnection();
		String select="SELECT id FROM direcciones WHERE pais=? AND ciudad=? AND calle=? AND numero=?";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(select);
			pstmt.setString(1,pais);
			pstmt.setString(2,ciudad);
			pstmt.setString(3,calle);
			pstmt.setInt(4,numero);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Aniadir.
	 *
	 * @param pais the pais
	 * @param ciudad the ciudad
	 * @param calle the calle
	 * @param numero the numero
	 */
	public static void aniadir(String pais, String ciudad,String calle,int numero) {
		conection=ConexionBBDD.getConnection();
		String insert="INSERT INTO direcciones (pais,ciudad,calle,numero) VALUES (?,?,?,?)";
		try {
			PreparedStatement pstmt;
			pstmt=conection.prepareStatement(insert);
			pstmt.setString(1,pais);
			pstmt.setString(2,ciudad);
			pstmt.setString(3,calle);
			pstmt.setInt(4,numero);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
