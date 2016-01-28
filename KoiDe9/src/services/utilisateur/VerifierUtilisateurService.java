package services.utilisateur;

import java.sql.*;
import app.utils.ServiceUtils;
import app.utils.TabAndCo;
import services.core.AbstractService;
import services.objects.RequestObject;
import services.objects.ResponseObject;


/**
 * Permet de verifier le login et le mot de passe d'un utilisateur
 * @author VASTRAA
 */
public class VerifierUtilisateurService extends AbstractService {
	
	public VerifierUtilisateurService(){
		this.requiredData = TabAndCo.getRequiredFields(TabAndCo.TABLE_USERS);
	}
	
	
	@Override
	public ResponseObject serviceLogic(RequestObject requestObject) {
		final Connection connection = ServiceUtils.getConnection();
		
		try {
			final PreparedStatement pst = connection.prepareStatement("SELECT * FROM users WHERE login = ? AND mdp = md5(?);");

			final String login = requestObject.getDataStringValue(TabAndCo.USERS_LOGIN);
			final String mdp   = requestObject.getDataStringValue(TabAndCo.USERS_MDP);  
			pst.setString(1, login);
			pst.setString(2, mdp);
			 
			final ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				this.responseObject.setResponseData(ResponseObject.RETURN_CODE_OK, "Utilisateur " +login+ " bien identifié vous allez être redirigé vers votre page", null);
				ServiceUtils.logger.warn("Utilisateur " +login+ " connecté.");
			}else{
			 	this.responseObject.setResponseData(ResponseObject.RETURN_CODE_ERROR, "Login ou mot de passe incorrect.", null);
			}
		} catch (SQLException e) {
			ServiceUtils.logger.error("Erreur SQLException");
			e.printStackTrace();
		}finally {
			try { connection.close(); } 
			catch (SQLException e) { ServiceUtils.logger.error("Erreur close connexion"); e.printStackTrace(); }
		}
		return this.responseObject;
	}
	
	
}