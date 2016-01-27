package services.utilisateur;

import java.sql.*;
import app.utils.ServiceUtils;
import app.utils.TabAndCo;
import services.core.AbstractService;
import services.objects.RequestObject;
import services.objects.ResponseObject;


public class VerifierUtilisateurService extends AbstractService {
	

	@Override
	public ResponseObject execute(RequestObject requestObject) {

		Connection connection = ServiceUtils.getConnection();
	
		try {
			final PreparedStatement stmt = connection.prepareStatement("select * from users where login = ? and mdp = md5(?)");
			
			final String login =  requestObject.getDataStringValue(TabAndCo.USERS_LOGIN);
			stmt.setString(1, login);
			stmt.setString(2, requestObject.getDataStringValue(TabAndCo.USERS_MDP));
			final ResultSet rs = stmt.executeQuery();
			 
			if(rs.next()) {
				this.responseObject.setResponseData(ResponseObject.RETURN_CODE_OK, "Utilisateur " +login+ " bien identifié vous allez être redirigé vers votre page", null);
				ServiceUtils.logger.warn("Utilisateur " +login+ " connecté.");
			}else{
			 	this.responseObject.setResponseData(ResponseObject.RETURN_CODE_ERROR, "Utilisateur " +login+ " non reconnu ", null);
			}
		} catch (SQLException e) {
			ServiceUtils.logger.error("Erreur SQLException");
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				ServiceUtils.logger.error("Erreur close connexion");
				e.printStackTrace();
			}
		}
	
		return this.responseObject;
	}
		
}