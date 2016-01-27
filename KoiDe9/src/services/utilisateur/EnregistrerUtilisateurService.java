package services.utilisateur;

import java.sql.*;
import app.utils.BddUtils;
import app.utils.ServiceUtils;
import app.utils.TabAndCo;
import services.core.AbstractService;
import services.objects.RequestObject;
import services.objects.ResponseObject;


public class EnregistrerUtilisateurService extends AbstractService {

	private String[] requiredData = { TabAndCo.USERS_LOGIN, TabAndCo.USERS_MDP };
	
	@Override
	public ResponseObject execute(final RequestObject requestObject) {
		if (validate(requestObject, requiredData)) {

			final String login = requestObject.getDataStringValue(TabAndCo.USERS_LOGIN);
			// login deja utilise
			boolean existeDeja = BddUtils.existeDans("users", TabAndCo.USERS_LOGIN, login);
			if(existeDeja){
				this.responseObject.setResponseData(ResponseObject.RETURN_CODE_ERROR, "Login " +login+ " deja utilisé", null);
			}else{
				
				final Connection connection = ServiceUtils.getConnection();
				final StringBuilder req = new StringBuilder(
						"insert into users (login, mdp, nom, email, description, photo) values (?,md5(?),?,?,?,?);" );
				
				createUser(connection, requestObject, req.toString());
				
			}			
		} else {
			this.responseObject.setResponseData(ResponseObject.RETURN_CODE_ERROR, "Champ requis manquant", null);
		}
		return this.responseObject;
	}

	
	private void createUser(final Connection connection, final RequestObject requestObject, final String req ) {
		try {
			final PreparedStatement pst = connection.prepareStatement(req.toString());
			
			pst.setString(1, requestObject.getDataStringValue(TabAndCo.USERS_LOGIN));
			pst.setString(2, requestObject.getDataStringValue(TabAndCo.USERS_MDP));
			pst.setString(3, requestObject.getDataStringValue(TabAndCo.USERS_NOM));
			pst.setString(4, requestObject.getDataStringValue(TabAndCo.USERS_EMAIL));
			pst.setString(5, requestObject.getDataStringValue(TabAndCo.USERS_DESCRIPTION));
			pst.setString(6, requestObject.getDataStringValue(TabAndCo.USERS_PHOTO));
			
			pst.executeUpdate();
			this.responseObject.setResponseData(ResponseObject.RETURN_CODE_OK, "Utilisateur"+ requestObject.getDataStringValue(TabAndCo.USERS_LOGIN) + " créé", null);
			
			

		} catch (SQLException e) {
			ServiceUtils.logger.error("Erreur lors de la creation du executeUpdate");
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				ServiceUtils.logger.error("Erreur close connexion");
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}
