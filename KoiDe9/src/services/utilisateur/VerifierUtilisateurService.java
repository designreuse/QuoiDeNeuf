package services.utilisateur;

import java.sql.*;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import app.utils.MappingBddToBeans;
import app.utils.ServiceUtils;
import app.utils.TabAndCo;
import services.beans.Utilisateur;
import services.core.AbstractService;
import services.objects.RequestObject;
import services.objects.ResponseObject;


/**
 * Permet de verifier le login et le mot de passe d'un utilisateur
 * @author VASTRAA
 */
public class VerifierUtilisateurService extends AbstractService {
	
	public VerifierUtilisateurService(){
		super();
		this.requiredData = TabAndCo.getRequiredFields(TabAndCo.TABLE_USERS);
	}
	
	
	@Override
	public ResponseObject serviceLogic(RequestObject requestObject, ServletRequest servletRequest) {
		final Connection connection = ServiceUtils.getConnection();
		
		try {
			final PreparedStatement pst = connection.prepareStatement("SELECT * FROM users WHERE login = ? AND mdp = md5(?);");

			final String login = requestObject.getDataStringValue(TabAndCo.USERS_LOGIN);
			final String mdp   = requestObject.getDataStringValue(TabAndCo.USERS_MDP);  
			pst.setString(1, login);
			pst.setString(2, mdp);
			 
			final ResultSet rs = pst.executeQuery();
			final List<Utilisateur> liste = MappingBddToBeans.resultsetToListUtilisateur(rs);
			if(liste.size() == 1){
				this.responseObject.setResponseData(ResponseObject.RETURN_CODE_OK, "Utilisateur " +login+ " bien identifié vous allez être redirigé vers votre page", liste);
				final Utilisateur utilisateur = liste.get(0);
				ServiceUtils.logger.info("Utilisateur " +login+ " connecté.");
				
				final HttpServletRequest httpServReq = (HttpServletRequest) servletRequest;
				final HttpSession session = httpServReq.getSession();
				session.setAttribute("dejaConnecte", utilisateur);
			}else{
			 	this.responseObject.setResponseData(ResponseObject.RETURN_CODE_ERROR, "Erreur : Login ou mot de passe incorrect.", null);
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