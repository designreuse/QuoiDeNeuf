package app.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import services.objects.RequestObject;

public class BddUtils {

	
//	public static PreparedStatement createInsertSQL(final String table, final String[] colonnes, final RequestObject requestObject, Connection connection){
//		final StringBuilder req = new StringBuilder("INSERT INTO " +table+ " (" );
//		
//		for(String col : colonnes){
//			req.append(col + ",");
//		}
//		
//		req.replace(req.length()-1, req.length(), ")");
//		req.append(" VALUES (");
//		
//		for (int i=0; i < colonnes.length; i++) {
//			req.append("?,");
//		}
//		
//		req.replace(req.length()-1, req.length(), ");");
//		
//		try {
//			final PreparedStatement pst = connection.prepareStatement(req.toString());
//			
//			for (int i=0; i < colonnes.length; i++) {
//				final String val =  requestObject.getDataValue(colonnes[i]);
//				pst.setString(i+1, val);
//				
//			}
//			System.out.println(pst);
//			return pst;
//			
//		} catch (SQLException e) {
//			ServiceUtils.logger.error("Erreur lors de la creation du PreparedStatement");
//			e.printStackTrace();
//		}
//
//		return null;
//	}
	
	
	public static boolean existeDans (final String table, final String col, final String value){
		final Connection connection = ServiceUtils.getConnection();
		final StringBuilder req = new StringBuilder("SELECT * FROM " +table+ " WHERE " +col+ " = '" +value+ "';");
		boolean res = false;
		try {
			final Statement st = connection.createStatement();
			final ResultSet rs = st.executeQuery(req.toString());
			
			res = rs.next();
			
		} catch (SQLException e) {
			ServiceUtils.logger.error("Erreur lors de la recuperation du Statement ");
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				ServiceUtils.logger.error("Erreur close connexion");
				e.printStackTrace();
			}
		}
		
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
