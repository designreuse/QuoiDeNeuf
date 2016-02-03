package services.utilisateur;

import java.sql.*;

import javax.servlet.ServletRequest;

import app.utils.BddUtils;
import app.utils.ServiceUtils;
import app.utils.TabAndCo;
import services.core.AbstractService;
import services.objects.RequestObject;
import services.objects.ResponseObject;


public class EnregistrerUtilisateurService extends AbstractService {
	
	public EnregistrerUtilisateurService() {
		super();
		this.requiredData = TabAndCo.getRequiredFields(TabAndCo.TABLE_USERS);
	}
	
	@Override
	public ResponseObject serviceLogic(RequestObject requestObject, ServletRequest servletRequest) {
		final String login = requestObject.getDataStringValue(TabAndCo.USERS_LOGIN);
		
		// Verifier si le login est deja utilise
		boolean existeDeja = BddUtils.existeDans("users", TabAndCo.USERS_LOGIN, login);
		if(existeDeja){
			this.responseObject.setResponseData(ResponseObject.RETURN_CODE_ERROR, "Login " +login+ " deja utilisé", null);
		}else{
			// Creer un utilisateur
			// Associer les groupes par defaut
			
			
			
			final Connection connection = ServiceUtils.getConnection();
			final StringBuilder req = new StringBuilder("INSERT INTO users (login, mdp, nom, email, description, photo) values (?,md5(?),?,?,?,?);" );
			
			try {
				final PreparedStatement pst = connection.prepareStatement(req.toString());
				
				// Valeurs par defaut
				String nom = requestObject.getDataStringValue(TabAndCo.USERS_NOM);
				nom = (nom == null || nom.isEmpty()) ? login : nom;
				
				pst.setString(1, login);
				pst.setString(2, requestObject.getDataStringValue(TabAndCo.USERS_MDP));
				pst.setString(3, nom);
				pst.setString(4, requestObject.getDataStringValue(TabAndCo.USERS_EMAIL));
				pst.setString(5, requestObject.getDataStringValue(TabAndCo.USERS_DESCRIPTION));
				pst.setString(6, "default.png");
				
				pst.executeUpdate();
				
				Statement stt = connection.createStatement();
                stt.executeUpdate("insert into usergroups values ((select numu from users where login='"+login+"') , 1) ;"
                        +"insert into usergroups values ((select numu from users where login='"+login+"') , 2);"
                        +"insert into usergroups values ((select numu from users where login='"+login+"') , 3);" 
                        +"insert into usergroups values ((select numu from users where login='"+login+"') , 4);");
				
				
				
				this.responseObject.setResponseData(ResponseObject.RETURN_CODE_OK, "Utilisateur "+ requestObject.getDataStringValue(TabAndCo.USERS_LOGIN) + " créé.", null);
				
			} catch (SQLException e) {
				ServiceUtils.logger.error("Erreur exec SQL");
				e.printStackTrace();
			}finally {
				try { connection.close(); }
				catch (SQLException e) { ServiceUtils.logger.error("Erreur close connexion"); e.printStackTrace(); }
			}
		}
		return responseObject;
	}
	
	
	
	
}
